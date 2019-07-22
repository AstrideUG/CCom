package me.helight.ccom.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import lombok.Getter;
import me.helight.ccom.config.defaults.SimpleMongoConfig;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Getter
public class MongoConnector {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    private MongoConnector(SimpleMongoConfig credentials, boolean pojo) {
        if (pojo) {
            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            if (credentials.getLocal()) {
                ConnectionString connectionString = new ConnectionString("mongodb://"+ credentials.host + ":" + credentials.port);
                MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(pojoCodecRegistry).build();
                mongoClient = MongoClients.create(mongoClientSettings);
                mongoDatabase = mongoClient.getDatabase(credentials.getDatabase());
            } else {
                ConnectionString connectionString = new ConnectionString("mongodb://" + (credentials.user + ":" + credentials.password + "@") + credentials.host + ":" + credentials.port);
                MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(pojoCodecRegistry).build();
                mongoClient = MongoClients.create(mongoClientSettings);
                mongoDatabase = mongoClient.getDatabase(credentials.getDatabase());
            }
        } else {
            if (credentials.getLocal()) {
                ConnectionString connectionString = new ConnectionString("mongodb://"+ credentials.host + ":" + credentials.port);
                MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
                mongoClient = MongoClients.create(mongoClientSettings);
                mongoDatabase = mongoClient.getDatabase(credentials.getDatabase());
            } else {
                ConnectionString connectionString = new ConnectionString("mongodb://" + (credentials.user + ":" + credentials.password + "@") + credentials.host + ":" + credentials.port);
                MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
                mongoClient = MongoClients.create(mongoClientSettings);
                mongoDatabase = mongoClient.getDatabase(credentials.getDatabase());
            }
        }
    }

    /**
     * @param selector The selector to find the Document
     */
    public <K,V> void docSet(MongoCollection<K> collection, Bson selector, String key, V value) {
        collection.updateMany(selector, Updates.set(key, value));
    }

    /**
     * @param selector The selector to find the Document
     */
    public <K> void docRem(MongoCollection<K> collection, Bson selector, String key) {
        collection.updateMany(selector, Updates.unset(key));
    }

    /**
     * @param selector The selector to find the Document
     */
    @SuppressWarnings("unchecked")
    public <K,V> V docGet(MongoCollection<K> collection, Bson selector, String key) {
        Document document = collection.find(selector, Document.class).projection(Projections.include(key)).first();
        return document == null ? null : (V) document.get(key);
    }

    /**
     * @param selector The selector to find the Document
     * @param key The key to look for in the Document
     * @return Whether there is a document match the selector and containing the key
     */
    public <K> boolean docExists(MongoCollection<K> collection, Bson selector, String key) {
        return collection.countDocuments(Filters.and(selector, Filters.exists(key))) > 0;
    }

    public static MongoConnector dispense(SimpleMongoConfig config) {
        return new MongoConnector(config, true);
    }

    public static MongoConnector dispenseWithoutPojos(SimpleMongoConfig config) {
        return new MongoConnector(config, false);
    }
}

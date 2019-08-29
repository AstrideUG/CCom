package me.helight.ccom;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CCom {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("readme.ctfcompetition.com",1337);
            InputStream stream = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            String[] startup = new String[]{
                    "cd ../",
                    "cd usr/bin/",
                    "ls"
            };

            AtomicInteger totalChars = new AtomicInteger(0);

            Thread reader = new Thread(() -> {
                int i = 0;
                while (i != -1) {
                    totalChars.getAndIncrement();
                    try {
                        i = stream.read();
                        char c = (char)(i & 0xFF);
                        System.out.print(c + "("+(int)c+")");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread writer = new Thread(() -> {
                try {
                    Scanner scanner = new Scanner(System.in);
                    @Cleanup OutputStreamWriter wr = new OutputStreamWriter(out);

                    while (totalChars.get() == 0) {
                        cooldown();
                    }

                    for (int i = 0; i < startup.length; i++) {
                        String s = startup[i];
                        printString(s,out);
                        cooldown();
                    }


                    while (scanner.hasNext()) {
                        printString(scanner.nextLine(), out);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            reader.start();
            writer.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static void cooldown() {
        Thread.sleep(300);
    }

    @SneakyThrows
    public static void printString(String str, OutputStream out) {
        String line = str + "\n";


        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            int cint = c & 0xFF;
            out.write(cint);
        }
        out.flush();
    }

}

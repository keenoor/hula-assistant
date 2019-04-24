package com.keenor.hulaassistant;

import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UuidTests {

    private Set<String> set = Sets.newTreeSet();
    private ExecutorService pool = Executors.newFixedThreadPool(120);

    public static void main(String[] args) {
        UuidTests tests = new UuidTests();
        tests.contextLoads();
    }

    public void contextLoads() {
        //        for (int i = 0; i < 100; i++) {
        //            pool.execute(new GenUuid());
        //        }
        new GenUuid().run();
    }

    class GenUuid implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("thread: " + name);
            int num = 1_000_000_000;
            for (int i = 0; i < num; i++) {
                String id = UUID.randomUUID().toString();
                boolean add = set.add(id);
                if (!add) {
                    System.out.println("REPEAT: " + id.replace("-", ""));
                }
                if (i % 10000 == 0) {
                    System.out.println(" size: " + set.size());
                }
                if (i == num - 1) {
                    System.out.println("thread: " + name + " size: " + set.size());
                }
            }
        }
    }


}

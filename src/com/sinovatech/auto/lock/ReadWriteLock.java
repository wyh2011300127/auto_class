package com.sinovatech.auto.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLock {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final ReadWriteLock readWriteLock = new ReadWriteLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.get(Thread.currentThread());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.get(Thread.currentThread());
            }
        }).start();
    }

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 1) {
            System.out.println(thread.getName() + "正在进行读操作");
        }
        System.out.println(thread.getName() + "读操作完毕");
    }

}

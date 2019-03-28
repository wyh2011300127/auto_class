package com.sinovatech.auto.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock2 {
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReadWriteLock2 readWriteLock2 = new ReadWriteLock2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock2.get(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock2.get(Thread.currentThread());
            }
        }).start();

    }

    private void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 10) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }
}
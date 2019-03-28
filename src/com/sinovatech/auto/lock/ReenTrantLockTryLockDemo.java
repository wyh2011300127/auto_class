package com.sinovatech.auto.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockTryLockDemo {
    private List<Integer> list = new ArrayList<Integer>(10);
    private Lock lock = new ReentrantLock();
    private static final long i;
    static {
        i = 1;
    }

    public static void main(String[] args) {

        final ReenTrantLockTryLockDemo reenTrantLockDemo = new ReenTrantLockTryLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reenTrantLockDemo.insert(Thread.currentThread());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reenTrantLockDemo.insert(Thread.currentThread());
            }
        }).start();
    }

    public void insert(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "获取到了锁");
                for (int i = 0; i < 10000; i++) {
                    list.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }

}
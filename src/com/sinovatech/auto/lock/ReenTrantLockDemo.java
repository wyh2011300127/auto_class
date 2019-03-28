package com.sinovatech.auto.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockDemo {
    private List<Integer> list = new ArrayList<Integer>(10);
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final ReenTrantLockDemo reenTrantLockDemo = new ReenTrantLockDemo();
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
        // Lock lock = new ReentrantLock();该处定义起不到锁的作用，因为是局部变量
        lock.lock();
        try {
            System.out.println(thread.getName() + "获取到了锁");
            for (int i = 0; i < 5; i++) {
                list.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

}
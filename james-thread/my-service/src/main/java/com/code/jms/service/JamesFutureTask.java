package com.code.jms.service;

import java.util.concurrent.*;

public class JamesFutureTask<V> implements Runnable, Future<V> {

    Callable<V> callable; //封装业务逻辑

    V result = null; //执行结果

    public JamesFutureTask(Callable<V> callable){
        this.callable = callable;
    }

    //实现?执行业务逻辑
    @Override
    public void run() {
        try {
            result = callable.call();//调远程接口
            System.out.println("result =="+result);
            synchronized (this){
                this.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取业务逻辑的处理结果
    @Override
    public V get() throws InterruptedException, ExecutionException {
        if(result != null){
            return result;
        }
        synchronized (this){
            this.wait();//阻塞
        }
        return result;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }



    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}

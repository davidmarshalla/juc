package ThreadPoolDemo;

import java.util.concurrent.*;

public class MyThreadPoolDemo {


    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService myThreadPool = new ThreadPoolExecutor(
                2,//核心线程数量，线程池最终会收缩到核心线程数量
                5,//最大线程数量 = 核心线程数量 + blocking queue size
                3,//线程无事可做且超过延时时间，线程会判断池子中线程数量是否大于corePoolSize；
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingDeque<>(3),//blocking queue
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.CallerRunsPolicy()//线程池满时返还给发出请求的线程
        );

        //三种开启线程池的方法
        //固定线程池的大小
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //固定只有一个线程池
        threadPool = Executors.newSingleThreadExecutor();
        //容量可变线程池
        threadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 16; i++) {
            myThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
    }
}

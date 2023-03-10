package test1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/28 17:00
 */

public class Mother {
    int age = 55;

    public static void main(String[] args) {

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.err.println(12);
                Thread.sleep(5000);
                return 1;
            }
        });


        Thread thread = new Thread(task,"task");
        thread.start();
//        try {
//            System.err.println(task.get());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
        System.err.println(123);
                try {
            System.err.println(task.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}


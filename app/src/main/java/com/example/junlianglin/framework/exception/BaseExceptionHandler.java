package com.example.junlianglin.framework.exception;

/**
 * Created by JunliangLin on 1/4/2018.
 */

public abstract class BaseExceptionHandler implements Thread.UncaughtExceptionHandler {

    public abstract boolean handleException(Throwable throwable);
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if(handleException(throwable)){
            try {
                Thread.sleep(3000);

            }
            catch (InterruptedException ex){
                ex.printStackTrace();

            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
}

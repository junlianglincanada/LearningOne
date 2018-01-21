package com.example.junlianglin.framework.exception;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.junlianglin.framework.utils.FileUtils;
import com.example.junlianglin.framework.utils.SystemUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by JunliangLin on 1/4/2018.
 */

public class LocalFileHandler extends BaseExceptionHandler {

    private Context context;

    @Override
    public boolean handleException(Throwable throwable) {
        if (throwable==null)
            return false;

        new Thread(){
            public void run(){
                Toast.makeText(context,"Application has crashed!",Toast.LENGTH_LONG);

            }
        }.start();
        saveLog(throwable);
        return true;
    }

    public LocalFileHandler(Context context) {
        this.context = context;
    }

    public void saveLog(Throwable throwable){
        try{
            File file = new File(FileUtils.getDiskCacheDir(context),"/log/crash.log");
            if (!file.exists())
                file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file,true);
            outputStream.write(SystemUtil.getSystemInfo().getBytes());
            PrintStream printStream = new PrintStream(outputStream);
            throwable.printStackTrace(printStream);
            printStream.flush();
            outputStream.flush();
            printStream.close();
            outputStream.close();

        }
        catch (Exception ex){
            ex.printStackTrace();

        }

    }


}

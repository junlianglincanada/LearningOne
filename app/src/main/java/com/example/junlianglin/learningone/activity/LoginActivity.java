package com.example.junlianglin.learningone.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import org.apache.commons.codec.binary.Base64;

import android.os.Environment;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;


import com.example.junlianglin.framework.activity.BaseActivity;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.db.SqliteDBHelper;
import com.example.junlianglin.learningone.utils.LoginResult;
import com.example.junlianglin.learningone.utils.Preferences;
import com.example.junlianglin.learningone.utils.ServerUrl;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


/**
 * A login screen that offers login via email/password.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity  {

    private SharedPreferences pref;

    @ViewInject(R.id.email)
    private EditText mEmail;
    @ViewInject(R.id.password)
    private EditText mPassword;

    @ViewInject(R.id.login_progress)
    private View mProgressView;

    @ViewInject(R.id.sign_in_button)
    private Button mSignInButton;

    private String serverDateFormatPattern = "EE, dd MMM yyyy HH:mm:ss 'GMT'";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        File fileDir = this.getApplicationContext().getFilesDir();
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {

            fileDir = Environment.getExternalStorageDirectory();
        }

        Preferences preferences = new Preferences();

        if (preferences.isTokenValid()){
            String token = preferences.getAccessToken();
            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
            intent.putExtra("token",token);

            startActivity(intent);
        }
        preferences = null;



        try {
            SqliteDBHelper mDbHelper = new SqliteDBHelper(this.getBaseContext());
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("token", "dddddzxcvz");
            long newRowId = db.insert(SqliteDBHelper.TABLE_T_USER, null, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteStudioService.instance().start(this);










    }

    @Override
    protected void initParams() {
        mEmail.setText("jun@webwizards.ca");
        mPassword.setText("222432");

    }


    @Event(value = {R.id.sign_in_button},type = Button.OnClickListener.class)
    private void onClick(View view) {
        System.out.println("click....");
        attemptLogin();

    }








    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


        // Store values at the time of the login attempt.
        String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();



        showProgress(true);

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                try{
                    Thread.sleep(1000);
                    RequestParams requestParams = new RequestParams(ServerUrl.REMOTEURL + ":" + ServerUrl.REMOTEPORT + ServerUrl.LOGIN);

                    String encodedText = "";

                    byte[] encoded = Base64.encodeBase64( (mEmail.getText()+ ":" + mPassword.getText()).getBytes("UTF-8"));
                    encodedText = new String(encoded);


                    //System.out.println("encodedText=" + encodedText);
                    requestParams.addHeader("Authorization","Basic " + encodedText);

                    x.http().post(requestParams, new Callback.CommonCallback<LoginResult>() {

                        @Override
                        public void onSuccess(LoginResult result) {
                        }
                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                        }
                        @Override
                        public void onCancelled(CancelledException cex) {
                        }
                        @Override
                        public void onFinished() {


                            Preferences preferences = new Preferences();

                            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                            intent.putExtra("token",preferences.getAccessToken());

                            startActivity(intent);

                        }

                    });

                }
                catch (Exception ex){
                }

                return null;
            }
            @Override
            protected void  onPostExecute(Void result){
                super.onPostExecute(result);
                System.out.println("onPostExecute");



            }
        }.execute();

    }



    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);



            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        SQLiteStudioService.instance().stop();
        super.onDestroy();
    }









    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */

}


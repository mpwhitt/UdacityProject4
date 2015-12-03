package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.graingersoftware.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.graingersoftware.androidlibrary.LibraryActivity;

import junit.framework.Assert;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by graingersoftware on 11/29/15.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String>
{
    private static MyApi myApiService = null;
    private Context mContext;
    private EndpointsAsyncTaskListener mListener = null;
    private ProgressDialog mDialog;
    private String mResult;

    public EndpointsAsyncTask(Context context){
        mContext = context;
        mDialog = new ProgressDialog(mContext);
    }

    public EndpointsAsyncTask(Context context, GetEndpointsAsyncTaskListener listener)
    {
        mListener = listener;
        mContext = context;
    }

   @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        if (null != mDialog)
        {
            mDialog.setMessage("Loading awesome joke...");
            mDialog.show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(mContext.getString(R.string.gce_endpoint_url));
            myApiService = builder.build();
        }
        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }


    }

    @Override
    protected void onPostExecute(String result) {
        mResult = result;
        if (null != mListener && null != result && !result.equals(""))
        {
            if (!result.equals(""))
            {
                mListener.onSuccess();
            }
            else
            {
                mListener.onFail();
            }
        }
        else
        {
            if (null != mDialog)
            {
                mDialog.dismiss();
            }
            startJokeActivity();
        }
    }

    private void startJokeActivity()
    {
        Intent libraryIntent = new Intent(mContext, LibraryActivity.class);
        libraryIntent.putExtra(LibraryActivity.JOKE_EXTRA_STRING, mResult);
        mContext.startActivity(libraryIntent);
    }

    public interface EndpointsAsyncTaskListener {
        void onSuccess();
        void onFail();

    }

    public static class CallbackListener implements EndpointsAsyncTaskListener{
        private CountDownLatch signal;

        CallbackListener(CountDownLatch signal){
            this.signal = signal;
        }

        public void onSuccess() {
            signal.countDown();
        }

        public void onFail() {
            signal.countDown();
        }
    }

    public static class GetEndpointsAsyncTaskListener extends CallbackListener{

        GetEndpointsAsyncTaskListener(CountDownLatch signal){
            super(signal);
        }

        public void onSuccess() {
            Assert.assertTrue(true);
            super.onSuccess();

        }

        public void onFail() {
            Assert.assertTrue(false);
            super.onFail();

        }
    }
}
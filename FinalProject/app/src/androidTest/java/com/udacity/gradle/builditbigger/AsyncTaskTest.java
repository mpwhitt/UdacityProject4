package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncTaskTest extends InstrumentationTestCase
{
    //Information gathered from the following links
    //http://marksunghunpark.blogspot.com/2015/05/how-to-test-asynctask-in-android.html
    //https://gist.github.com/nalitzis/2667108

    public void testAsyncTask() throws Throwable{

        final Context context = getInstrumentation().getTargetContext();
        final CountDownLatch signal = new CountDownLatch(1);
        final EndpointsAsyncTask wEndpointsAsyncTask =
                new EndpointsAsyncTask(context, new EndpointsAsyncTask.
                        GetEndpointsAsyncTaskListener(signal));

        runTestOnUiThread(new Runnable() {
            public void run() {
                wEndpointsAsyncTask.execute();
            }
        });

        signal.await(30, TimeUnit.SECONDS);

    }
}

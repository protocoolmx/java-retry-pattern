package cool.proto.retry.example;

import cool.proto.retry.*;

import java.util.logging.Logger;

public class AsyncExample implements RetryAsyncTaskCallback, RetryCriteriaCallback, RetryEventListener {

    private Retry retry;
    private int counter = 1;

    public static final String TAG = AsyncExample.class.getName();
    private static final Logger LOGGER = Logger.getLogger(TAG);

    public AsyncExample() {
        retry = new Retry();
        retry.registerRetryTask(this);
        retry.registerRetryCriteriaCallback(this);
        retry.registerRetryEventListener(this);

        retry.exec();
    }

    public static void main(String[] args) {
        new AsyncExample();
    }

    @Override
    public void retryTask(final RetryTaskCompleteCallback taskCompleteCallback) {
        LOGGER.info("retryTask()");

        try {
            Thread.sleep(1000);

            counter++;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    taskCompleteCallback.taskCompleted();
                }
            }).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean retryCriteria() {
        LOGGER.info("retryCriteria()");

        return counter == 3;
    }

    @Override
    public void onRetryFailed() {
        LOGGER.info("onRetryFailed()");

        retry.exec();
    }

    @Override
    public void onRetrySucceed() {
        LOGGER.info("onRetrySucceed()");
    }
}

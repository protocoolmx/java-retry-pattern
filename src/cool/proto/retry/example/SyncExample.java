package cool.proto.retry.example;

import cool.proto.retry.Retry;
import cool.proto.retry.RetryCriteriaCallback;
import cool.proto.retry.RetryEventListener;
import cool.proto.retry.RetrySyncTaskCallback;

import java.util.logging.Logger;

public class SyncExample implements RetrySyncTaskCallback, RetryCriteriaCallback, RetryEventListener {

    private Retry retry;
    private int counter = 1;

    public static final String TAG = SyncExample.class.getName();
    private static final Logger LOGGER = Logger.getLogger(TAG);

    public SyncExample() {
        retry = new Retry();
        retry.registerRetryTask(this);
        retry.registerRetryCriteriaCallback(this);
        retry.registerRetryEventListener(this);

        retry.exec();
    }

    public static void main(String[] args) {
        new SyncExample();
    }

    @Override
    public void retryTask() {
        LOGGER.info("retryTask()");

        try {
            Thread.sleep(1000);

            counter++;
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

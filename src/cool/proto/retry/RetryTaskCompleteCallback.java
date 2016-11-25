package cool.proto.retry;

/**
 * Listener for when retry tasks have finished.
 */
public interface RetryTaskCompleteCallback {

    /**
     * Will be automatically executed after {@link RetrySyncTaskCallback#retryTask()},
     * when using {@link RetryAsyncTaskCallback} it is passed as argument to let user
     * call it.
     */
    void taskCompleted();
}
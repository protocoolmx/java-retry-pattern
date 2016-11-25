package cool.proto.retry;

/**
 * This retry task is used when we want to let the user
 * decided when we are done, usually when we want to run
 * asynchronous tasks.
 */
public interface RetryAsyncTaskCallback extends RetryTaskCallback  {

    /**
     * The same as {@link RetrySyncTaskCallback#retryTask()} but with the difference
     * that we pass an instance of {@link RetryTaskCompleteCallback}.
     *
     * @param taskCompleteCallback needs to be executed when done with this task
     *                             to continue with the process.
     */
    void retryTask(RetryTaskCompleteCallback taskCompleteCallback);
}

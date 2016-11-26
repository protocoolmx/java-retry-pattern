package cool.proto.retry;

/**
 * Builds a Retry instance with callbacks registered.
 */
public class RetryBuilder {

    private Retry retry;

    private RetryEventListener retryEventListener;
    private RetryCriteriaCallback retryCriteriaCallback;

    public RetryBuilder() {
        // Make `retry` instance available here to register
        // `RetrySyncTaskCallback` instances.
        retry = new Retry();
    }

    /**
     * Registers {@link RetrySyncTaskCallback} on {@link Retry}
     *
     * @param retrySyncTaskCallback Synchronous retry task instance.
     * @return retry builder instance
     */
    public RetryBuilder task(RetrySyncTaskCallback retrySyncTaskCallback){
        retry.registerRetryTask(retrySyncTaskCallback);

        return this;
    }

    /**
     * Registers {@link RetryAsyncTaskCallback} on {@link Retry}
     *
     * @param retryAsyncTaskCallback Asynchronous retry task instance.
     * @return retry builder instance
     */
    public RetryBuilder task(RetryAsyncTaskCallback retryAsyncTaskCallback){
        retry.registerRetryTask(retryAsyncTaskCallback);

        return this;
    }

    /**
     * Registers {@link RetryCriteriaCallback} on {@link Retry}
     *
     * @param retryCriteriaCallback Retry criteria callback instance.
     * @return retry builder instance
     */
    public RetryBuilder criteria(RetryCriteriaCallback retryCriteriaCallback) {
        this.retryCriteriaCallback = retryCriteriaCallback;

        return this;
    }

    /**
     * Registers {@link RetryEventListener} on {@link Retry}
     *
     * @param retryEventListener Retry event listener instance.
     * @return retry builder instance
     */
    public RetryBuilder events(RetryEventListener retryEventListener) {
        this.retryEventListener = retryEventListener;

        return this;
    }

    /**
     * Returns {@link Retry} with callbacks and interfaces ready.
     *
     * @return instance of {@link Retry} build.
     */
    public Retry build() {

        retry.registerRetryCriteriaCallback(retryCriteriaCallback);
        retry.registerRetryEventListener(retryEventListener);

        return retry;
    }

}

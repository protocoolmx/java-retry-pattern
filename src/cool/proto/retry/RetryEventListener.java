package cool.proto.retry;

/**
 * Listener for retry events
 */
public interface RetryEventListener {

    /**
     * Will be executed when {@link RetryCriteriaCallback#retryCriteria()} returns false.
     */
    void onRetryFailed();

    /**
     * Will be executed when {@link RetryCriteriaCallback#retryCriteria()} returns true.
     */
    void onRetrySucceed();

}

package cool.proto.retry;

/**
 * Used to decide which method of {@link RetryEventListener} to call.
 */
public interface RetryCriteriaCallback {

    /**
     * @return if <code>true</code> internally calls {@link RetryEventListener#onRetrySucceed()}
     * otherwise {@link RetryEventListener#onRetryFailed()}.
     */
    boolean retryCriteria();
}

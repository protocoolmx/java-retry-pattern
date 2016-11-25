package cool.proto.retry;

/**
 * Runner for synchronous and asynchronous tasks.
 *
 * @implNote Whether the task is of the form synchronous
 * or asynchronous they will be executed in background.
 */
public class RetryTaskRunner implements Runnable {

    private RetryTaskCallback retryTaskCallback;
    private RetryTaskCompleteCallback taskCompleteCallback;

    /**
     * @param retryTaskCallback retry task interface instance
     */
    public RetryTaskRunner(RetryTaskCallback retryTaskCallback) {
        this.retryTaskCallback = retryTaskCallback;
    }

    /**
     * Set instance of {@link RetryTaskCompleteCallback}
     *
     * @param taskCompleteCallback listener for task completion.
     */
    public void registerTaskCompleteCallback(RetryTaskCompleteCallback taskCompleteCallback) {
        this.taskCompleteCallback = taskCompleteCallback;
    }

    /**
     * @return <code>true</code> if {@link this#retryTaskCallback} if of type
     * {@link RetryAsyncTaskCallback}, <code>false</code> otherwise.
     */
    private boolean isRetryAsyncTaskCallback() {
        return this.retryTaskCallback instanceof RetryAsyncTaskCallback;
    }

    @Override
    public void run() {
        if (isRetryAsyncTaskCallback()) {
            // Call `retryTask()` with `taskCompleteCallback` to let user
            // indicate when to proceed.
            ((RetryAsyncTaskCallback) retryTaskCallback).retryTask(taskCompleteCallback);
        } else {
            // Execute `retryTask()` and when finished call `taskCompleted()`.
            try {
                ((RetrySyncTaskCallback) retryTaskCallback).retryTask();
            } finally {
                taskCompleteCallback.taskCompleted();
            }
        }
    }

}

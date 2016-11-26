# java-retry-pattern
Collection of interfaces to build the Retry Pattern.

## How to implement it?
 
 You can find an example for `async` and `sync` tasks inside [`cool.proto.retry.example`](src/cool/proto/retry/example) package.
 
## Usage

### Implement RetryTaskSyncCallback || RetryTaskAsyncCallback

#### `#retryTask()`

This is where you will put the code to run in background.

```java
public class SomeClass implements RetrySyncTaskCallback {

    @Override
    public void retryTask() {
      // do something in background here!
    }
}
```

#### `#retryTask(RetryTaskCompleteCallback)`

Exactly like `#retryTask()` but the user has to specify when to finish to continue the process.

```java
public class SomeClass implements RetryAsyncTaskCallback {

    @Override
    public void retryTask(RetryTaskCompleteCallback taskCompleteCallback) {
      // do something in background here!
      
      // Call `taskCompleteCallback.taskCompleted()` when you are done!
      taskCompleteCallback.taskCompleted();
    }
}
```

### Implement RetryCriteriaCallback

#### `#retryCriteria()`

Put the logic in here to decide which `RetryEventListener` method to call, `onRetryFailed()` or `onRetrySucceed()`.

```java
public class SomeClass implements RetryCriteriaCallback {

    @Override
    public boolean retryCriteria() {
      // put your retry decision logic here!
    }
}
```

### Implement RetryEventListener

Listen for `onRetryFailed` and `onRetrySucceed` events, one of this two methods will get triggered once the `retryTask()` method execution has finished.

If `#retryCriteria()`returns `false` `#onRetryFailed()` will be execute, otherwise `#onRetrySucceed()`.

```java
public class SomeClass implements RetryEventListener {

    @Override
    public void onRetryFailed() { }

    @Override
    public void onRetrySucceed() {}
}
```

 
## Licence
 
 [MIT](LICENSE)

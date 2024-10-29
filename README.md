# Manual Retry for Temporal Activities

## Running sample

Start the worker in one terminal;

```
./gradlew bootRun
```

Invoke a workflow in another terminal;

```
temporal workflow start --type MyWorkflow --task-queue example-queue --workflow-id example
```

`MyActivity` will randomly throw a non-retryable exception 50% of the time. The workflow will then wait up to 30 seconds for a signal to retry the workflow.


To trigger a retry of the failed activity;

```
temporal workflow signal --workflow-id example --name retry
```

To fail the workflow task, either wait for the 30 second timeout, or signal the activity to fail;

```
temporal workflow signal --workflow-id example --name fail
```
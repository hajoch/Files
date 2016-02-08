public class Executors {

	public static void executors() {

		/**
		* Simple executor with single thread
		* The difference from doing this manually is that this one
		* never stops. Executors have to be stopped explicitly -
		* otherwise they keep listening for new tasks.
		**/
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			String threadName = Thread.concurrentThread().getName();
			System.out.println("hello, I'm "+threadName);
		});

		//shutting down the executors softly
		try {
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedExection e) {
			e.printStackTrace();
		} finally {
			if(!executor.isTerminated())
				System.out.println("Now shutting down unfinished tasks");
			executor.shutdownNow();
		}

		/**
		* ---------------------------
		* TYPES OF EXECUTOR FACTORIES
		* ---------------------------
		**/

		/**
		*  Executor service with a single thread executor
		**/
		executor = Executors.newSingleThreadExecutor();

		/**
		* Executor service backed by a thread-pool of size of the argument
		* passed
		**/
		executor = Executors.newFixedThreadPool(2);

		/**
		* Of the type ForkJoinPool, which means:
		* Instead of using a fixed size thread-pool ForkJoinPools are
		* created for a given parallelism size which per default is the
		* number of available cores of the hosts CPU.
		**/
		executor = Executors.newWorkStealingPool();

	}

	public static void callablesAndFutures() {

		/**
		* In additions to Runnable theres another kind of task:
		* Callable, the difference is that it has a return value,
		* instead of void (like Runnable has).
		**/
		Callable<Integer> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				return 1829;
			} catch (InterruptedExection e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		/**
		* To retrieve the returned value from the callable
		* we must use a Future
		**/
		ExecutorService executor = Executor.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);

		//Check if the execution is finished
		if(future.isDone())
			System.out.println("Yeey");

		//Block the current thread to wait for the callable to complete
		Integer result = future.get();


		/**
		* In the worst case a callable runs forever - thus making your
		* application unresponsive. You can simply counteract those
		* scenarios by passing a timeout when getting the results
		**/
		Future<Integer> future = executor.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				return 1829;
			} catch (InterruptedExection e) {
				throw new IllegalStateException("task interrupted", e);
			}
		});

		// This will result in a TimeoutExecption since we timeout after
		// one second, while the task takes 2 seconds to complete.
		future.get(1, TimeUnit.SECONDS);

		/**
		* Call multiple Callables with invokeAll()
		* Here the string will be printed from all the callables
		**/
		ExecutorService executor = Executors.newWorkStealingPool();
		List<Callable<String>> callables = Arrays.asList(
			()->"task1", ()->"task2", ()->"task3");

		executor.invokeAll(callables)
			.stream()
			.map(future -> {
				try {
					return future.get();
				}
				catch (Exception e) {
					throw new IllegalStateException(e);
				}
			})
			.forEach(System.out::println);


		/**
		* You can also get the return value of the firsts one to complete
		* by using invokeAny.
		* This blocks the current thread until it gets a result.
		**/
		String result = executor.invokeAny(callables);
	}


	/**
	* In order to periodically run common tasks multiple times,
	* we can utilize scheduled thread pools
	**/
	public static void scheduledExecutors() {

		/**
		* This will schedule the task to run after an initial delay
		**/
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> System.out.println("Coolbeans");
		ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

		//We can also get the remaining time
		long remaining = future.getDelay(TimeUnit.MILLISECONDS);

		/**
		* In order to schedule tasks to be executed periodically,
		* executors provide the two methods scheduleAtFixedRate()
		* and scheduleWithFixedDelay()
		**/

		//This capable of executing tasks with a fixed time rate
		int initialDelay = 0, period = 5;
		executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

		/**
		* scheduleAtFixedRate() doesn't take into account the actual
		* duration of the task.
		* However scheduleWithFixedDelay() does. It will start the wait-
		* period after the previous run has ended.
		**/
		executor.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.SECONDS);
	}
}

//Source: http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
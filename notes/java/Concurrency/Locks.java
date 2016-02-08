/**
* Instead of using implicit locking via the synchronized keyword the
* Concurrency API supports various explicit locks specified by the Lock
* interface. Locks support various methods for finer grained lock control
* thus are more expressive than implicit monitors
**/
public class Locks {

	/**
	* A lock is acquired via lock() and released via unlock(). It's
	* important to wrap your code into a try/finally block to ensure
	* unlocking in case of exceptions
	**/

	//mutual exclusion lock that implements reentrant characteristics
	ReentrantLock lock = new ReentrantLock();
	int count1 = 0;
	void increment() {
		lock.lock();
		try {
			count1++;
		} finally {
			lock.unlock();
		}
	}

	/**
	* Threads can call various methods for greater control over the resource
	**/
	boolean locked = lock.isLocked();
	boolean mine = lock.isHeldByCurrentThread();

	//alternative to lock(), if true the lock was set by current thread.
	boolean lockAquired = lock.tryLock();


	/**
	* ReadWriteLock specifies another type of lock maintaining a pair
	* of locks for read and write access
	* The same principle just with lock.readLock().* is used when reading.
	**/
	ExecutorService executor = Executors.newFixedThreadPool(2);
	Map<String, String> map = new HashMap<>();
	ReadWriteLock lock2 = new ReentrantReadWriteLock();

	executor.submit(() -> {
		lock2.writeLock().lock();
		try {
			TimeUnit.SECONDS.sleep(1);
			map.put("javel", "sedet");
		} finally {
			lock2.writeLock().unlock();
		}
	});

	/**
	* StampedLock
	* StampedLock return a stamp represented by a long value. You can use
	* these stamps to either release a lock or to check if the lock is still
	* valid
	**/
	StampedLock lock3 = new StampedLock();
	executor.submit(() -> {
		long stamp = lock.writeLock();
		try {
			TimeUnit.SECONDS.sleep(1);
			map.put("javel", "sedet");
		} finally {
			lock.unlockWrite(stamp);
		}
	});

	//A read lock can also be converted to a writeLock
	long stamp = lock.readLock();
	stamp = lock.tryConvertToWriteLock(stamp);
	//the lock-change was successfull if(stamp != 0L)


	//TODO SEMAPHORES!





	/**
	* Bonus:
	* In addition to the normal synchronized method,
	* you can also use the keyword in a block statement.
	**/
	int count = 0;
	void incrementSync() {
		synchronized (this) {
			count = count+1;
		}
	}

}
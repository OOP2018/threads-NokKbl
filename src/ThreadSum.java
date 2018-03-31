import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSum {
	
	public static void main(String[] args) {
		// upper limit of numbers to add/subtract to Counter
		final int LIMIT = 10000000;
		// The counter that accumulates a total.
		Counter counter = new Counter();
		//Counter counter = new CounterWithLock();
		//Counter counter = new SynchronousCounter();
		//Counter counter = new AtomicCounter();
		int poolsize = 5;
		
		runThreads(poolsize, counter, LIMIT);
	}
	
	/** Run nthread adders and nthread subtracters. */
	public static void runThreads(int nthread, Counter counter, final int limit) {
		ExecutorService executor = Executors.newFixedThreadPool(2 * nthread);
		System.out.println("Starting tasks");
		long startTime = System.nanoTime();
		
		for(int k=1; k<=nthread; k++) {
			Runnable addtask = new Runnable() {
				@Override
				public void run() {
					for (int k = 1; k <= limit; k++) counter.add(k);
					System.out.println("Done " + Thread.currentThread().getName() + " [Add]");
				}
			};
			Runnable subtask = new Runnable() {
				@Override
				public void run() {
					for (int k = 1; k <= limit; k++) counter.add(-k);
					System.out.println("Done " + Thread.currentThread().getName() + " [Sub]");
				}
			};
			executor.submit(addtask);
			executor.submit(subtask);
		}
		
		try {
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println("All down");
		} catch(InterruptedException e) {
			System.out.println("Threads interrupted");
		}
		
		double elapsed = 1.0E-9*(System.nanoTime() - startTime);
		System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);
		
		// the sum should be 0.
		System.out.printf("Counter total is %d\n", counter.get());
	}
	
}

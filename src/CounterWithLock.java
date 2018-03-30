import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * An accumulator for a sum by using ReentrantLock.
 * @author Kunyaruk Katebunlu
 */
public class CounterWithLock extends Counter{
	private Lock lock = new ReentrantLock();
	
	/**
	 * Add an amount to the total.
	 */
	@Override
	public void add(int amount) {
		try {
			lock.lock();
			super.add(amount);
		} finally {
			lock.unlock();
		}
	}
	
}

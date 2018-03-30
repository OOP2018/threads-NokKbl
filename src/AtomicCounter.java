import java.util.concurrent.atomic.AtomicLong;

/**
 * An accumulator for a sum by using AtomicLong.
 * @author Kunyaruk Katebunlu
 */
public class AtomicCounter extends Counter{
	private AtomicLong total;
	
	public AtomicCounter() {
		total = new AtomicLong();
	}
	
	/**
	 * Add an amount to the total.
	 */
	@Override
	public void add(int amount) {
		total.getAndAdd(amount);
	}
	
	/**
	 * Get the total value of counter.
	 */
	@Override
	public long get() {
		return total.longValue();
	}
	
}

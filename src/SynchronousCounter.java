/**
 * An accumulator for a sum by using synchronized.
 * @author Kunyaruk Katebunlu
 */
public class SynchronousCounter extends Counter{
	
	/**
	 * Add an amount to the total.
	 */
	@Override
	public synchronized void add(int amount) {
		total += amount;
	}
	
}

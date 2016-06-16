package js.junit.model;

public class DumbCounter implements Counter {
	private int counter;

	@Override
	public int incrementAndGet() {
		return ++counter;
	}
}

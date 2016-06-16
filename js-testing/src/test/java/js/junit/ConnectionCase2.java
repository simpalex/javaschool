package js.junit;

import js.junit.model.ConnectionHolder;
import org.junit.Assert;
import org.junit.Test;

public class ConnectionCase2 {

	@Test
	public void testConnection() {
		Assert.assertTrue(ConnectionHolder.getConnection().getUrl().equals("url1"));
	}
}

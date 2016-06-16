package js.junit.model;

import java.util.concurrent.TimeUnit;

public class Connection {


	private boolean active;
	private String url;
	
	public boolean establishConnection(String url, Integer  time) {
		if (time != null) {
			try {
				TimeUnit.SECONDS.sleep(time);
				if (url != null) {
					this.url = url;
				} else {
					throw new IllegalArgumentException("Connection must be set");
				}
				this.active = true;
				System.out.println("Connection to: " +url+ " established");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void closeConnection() {
		System.out.println("Connection to: " +url+ " close...");
		active = false;
		url = null;
	}
	
	public boolean getRealConnectionState(){
		return active;
	}
	
	public boolean getRealConnectionStateByPrivateMethod(){
		return getConnectionState();
	}
	
	private boolean getConnectionState(){
		return active;
	}

}

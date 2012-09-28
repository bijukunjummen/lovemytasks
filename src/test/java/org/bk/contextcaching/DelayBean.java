package org.bk.contextcaching;

public class DelayBean {
	public DelayBean(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

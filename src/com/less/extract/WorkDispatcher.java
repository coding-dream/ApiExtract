package com.less.extract;

public class WorkDispatcher implements Dispatcher{

	@Override
	public void dipatch(Task task) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				task.execute();
			}
		}).start();
		
		
	}
	
}

package com.less.extract;

public class App {
	
	public static void main(String[] args) {
		
		Task task = TaskFactory.create(ZhihuTask.class);
		task.add(new Step() {
			
		})
		.add(new Step() {
			
		})
		
		Dispatcher dispatcher = new WorkDispatcher();
		dispatcher.dipatch(new ZhihuTask());
	}
}

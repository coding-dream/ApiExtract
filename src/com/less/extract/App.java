package com.less.extract;

public class App {
	
	public static void main(String[] args) {
		
		Task task = TaskFactory.create(ZhihuTask.class);
		
		Dispatcher dispatcher = new WorkDispatcher();
		dispatcher.dipatch(new ZhihuTask());
	}
}

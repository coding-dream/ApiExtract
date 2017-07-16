package com.less.extract;

public class TaskFactory {
	
	public static Task create(Class clazz){
		try {
			Task task;
			task = (Task) clazz.newInstance();
			return task;
		} catch (Exception e) {
			throw new NoClassDefFoundError();
		}
	}
}

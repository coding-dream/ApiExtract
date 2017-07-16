package com.less.extract;

import java.util.ArrayDeque;

public class ZhihuTask implements Task {
	private ArrayDeque<Step> mTasks = new ArrayDeque<Step>();
	
	@Override
	public void execute(Runnable runnable) {
		
	}
	
	public static Task create() {
		return new ZhihuTask();
	}

	@Override
	public Task add(Step step) {
		mTasks.add(step);
		return this;
	}
	
}

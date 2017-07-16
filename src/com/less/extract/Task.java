package com.less.extract;

public interface Task {
	void execute(Runnable runnable);
	Task add(Step step);
}

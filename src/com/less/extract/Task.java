package com.less.extract;

import okhttp3.Request;

public abstract class Task {
	abstract void execute();
	
	abstract void StepOne(Request request);
	abstract void StepTwo(Request request);
	abstract void StepTree(Request request);
	abstract void StepFour(Request request);
	abstract void StepFive(Request request);
	
}

package com.less.extract;

public abstract class Task <T>{
	
	abstract void execute();
	
	abstract void StepOne(T param);
	abstract void StepTwo(T param);
	abstract void StepTree(T param);
	abstract void StepFour(T param);
	
}

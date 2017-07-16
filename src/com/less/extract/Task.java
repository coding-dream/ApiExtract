package com.less.extract;

public abstract class Task {
	void execute(){
		StepOne();
		StepTwo();
		StepTree();
		StepFour();
		StepFive();
	}

	abstract void StepOne();
	abstract void StepTwo();
	abstract void StepTree();
	abstract void StepFour();
	abstract void StepFive();
	
}

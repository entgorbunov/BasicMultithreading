package com.balazsholczer.udemy;

class RunnerThread1 extends Thread {
	
	@Override
	public void run() {
		for(int i=0;i<100;++i){
			System.out.println("Runner1: "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class RunnerThread2 extends Thread {
	
	@Override
	public void run() {
		for(int i=0;i<100;++i){
			System.out.println("Runner2: "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class App {

	public static void main(String[] args) {
		
//		Thread t1 = new Thread(new Runner1());
//		Thread t2 = new Thread(new Runner2());
		
		RunnerThread1 t1 = new RunnerThread1();
		RunnerThread2 t2 = new RunnerThread2();
		
		t1.start();
		t2.start();
	}
}



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class App {

	private static int counter = 0;
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void increment(){

        lock.lock();
        counter++;
		lock.unlock();
	}

	public static void incrementWithCondition() {
		if (!lock.isLocked()) {
			System.out.println("Lock is free, attempting to lock and increment.");
			lock.lock();
			try {
				counter++;
			} finally {
				lock.unlock();
			}
		} else {
			System.out.println("Lock is busy, increment will be delayed.");
		}
	}


	public static void firstThread(){
		for(int i=0;i<1000;i++){
			increment();
		}
	}
	
	public static void secondThread(){
		for(int i=0;i<1000;i++){
			increment();
		}
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				firstThread();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				firstThread();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(counter);
		
	}
}


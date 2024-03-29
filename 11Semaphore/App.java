

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * 
 * 	- semaphore maintains a set of permits
 *	- acquire() -> if a permit is available then takes it
 *	- release() -> adds a permit
 *		
 *		 Semaphore just keeps a count of the number available
 *		new Semaphore(int permits, boolean fair) !!!
 */

enum Downloader {
	
	INSTANCE;
	
	private Semaphore semaphore = new Semaphore(5, true);
	
	public void downloadData() {
		
		try {
			semaphore.acquire();
			download();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
		
	}

	private void download() {
		System.out.println("Downloading data from the web...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class App {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, 12).<Runnable>mapToObj(i -> Downloader.INSTANCE::downloadData).forEach(executorService::execute);
		
	}
}
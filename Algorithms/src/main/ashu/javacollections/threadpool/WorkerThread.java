package main.ashu.javacollections.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorkerThread extends Thread{
	private final LinkedBlockingQueue<Runnable> taskQueue;
	private AtomicBoolean isThreadPoolShutDownInitiated;
	
	public WorkerThread(LinkedBlockingQueue<Runnable> taskQueue,
			AtomicBoolean isThreadPoolShutDownInitiated) {
		super();
		this.taskQueue = taskQueue;
		this.isThreadPoolShutDownInitiated = isThreadPoolShutDownInitiated;
	}
	
	public void run() {
		try {
			// continue until all tasks finished processing
			while (!isThreadPoolShutDownInitiated.get() || !taskQueue.isEmpty()) {
				Runnable r;
				// Poll a runnable from the queue and execute it
				while ((r = taskQueue.poll()) != null) {
					r.run();
				}
				 // Sleep in case there wasn't any runnable in the queue. This helps to avoid hogging the CPU.
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			throw new CustomThreadPoolException(e);
		}
	}
	
}

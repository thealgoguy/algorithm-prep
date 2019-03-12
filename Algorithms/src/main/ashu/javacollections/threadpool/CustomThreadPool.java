package main.ashu.javacollections.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

//https://caffinc.github.io/2016/03/simple-threadpool/
//https://www.jeejava.com/custom-thread-pool-in-java/
//questions - how does jaav thread pool wraps runnable  into future ?
//implement shutDown() and shutDownNow()
public class CustomThreadPool {
	
	private final List<WorkerThread> threads;      //list of active threads in the pool
	private final LinkedBlockingQueue<Runnable> taskQueue;   //a queue to hold excess tasks for which no thread is immediately available
	private final int noOfThreads;  //requested no of threads in the pool
	private final int maxNoOfTasks;    //or capacity of the pool to hold tasks(in the blocking queue)
	private AtomicBoolean isThreadPoolShutDownInitiated;

	public CustomThreadPool(int noOfThreads, int maxNoOfTasks) {
		this.noOfThreads = noOfThreads;
		this.maxNoOfTasks = maxNoOfTasks;
		threads = new ArrayList<WorkerThread>();  
		this.taskQueue = new LinkedBlockingQueue(maxNoOfTasks);
		this.isThreadPoolShutDownInitiated = new AtomicBoolean(false);
		//create requested no of threads for each task and add to the queue
		for(int i=0; i<noOfThreads; i++) {
			//passing queue to each worker thread
			WorkerThread newThread = new WorkerThread(taskQueue, isThreadPoolShutDownInitiated); 
			threads.add(newThread);
			newThread.start();
		}
	}	
	// called by Handler/application thread for submitting runnable tasks in the pool
	//we'd need to synchronize the method if not using blocking queue
	public void execute(Runnable task) throws InterruptedException{
		//check if pool is alive
		if(isThreadPoolShutDownInitiated.get()) {
			throw new IllegalStateException("Thread Pool shutdown is initiated, unable to execute task");
		}
		this.taskQueue.add(task);  //add the task to the queue....Producer concept of PC
	}
	
	/**
     * Clears the queue of runnables and stops the threadpool. 
     * Any runnables currently executing will continue to execute.
     */
    public void terminate() {
        taskQueue.clear();
        stop();
    }
	

    // Stops addition of new runnables to the threadpool and 
    //terminates the pool once all runnables in the queue are executed.
	//we'd need to synchronize the method if not using atomic variable
	public  void stop() {
		System.out.println("Terminating the thread pool");
		this.isThreadPoolShutDownInitiated.set(true);
	}
	

    /**
     * Awaits up to <b>timeout</b> ms the termination of the threads in the threadpool
     *
     * @param timeout Timeout in milliseconds
     * @throws TimeoutException      Thrown if the termination takes longer than the timeout
     * @throws IllegalStateException Thrown if the stop() or terminate() methods haven't been called before awaiting
     */
    public void awaitTermination(long timeout) throws TimeoutException {
        if (this.isThreadPoolShutDownInitiated.get()) {
            throw new IllegalStateException("Threadpool not terminated before awaiting termination");
        }
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= timeout) {
            boolean flag = true;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new CustomThreadPoolException(e);
            }
        }
        throw new TimeoutException("Unable to terminate threadpool within the specified timeout (" + timeout + "ms)");
    }

    /**
     * Awaits the termination of the threads in the threadpool indefinitely
     *
     * @throws IllegalStateException Thrown if the stop() or terminate() methods haven't been called before awaiting
     */
    public void awaitTermination() throws TimeoutException {
        if (this.isThreadPoolShutDownInitiated.get()) {
            throw new IllegalStateException("Threadpool not terminated before awaiting termination");
        }
        while (true) {
            boolean flag = true;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new CustomThreadPoolException(e);
            }
        }
    }

}

package main.ashu.javacollections.threadpool;

public class CustomThreadPoolException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomThreadPoolException(Throwable t) {
		super(t);
	}
}

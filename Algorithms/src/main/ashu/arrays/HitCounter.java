package main.ashu.arrays;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class HitCounter {
    private class Hit {
    	public long time;
    	public long count;
    	
    	public Hit() {
    		this.time = (long)(System.currentTimeMillis()/1E3);
    		this.count = 1;
    	}
    }
    
    private static final int MAX_SIZE = 60*60*24;
    private long lastHitTime = 0;
    private  Deque<Hit> dq;
    private  long lastMinuteCount = 0; 
    
    public HitCounter() {
    	this.lastHitTime = (long)(System.currentTimeMillis()/1E3);
    	this.lastMinuteCount = 0;
    	dq = new ArrayDeque<Hit>();
    }
    
    public synchronized void hit() {
    	System.out.print(" Last hit time : "+lastHitTime);
    	long currentTime = (long)(System.currentTimeMillis()/1E3);
    	synchronized(this) {
    		if(currentTime == lastHitTime) {  //next hit in the same second
        		Hit lastHit = dq.peekLast();
        		if(lastHit == null) {
        			lastHit = new Hit();
        			dq.offer(lastHit);
        		}
        		else lastHit.count++;     
        	}   	
        	else { 
        		//remove the entries that are outside the minute window
            	while(!dq.isEmpty() && currentTime - dq.peekFirst().time >MAX_SIZE) {
            		lastMinuteCount -= dq.peekFirst().count;   
            		dq.poll();
            	}
        		//first hit for this second
        		Hit newHit = new Hit();
        		dq.add(newHit);
        	}   
    		lastMinuteCount++;
        	lastHitTime = currentTime;
        	System.out.print(" LastDayHitCount = "+getLastMinuteCount()+"\n");
    	}
    }
    
    public long getLastMinuteCount() {
    	return lastMinuteCount;
    }
    
    public static void main(String[] args){
		HitCounter hc = new HitCounter();
		Random rd = new Random();
		for(int i=0;i<1E3;i++){
			System.out.print("i = " + i + ", ");
			hc.hit();
			try {
				Thread.sleep(rd.nextInt(100));//thread sleep a random time between [0,100] miliseconds 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
    
}

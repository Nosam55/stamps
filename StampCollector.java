/**
 * @(#)StampCollector.java
 *
 * StampCollector application
 *
 * @author 
 * @period 
 * @lab
 * @version 1.00 2016/5/5
 */
 import java.util.*;
 
public class StampCollector{
	
	private StampGUI gui;
	private List<StampEarner> earners;
	private long totalStamps;
	private Updater updater;
	private final Object LOCK = new Object();
	private boolean isChanged = false;
	
	public static void main(String[] args){
		new StampCollector();
	}
	
	public StampCollector(){
		totalStamps = 10l;
		earners = new ArrayList<StampEarner>();
		
		earners.add(new StampEarner(1,2d,10));
		earners.add(new StampEarner(5,1.75d,50));
		earners.add(new StampEarner(10,1.5d,100));
		earners.add(new StampEarner(15,1.5d,150));
		
		for(StampEarner e : earners){
			e.setGame(this);
		}
		
		
		gui = new StampGUI(earners);
		updater = new Updater(earners, this, gui);
		updater.start();
	}
	protected void setStamps(long x){
		totalStamps = x;
	}
	public long getTotal(){
		return totalStamps;
	}
	public Object getLock(){
		return LOCK;
	}
	public void update(){
		gui.redraw();
	}
	public void change(boolean b){
		isChanged = b;
	}
	public boolean isChanged(){
		return isChanged;
	}
}
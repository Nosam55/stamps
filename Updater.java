import java.util.*;
class Updater{
	private List<StampEarner> earners;
	private long total;
	private StampCollector game; 
	private StampGUI gui;
	
	public Updater(List<StampEarner> list, StampCollector c, StampGUI g){
		earners = list;
		total = 10l;
		game = c;
		gui = g;
		}
	
	


	private class TinyUpdater extends Thread{
		private StampEarner earner;
		public TinyUpdater(StampEarner e){
			earner = e;
		}
		public void run(){
			while(true){
				long time = System.currentTimeMillis();
				
				while(true){
				
				}
			}
			
		}
	}
}
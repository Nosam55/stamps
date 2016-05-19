import java.util.*;
class Updater extends Thread{
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
	
	public void update(){
		total = game.getTotal();
		for(StampEarner e : earners){
			if(e.isBought())
				total += (long)e.getStamps();
		}
		synchronized(game.getLock()){
			game.setStamps(total);
			System.out.println(total);
		}
		gui.getStampLabel().setText(Long.toString(total));
	}
	public void run(){
		while(true){
			long time = System.nanoTime();
			while(System.nanoTime() - time < 1000000000){
				
			}
			update();
		}
	}
}
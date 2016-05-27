import java.util.*;
class Updater{
	private List<Updatable> updatables;
	private long total;
	private StampCollector game; 
	private StampGUI gui;
	public Updater(List<Updatable> list, StampCollector c, StampGUI g){
		updatables = list;
		total = 10l;
		game = c;
		gui = g;
	}

	public void start(){
		for(Updatable e : updatables){
			new TinyUpdater(e).start();
		}
	}
	private class TinyUpdater extends Thread{
		private Updatable updatable;
		public TinyUpdater(Updatable u){
			updatable = u;
		}
		public void run(){
			while(true){
				updatable.update();
			}


		}
	}
}
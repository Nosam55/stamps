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
import java.io.*;
import javax.swing.UIManager;

public class StampCollector implements Savable{

	private StampGUI gui;
	private List<StampEarner> earners;
	private List<Updatable> updatables;
	private List<Savable> savables;
	private long totalStamps;
	private Updater updater;
	private boolean isChanged = false;
	private final String savePath = "C:/Program Files/Stamp Collector Simulator/save-game.txt";
	

	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}

		new StampCollector();
	}

	public StampCollector(){
		
		earners = new ArrayList<StampEarner>();
		updatables = new ArrayList<Updatable>();
	//	savables = new ArrayList<Savable>();
/*		String[] info = loadGame();
		if(info.length < 1){
			newStart();
		}
		else{
			process(info);
		}
*/
		newStart();
		for(StampEarner e : earners){
			e.setGame(this);
			updatables.add(e);
//			savables.add(e);
		}
		

		gui = new StampGUI(earners, this);
		updatables.add(gui);
		updater = new Updater(updatables, this, gui);
		updater.start();
//		savables.add(this);
	}
	private void newStart(){
		totalStamps = 10l;
		earners.removeAll(earners);
		earners.add(new StampEarner(1, 2d, 10, 500l, false));
		earners.add(new StampEarner(5, 1.75d, 50, 1000l, false));
		earners.add(new StampEarner(10, 1.5d, 100, 2000l, false));
		earners.add(new StampEarner(15, 1.5d, 150, 4000l, false));
	}
	protected synchronized void setStamps(long x){
		totalStamps = x;
	}
	public synchronized long getTotal(){
		return totalStamps;
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
	public String[] save(){
		String[] info = new String[2];
		info[0] = "Game";
		info[1] = Long.toString(getTotal());
		return info;
	}
	public static int lines(){
		return 2;
	}
	public void saveGame(){
		java.util.List<String> info = new ArrayList<String>();
		for(Savable s : savables){
			for(String str : s.save()){
				info.add(str);
			}
			
		}
		File saveFile = new File("../save-game.txt");
	
		try(
				PrintWriter writer = new PrintWriter(saveFile)
				){
			for(String s : info){
				writer.println(s);
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	public String[] loadGame(){
		java.util.List<String> info = new ArrayList<String>();
		String[] ret = new String[0];
		File saveFile = new File("../save-game.txt");
		
		try(
				Scanner scanner = new Scanner(saveFile)
				){
			while(scanner.hasNextLine()){
				info.add(scanner.nextLine());
			}
			ret = new String[info.size()];
			info.toArray(ret);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return ret;
	}
	public void process(String[] arr){
		int count = 0;
		while(count < arr.length){
			if(arr[count].equals("Earner")){
				count++;
				boolean isBought = Boolean.parseBoolean(arr[count]);
				count++;
				long upgradeCost = Long.parseLong(arr[count]);
				count++;
				double upgradeRatio = Double.parseDouble(arr[count]);
				count++;
				long stampsEarned = Long.parseLong(arr[count]);
				count++;
				long time = Long.parseLong(arr[count]);
				count++;
				earners.add(new StampEarner(stampsEarned, upgradeRatio, upgradeCost, time, isBought));
				
				
			}
			else if(arr[count].equals("Game")){
				count++;
				totalStamps = Long.parseLong(arr[count]);
				count++;
			}
		}
		
	}
	
}
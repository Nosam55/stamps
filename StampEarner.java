import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class StampEarner extends JPanel implements ActionListener, Updatable, Savable{
	private Image image;
	private long stampsEarned;
	private double upgradeRatio;
	private long upgradeCost;
	private JButton upgrade;
	private JButton buy;
	private StampCollector game;
	private boolean isBought;
	public static int lines;
	private long time;
	private LoadingBar loadingBar;

	public StampEarner(long s, double r, long u, long t, boolean b){
		super(new GridLayout(1,3));
		stampsEarned = s;
		upgradeRatio = r;
		upgradeCost = u;
		time = t;
		isBought = b;
		image = new ImageIcon("stamp.png").getImage();
		loadingBar = new LoadingBar(new ImageIcon("loading.png").getImage());
		upgrade = new JButton("Upgrade "+ upgradeCost);
		buy = new JButton("Buy " + upgradeCost);
//		loadingBar.scale(160,20);
		loadingBar.setAnimation(t);
		if(isBought)
			buildBought();
		else
			buildForSale();





	}
	private void buildBought(){
		this.removeAll();
		this.setLayout(new GridLayout(1,3));
		upgrade.addActionListener(this);
		this.add(upgrade);
		this.add(loadingBar);
		this.add(new ImagePanel(image));
		this.setSize(300, image.getHeight(this));
		this.revalidate();
	}
	private void buildForSale(){
		this.removeAll();
		this.setLayout(new FlowLayout());
		buy.addActionListener(this);
		this.add(buy);
	}
	public void setGame(StampCollector g){
		game = g;
	}
	
	public synchronized boolean isBought(){
		return isBought;
	}

	public synchronized void addStamps(){
		game.setStamps(game.getTotal() + stampsEarned);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == upgrade){

			long total = game.getTotal();
			if(total >= upgradeCost){
				game.setStamps(game.getTotal() - upgradeCost);
				synchronized(this){
					stampsEarned = (long) (stampsEarned * upgradeRatio);
					upgradeCost = (int) (upgradeCost * upgradeRatio);
				}
				upgrade.setText("Upgrade " + upgradeCost);

			}

		}
		else if(e.getSource() == buy){
			long total = game.getTotal();
			if(total >= upgradeCost){
				game.setStamps(total - upgradeCost);
				synchronized(this){
					isBought = true;
				}
				
				buildBought();
			}
		}



	
	}
	public void update(){
		if(isBought()){
			loadingBar.playAnimation();
			this.addStamps();
		}
	}
	public String[] save(){
		java.util.List<String> info = new ArrayList<String>();
		info.add("Earner");
		synchronized(this){
			info.add(Boolean.toString(isBought));
			info.add(Long.toString(upgradeCost));
			info.add(Double.toString(upgradeRatio));
			info.add(Long.toString(stampsEarned));
			info.add(Long.toString(time));
		}
		String[] infoArray = new String[info.size()];
		lines = info.size();
		infoArray = info.toArray(infoArray);
		return infoArray;
	}
	public static int lines(){
		return lines;
	}
	
	public LoadingBar getLoadingBar(){
		return loadingBar;
	}
	public synchronized long getStamps(){
		return stampsEarned;
	}
	

}

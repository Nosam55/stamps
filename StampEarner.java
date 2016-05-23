import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class StampEarner extends JPanel implements ActionListener{
	private Image image;
	private long stampsEarned;
	private double upgradeRatio;
	private int upgradeCost;
	private JButton upgrade;
	private JButton buy;
	private StampCollector game;
	private boolean isBought;
	private JPanel PLACEHOLDER;
	private double time;

	private final Object LOCK = new Object();
	
	public StampEarner(int x, double y, int u, double t){
		super(new GridLayout(1,3));
		stampsEarned = x;
		upgradeRatio = y;
		upgradeCost = u;
		time = t;
		isBought = false;
		image = new ImageIcon("../stamp.png").getImage();
		PLACEHOLDER = new JPanel();		// TODO: Create a loading bar graphic
		upgrade = new JButton("Upgrade "+ upgradeCost);
		buy = new JButton("Buy " + upgradeCost);
		buildForSale();
		
		
		
	
		
	}
	
	private void buildBought(){
		this.removeAll();
		this.setLayout(new GridLayout(1,3));
		upgrade.addActionListener(this);
		this.add(upgrade);
		this.add(PLACEHOLDER);
		this.add(new ImagePanel(image));
		this.setSize(300, image.getHeight(this));
		game.update();
		
	}
	public void update(){
		game.setStamps(game.getTotal() + stampsEarned);
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
	
	public boolean isBought(){
		return isBought;
	}
	public void getTime(){
		
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == upgrade){
			long total = game.getTotal();
			if(total >= upgradeCost){
				
				synchronized(this){
					stampsEarned = (int) (stampsEarned * upgradeRatio);
				}
			
				game.setStamps(game.getTotal() - upgradeCost);
				upgradeCost = (int) (upgradeCost * upgradeRatio);
				upgrade.setText("Upgrade " + upgradeCost);
			}
			
		}
		else if(e.getSource() == buy){
			
			long total = game.getTotal();
			if(total >= upgradeCost){
				game.setStamps(total - upgradeCost);
				isBought = true;
				buildBought();
				
			}
			
		}
		
		
		
		
	}

	public synchronized long getStamps(){
		return stampsEarned;
	}
	
}

/**
 * @(#)StampGUI.java
 *
 *
 * @author 
 * @version 1.00 2016/5/5
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class StampGUI implements Updatable, ActionListener{
	
	private JFrame frame;
	private JLabel totalStamps;
	private JPanel labelPanel;
	private StampCollector game;
	private JButton save;
	
    public StampGUI(java.util.List<StampEarner> list, StampCollector c) {
    	frame = new JFrame("Stamp Collector Simulator");
    	frame.setLocation(300,200);
    	frame.setLayout(new GridLayout(list.size() + 2, 1));
    	totalStamps = new JLabel("10");
    	labelPanel = new JPanel();
    	labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
    	labelPanel.add(new JLabel("Stamps: "));
    	labelPanel.add(totalStamps);
    	save = new JButton("Save");
    	save.addActionListener(this);
    	frame.add(labelPanel);
    	for(StampEarner e : list){
    		frame.add(e);
    	}
    	game = c;
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(500, list.size() * 60);
    	frame.add(save);
    	frame.setResizable(false);
    	frame.setVisible(true);
    	
    }
    public void setStampLabel(String s){
    	totalStamps.setText(s);
    	frame.revalidate();
    }
    public String getStamps(){
    	return totalStamps.getText();
    }
    public void redraw(){
    	Graphics g = frame.getGraphics();
    	frame.update(g);
    }
    public void update(){
    	long stampsL = game.getTotal();
    	String stamps = Long.toString(stampsL);
    	if(!totalStamps.getText().equals(stamps))
    		setStampLabel(stamps);
    }
    public void actionPerformed(ActionEvent e){
    	game.saveGame();
    	System.out.println("Tste");
    }
    
}
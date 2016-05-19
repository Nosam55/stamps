/**
 * @(#)StampGUI.java
 *
 *
 * @author 
 * @version 1.00 2016/5/5
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StampGUI {
	
	private JFrame frame;
	private JLabel totalStamps;

	
    public StampGUI(java.util.List<StampEarner> list) {
    	frame = new JFrame("Stamp Collector Simulator");
    	frame.setLocation(300,200);
    	frame.setLayout(new GridLayout(list.size() + 1, 1));
    	totalStamps = new JLabel("10");
    	frame.add(totalStamps);
    	for(StampEarner e : list){
    		frame.add(e);
    	}
    
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(500, list.size() * 60);
    	frame.setResizable(false);
    	frame.setVisible(true);
    	
    }
    public JLabel getStampLabel(){
    	return totalStamps;
    }
    public void redraw(){
    	Graphics g = frame.getGraphics();
    	frame.update(g);
    }
    
}
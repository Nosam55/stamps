/*
 * author: Mason McCully
 * date: 22/5/16
 * For my AP Computer Science final project
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class LoadingBar extends ImagePanel{
	private java.util.List<Image> animation; 
	private long millis;
	private final long TIME = 1000/15l; 
/*		public static void main(String[] args){
		LoadingBar lb = new LoadingBar(new ImageIcon("loading.png").getImage());
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(lb);
		frame.pack();
		frame.setLocation(100,100);
		frame.setVisible(true);
		frame.setSize(500,500);
		lb.scale(200,20);
		lb.setAnimation(4000);
		for(int i = 10; i>0; i--)
			lb.playAnimation();
	}
	*/	
	public LoadingBar(Image i){
		super(i);
		animation = new ArrayList<Image>();

	}
	
	public void playAnimation(){
		long frames = millis/TIME;
		for(Image i : animation){
			try {
				Thread.sleep(millis/frames);
				super.setImg(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setAnimation(long timeMillis){
		
		long frames = timeMillis/TIME;
		millis = timeMillis;
		Image image = getImage();
		for(int i = 1; i<=frames; i++){

			image = getImage().getScaledInstance(
					(int)(1 + ((double)i / (double)frames)*getImage().getWidth(this)),
					getImage().getHeight(this),
					Image.SCALE_DEFAULT);
			animation.add(image);

		}
	}
}

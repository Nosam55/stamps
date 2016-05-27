import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;

class ImagePanel extends JPanel {
	private Image img;
	public ImagePanel(Image i){
		img = i;
		this.setSize(img.getWidth(this), img.getHeight(this));
	}
	public synchronized Image scale(int newWidth, int newHeight){
		img = img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		while(img.getHeight(this) < 0){
			
		}
		return img;
	}
	public synchronized void setImg(Image image){
		img = image;
		this.repaint();
	}
	public synchronized Image getImage(){
		return img;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0,0,this);
	}
}

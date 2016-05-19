import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;

class ImagePanel extends JPanel {
	private Image img;
	public ImagePanel(Image i){
		img = i;
		this.setSize(img.getWidth(this), img.getHeight(this));
	}
	public void resize(int newWidth, int newHeight){
		img = img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0,0,this);
	}
}

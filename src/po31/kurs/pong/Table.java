package po31.kurs.pong;

import java.awt.Image;

import javax.swing.ImageIcon;



public class Table {
	
	private int border_up;
	private int border_down;
	private int border_left;
	private int border_right;
	private Image img;
	
		
	public Table(int w, int h){ //w - weight; h- height;
		setBorders(w, h);
		
	}
	
	public void setBorders(int w, int h) {
		setBorder_up(0);
		setBorder_down(h);
		setBorder_left((int)(-0.15*w));
		setBorder_right((int)(1.15*w));
		img = new ImageIcon("res/bg.png").getImage().getScaledInstance(w, h, 0);
		img.setAccelerationPriority(1);
		
	}

	public int getBorder_right() {
		return border_right;
	}

	public void setBorder_right(int border_right) {
		this.border_right = border_right;
	}

	public int getBorder_left() {
		return border_left;
	}

	public void setBorder_left(int border_left) {
		this.border_left = border_left;
	}

	public int getBorder_down() {
		return border_down;
	}

	public void setBorder_down(int border_down) {
		this.border_down = border_down;
	}

	public int getBorder_up() {
		return border_up;
	}

	public void setBorder_up(int border_up) {
		this.border_up = border_up;
	}

	public Image getImage() {
		return img;
	}

	public void setImage(Image img) {
		this.img = img;
	}
	
	
}

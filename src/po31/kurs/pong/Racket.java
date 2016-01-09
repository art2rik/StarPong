package po31.kurs.pong;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

abstract public class Racket {
	public final static double W_K= 0.03667;
	public final static double H_K= 0.2148;
	//коэффициент высоты

	protected static final byte DOWN = 1;
	protected static final byte UP = 0;
	protected int  x_pos; //позиция Ох
	protected int  y_pos = 250; // позиция Оу
	protected int height = 10;
	protected int width = 30;
	protected int score = 0; //очки
	protected Image img;
	protected String racket_name;
	protected int racket_num;
	protected  double sup_side;
	protected Rectangle racket_rect = new Rectangle(); // Абстрактное представление ракетки
	
	
	protected Racket(int w, int h, int num, String name) {
		
		setRacket(num, name,w);
		setSize(w, h);
		
		
	}
	public void setSize(int w, int h) {
		height =(int)(h*H_K);
		width = (int)(w*W_K);
		racket_rect.setSize(width, height);
		setSupSide(height);
		String fp = "";
		switch (racket_num) {
		case 2: fp = "res/2b.png"; x_pos = width; break;
		case 1: fp = "res/2b.png"; x_pos = w - 2*width;
		}
		img = new ImageIcon(fp).getImage().getScaledInstance(width, height, 0);
		racket_rect.setLocation(x_pos, y_pos);
		img.setAccelerationPriority(1);
	}
	
	protected void setSupSide(int h) {
		sup_side = h/2*Math.tan(Math.PI/3);
	}
	/*
	 * Устанавливает дополнительную сторону для рассчета траектории рикошета
	 */
	
	protected void setRacket(int num, String name, int w) {
		racket_name = name;
		racket_num= num;		
	}
	
	public void Move(int step_y, Table t) {
		y_pos += step_y;
		if (y_pos < t.getBorder_up()) {
			y_pos = t.getBorder_up();		
			} else if (y_pos+getHeight() > t.getBorder_down()) {
				y_pos = t.getBorder_down()-getHeight();
			}
		getRect().setLocation(x_pos, y_pos);
		
	}
	
	
	public void setY(int y) {
		y_pos = y;
	}
	
	public int getY() {
		return y_pos;
	}
	
	public int getX() {
		return x_pos;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int scr) {
		score = scr;
	}
	
	public String getPlayer_name() {
		return racket_name;
	}
	 
	 public Rectangle getRect() {
		 return racket_rect;
	 }
	 
	 public double getSupSide() {
		 return sup_side;
	 }
	 
	 public void incScore() {
		 score++;
	 }
	 
	 public int getWidth(){
		 return width;
	 }
	 
	 public int getHeight(){
		 return height;
	 }
	 
	 public Image getImage(){
		 return img;
	 }
}

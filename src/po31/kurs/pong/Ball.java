package po31.kurs.pong;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Ball {
	public static final double K = 0.0605; //коэфф.
	public static final byte RIGHT = 1;
	public static final byte LEFT = 2;
	public static final int MIN_SPEED = 10;
	public static final int MAX_SPEED = 50;
	public static final byte DOWN = 3;
	public static final byte UP = 4;
	public static final double MIN_CORNER = 0;
	public static final double MAX_CORNER = Math.PI/3;
	private int x_pos;
	private int y_pos;
	private int speed = 15;
	private byte x_direction;
	private byte y_direction;
	private int width;
	private int height;
	private int x_center;
	private int y_center;
	private double corner;
	private Rectangle ball_rect = new Rectangle();
	private Image img;
	/*
	 * K - коэффициент соотношения мяча с полем
	 * RIGHT,UP,LEFT,DOWN - константы направлений
	 * MIN_SPEED,MAX_SPEED - минимальное, максимальное значение скорости мяча
	 * x_pos, y_pos - текущие координаты мяча
	 * speed - текущая скорость
	 * x_direction, y_direction - текущие направления мяча по осям Ох, Оу
	 * width, height - ширина, высота мяча
	 * x_center, y_center - координаты центра поля
	 * corner - текущий угол движения мяча
	 * ball_rect - абстрактное представление мяча 
	 */
	
	public Ball(int w, int h) {
	 Center();
	 setSize(w, h);
	}
	
	public void speedUp(int v_step) {
		speed +=v_step;
		if (speed > MAX_SPEED) {
			speed = MAX_SPEED;
		}
	}
	/*
	 * Метод увеличивает скорость мяча на v_step
	 */
	
	public void DropBall(byte dir) {
		x_direction = dir;
		double crnr = -MAX_CORNER + Math.random()*2*MAX_CORNER;
		if (crnr <=0){
			y_direction = UP;
		} else {
			y_direction = DOWN;
		}
		setCorner(crnr);
		setSpeed(MIN_SPEED);
		Center();
	}
	
	/*
	 * Метод выбрасывает мяч в сторону под случайным углом
	 */
	
	
	public void setCenter(int w, int h) {
		Rectangle rect = new Rectangle(w,h);
		x_center = (int)(rect.getCenterX() - width/2);
		y_center = (int)(rect.getCenterY() - height/2);
	}
	/*
	 * Рассчитывает и устанавливает центральную точку
	 * появления мяча
	 */
	
	public void setSize(int wdth, int hght) {
		width =(int)(hght*K);
		height = width;
		ball_rect.setSize(width, height);
		setCenter(wdth,hght);
		img = new ImageIcon("res/3b.png").getImage().getScaledInstance(width, height, 2);
		img.setAccelerationPriority(1);

	}
	/*
	 * Устанавливает размеры мяча
	 * int wdth - ширина
	 * int hght - длина
	 */
	
	public void setSpeed(int spd) { //Сеттер скорости
		speed = spd;
	}
	
	public int getSpeed() { //Геттер скорости
		return speed;
	}

	public void setPos(int x, int y) { //Сеттер позиции
		x_pos = x;
		y_pos = y;
		ball_rect.setLocation(x, y);
	}
	
	public void Move(Table table) {
		
				
		if (x_direction == RIGHT) {
				x_pos += speed; 
		} else {
				x_pos -= speed;
		}
		
		int step_y = (int) (Math.tan(corner)*speed);
		
		y_pos+=step_y;
		
		ball_rect.setLocation(x_pos, y_pos);
		
		if (x_pos < table.getBorder_left()) {
			x_pos = table.getBorder_left();
		} else if (x_pos + getWidth() > table.getBorder_right()) {
			x_pos = table.getBorder_right();
		}
		
		if (y_pos < table.getBorder_up()) {
			y_pos = table.getBorder_up();
		} else if (y_pos + getHeight() > table.getBorder_down()) {
			y_pos = table.getBorder_down() - getHeight();
		}
		
	}
	/*
	 * Метод рассчета траектории движения мяча
	 */
	
	public void turn_around_x(){ //Разворот по оси Ох
		if(x_direction == LEFT) {
		x_direction = RIGHT;
		} else {
			x_direction = LEFT;
		}
	}
	
	public void turn_around_y(){//Разворот по оси Оу
			
			corner*=-1;
			if (corner <=0){
				y_direction = UP;
			} else {
				y_direction = DOWN;
			}
	
	}

	public int getY() { //Геттер У-координаты
		return y_pos;
	}
	
	public int getX() { //Геттер Х-координаты
		return x_pos;
	}

	public double getCorner() { // Геттер угла
		return corner;
	}

	public void setCorner(double corner) { //Сеттер угла
		this.corner = corner;
	}
	
	public Rectangle getRect() { //Геттер абстрактного прямоугольника
		return ball_rect;
	}

	public int getYdirection() { //Геттер направления оси Оу
		return y_direction;
	}
	
	public int getXdirection() { //Геттер направления оси Ох
		return x_direction;
	}

	private void Center() { 
		setPos(x_center, y_center);
	}
	/*
	 * Метод возвращает мяч в центральное положение
	 */
	public int getMaxSpeed(){
		return MAX_SPEED;
	}
	
	public int getMinSpeed() {
		return MIN_SPEED;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return width;
	}

	public Image getImage() {
		return img;
	}

	public void setImage(Image img) {
		this.img = img;
	}
}
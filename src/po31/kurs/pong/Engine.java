package po31.kurs.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import po31.kursk.Game;

public class Engine extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private Racket p1;
	private Racket p2;
	private Table table;
	private Ball ball;
	private Timer mt;
	private Input inp = new Input();
	private int type = 2;
	private int width;
	private int height;
	private Game gui;
	
	
	
	
	
	
	public Engine (int w, int h, String p1_name, String p2_name, int type, Game gui) {
		p1 = new Player(w,h,1, p1_name);		
		table = new Table(w,h);
		ball = new Ball(w,h);
		width = w;
		height = h;
		setType(type);
		if (type == 1) {
			p2 = new AIPlayer(width, height, 1);
		} else {
			p2 = new Player(w,h,2, p2_name);
		}
		this.gui = gui;
	}
	
	
	public void StartGame() {
		setVisible(true);
		mt = new Timer(50, this);
		byte dir = (byte)(1+Math.random()); //рандом от 1 до 2	
		p1.setScore(0);
		p2.setScore(0);
		ball.DropBall(dir);		
		setDoubleBuffered(true);
		repaint();
		mt.start();
		
		
	}
	/*
	 * Запускает игру
	 */
	
	public void Resize(int w, int h) {
		p1.setSize(w, h);
		p2.setSize(w, h);
		ball.setSize(w, h);
		table.setBorders(w, h);
		width = w;
		height = h;
	}
	
	private int getVStep() {
		int Vmax = 25;
		int Vmin = 5;
		double corner = Math.abs(ball.getCorner());
		return(int)((Vmax/Ball.MAX_CORNER)*(corner-Ball.MIN_CORNER)+Vmin);
	}
	/*
	 * Метод рассчитывает и возвращает шаг скорости, зависящий от угла отскока
	 * Vmax - максимальная скорость, при отскоке, Vmin - минимальная.
	 * corner - текущий угол
	 * Ball.MAX_CORNER - максимальный. Ball.MIN_CORNER - минимальный угол.
	 */
	
	private double getCorner(Racket racket) {
		double sup_point = ball.getRect().intersection(racket.getRect()).getCenterY(); //средняя точка пересечения
		double side = sup_point - racket.getRect().getCenterY(); //расстояния от т. пересечения, до центра ракетки.
		return Math.atan(side/racket.getSupSide());
		
	}
	/*
	 * 	Рассчитавает угол отскока, в зависимости от места соприкосновения
	 * мяча с ракеткой.
	 */
	
	private boolean isKick(Racket racket) {
		if (ball.getRect().intersects(racket.getRect())) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Проверка на столкновение с ракеткой
	 */
	
	private boolean isBounced() {
		int y = ball.getY();
		if (y <= table.getBorder_up() || y+ball.getHeight()>=table.getBorder_down() ){
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Проверка столкновения с боковой стеной
	 */
	
	private boolean isGoal() {
		int x = ball.getX();
		if (x <= table.getBorder_left() || x>=table.getBorder_right()){
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Проверка на забитый мяч
	 */
	
	private void Goal() {
			if (ball.getXdirection() == Ball.RIGHT) {
				p2.incScore();
				ball.DropBall(Ball.RIGHT);
			} else {
				p1.incScore();
				ball.DropBall(Ball.LEFT);
			}
	}
	/*
	 * Действие на забитый мяч
	 */
	
	public void paint(Graphics g) {
		
		g = (Graphics2D) g;

		g.clearRect(0, 0, width, height);
		
		g.drawImage(table.getImage(),0,0,null);
				
		g.drawImage(ball.getImage(),
					ball.getX(), 
					ball.getY(), 
					null);
		
		g.drawImage(p1.getImage(),
					p1.getX(),
					p1.getY(),
					null);		
		
		g.drawImage(p2.getImage(),
				p2.getX(),
				p2.getY(),
				null);	
		
				
		g.setFont(new Font("Aerial",Font.BOLD, 24));
		g.setColor(new Color(255,255,255));
		g.drawString(Integer.toString(p1.getScore()), p1.getX()+p1.getWidth()/2, 50);
		g.drawString(Integer.toString(p2.getScore()), p2.getX()+p2.getWidth()/2, 50);
		
		
		
		
	}
	/*
	 * Метод, выводящий игру на экран
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ProcessGame();		
		
	}
	
	private void ProcessGame() {

		ball.Move(table);
		
		if (type == 1) {
			((AIPlayer)p2).CalcMove(ball);			
		}
		
		realizeInput();
		if (isGoal()) {
			Goal();			
		}
		
		if (isBounced()) {
			ball.turn_around_y();				
		}
		if (isKick(p1)) {
			ball.setCorner(getCorner(p1));
			ball.turn_around_x();
			ball.speedUp(this.getVStep());
		} else if (isKick(p2)) {
			ball.setCorner(getCorner(p2));
			ball.turn_around_x();
			ball.speedUp(this.getVStep());
		}
		
		repaint();
		
		if (p1.getScore() > 9 || p2.getScore() > 9) {
			mt.stop();
			gui.ShowMenu();
		}
	}
	/*
	 * Алгоритм игры
	 */
	public void StopGame() {
		mt.stop();
		
	}
	private void realizeInput() {
		boolean[] pressed = inp.getPressed();
		
		if (pressed[KeyEvent.VK_UP]) {p1.Move(-15, table);}
		if (pressed[KeyEvent.VK_DOWN]) {p1.Move(15, table);}
		if (pressed[87]) {p2.Move(-15, table);}
		if (pressed[83]) {p2.Move(15, table);}
		if (pressed[KeyEvent.VK_ESCAPE]) {
			StopGame();
			gui.ShowMenu();
		}
	}
	
	public Input getInput() {
		return inp;
	}

	public void setInput(Input inp) {
		this.inp = inp;
	}



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}



	private class Input implements KeyListener {
		
		boolean[] pressed = new boolean[256];
	    
		
		@Override
		public synchronized void keyPressed(KeyEvent e) {
			pressed[e.getKeyCode()] = true;
			
		}

		
		@Override
		public void keyReleased(KeyEvent e) {
			pressed[e.getKeyCode()] = false;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public boolean[] getPressed() {
			return pressed;
		}
		
	}




	
}

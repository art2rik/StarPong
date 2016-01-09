package po31.kursk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements KeyListener {
	
	private Game game;
	private Image bg;
	private Image header; 
	private Image splayer;
	private Image mplayer;
	private Image about;
	private Image ball;
	private long pr_l = -1; //press_latency
	//private Image lines;
	
	private int height;
	private int width;
	private int pos = 1;
	private Map<Integer,Double> psm = new HashMap<Integer,Double>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -7727306813182086328L;
	
	public MainMenu(Game game){
		setGame(game);
		psm.put(1,0.5214);
		psm.put(2,0.6165);
		psm.put(3, 0.7292);
	}
		
	public void paint(Graphics g) {
		g = (Graphics2D) g;
		g.clearRect(0, 0, width, height);
		
	    g.drawImage(bg,0,0,this);
	    
	    int w_c = (int)(0.21*width);
	    int h_c = (int)(0.2145*height);
		g.drawImage(header, w_c, h_c, null);
		
		w_c = (int)(0.356*width);
	    h_c = (int)(0.5214*height);
	    g.drawImage(splayer, w_c, h_c, null);
	    
	    w_c = (int)(0.3844*width);
	    h_c = (int)(0.6165*height);
	    g.drawImage(mplayer, w_c, h_c, null);
	    
	    w_c = (int)(0.4731*width);
	    h_c = (int)(0.7292*height);
	    g.drawImage(about, w_c, h_c, null);
	    	    
	    w_c = (int)(0.6265*width);
	    h_c = (int)(psm.get(pos)*height);
	    g.drawImage(ball, w_c, h_c, null);
		
		
	}
	
	public void Resize(int w, int h) {
		width = w;
		height = h;
		
			
		bg = new ImageIcon("res/menu/bg.png").getImage().getScaledInstance(w, h, 0);
		bg.setAccelerationPriority(1);
		
		int w_c = (int)(w*0.619);
		int h_c = (int)(h*0.145);
		header = new ImageIcon("res/menu/0.png").getImage().getScaledInstance(w_c,h_c,0);
		header.setAccelerationPriority(1);
		
		w_c = (int)(w*0.2338);
		h_c = (int)(h*0.067);
		splayer = new ImageIcon("res/menu/1.png").getImage().getScaledInstance(w_c,h_c,0);
		splayer.setAccelerationPriority(1);
		
		w_c = (int)(w*0.2054);
		h_c = (int)(h*0.0657);
		mplayer = new ImageIcon("res/menu/2.png").getImage().getScaledInstance(w_c,h_c,0);
		mplayer.setAccelerationPriority(1);
		
		w_c = (int)(w*0.1096);
		h_c = (int)(h*0.054);
		about = new ImageIcon("res/menu/4.png").getImage().getScaledInstance(w_c,h_c,0);
		about.setAccelerationPriority(1);
		
		w_c = (int)(w*0.0366);
		
		ball = new ImageIcon("res/menu/b.png").getImage().getScaledInstance(w_c,w_c,0);
		ball.setAccelerationPriority(1);
		
		
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getWhen() - pr_l < 200 && pr_l != 1 ) {
			return;
		} else {
			pr_l = e.getWhen();
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (pos<3) {
					pos++;
				}
			}
			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (pos>1) {
					pos--;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				switch (pos) {
				case 1: game.Start(1); break;
				case 2: game.Start(2);
				}
			}
		}  
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setGame(Game game) {
		this.game = game;
	}


}

package po31.kursk;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import po31.kurs.pong.Engine;

public class Game{
	
	private JFrame frame = new JFrame();
	private Engine engine;
	private MainMenu menu;
	private boolean isGame = false;
	
	//-------------------------------------------------------
	public static void main(String[] args) {
		Game g = new Game(1280, 720);
		g.ShowMenu();
		
	}	
	//-------------------------------------------------------
	
	public Game(int w, int h) {
		frame.setSize(w, h);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		menu = new MainMenu(this);
		frame.setVisible(true);
				

		//frame.setVisible(true);		
		frame.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
			 engine.Resize(frame.getWidth(), frame.getHeight()-40);
			 menu.Resize(frame.getWidth(), frame.getHeight());
			 menu.repaint();
			 
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			    
			    });
		
		

		
	}
	
	public void Start(int type) {
		isGame = true;
		menu.setVisible(false);
		frame.remove(menu);
		frame.removeKeyListener(menu);
		engine = new Engine(1280, 720, "One", "Two", 1, this);
		frame.add(engine);
		frame.addKeyListener(engine.getInput());
		engine.setType(type);
		engine.Resize(frame.getWidth(), frame.getHeight()-40);
		engine.setVisible(true);
		engine.StartGame();
		frame.setVisible(true);

	}
	
	public void ShowMenu() {
		if (isGame) {
			frame.remove(engine);
			frame.removeKeyListener(engine.getInput());
			isGame = false;
		}
		frame.add(menu);
		frame.addKeyListener(menu);
		menu.setVisible(true);
		
		
		menu.Resize(frame.getWidth(),frame.getHeight());
		menu.paint(frame.getGraphics());
		
		
					
	}



	
}

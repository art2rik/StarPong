package po31.kurs.pong;

import java.awt.AWTException;
import java.awt.Robot;


public class AIPlayer extends Player {
	
	private Robot robot;
	
	public AIPlayer(int w, int h, int type) {
		super(w,h,2,"Computer");
		try {
		robot = new Robot();
		} catch (AWTException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	public void CalcMove(Ball ball) {
		int r_point = getY()+getHeight()/2;
		int b_point = ball.getY()+ball.getHeight()/2;
		
		if (b_point > r_point) {
			robot.keyRelease(87);
			robot.keyPress(83);
		} else if(b_point < r_point) {
			robot.keyRelease(83);
			robot.keyPress(87);
		} else {
			robot.keyRelease(83);
			robot.keyRelease(87);
		}
	}
	
	
	
	

}

package BreakOut;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
;


public class BreakOutWindow extends JFrame{

	static int scoore = 0 ;
	int x, y;
	JPanel myPannel ;
	Timer horloge ;
	Ball ball;
	Paddle paddle;
	boolean lost = false;
	int rowBrick = 4, colBrick = 8;
	ArrayList<ArrayList<Brick>> brickList = new ArrayList<>();
	
	public BreakOutWindow() {
		this.setTitle("breakout game");
		
		// get the screen size 
		x = Toolkit.getDefaultToolkit().getScreenSize().width ;
		y = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		this.setSize(x/2, y/2);
		this.setLocationRelativeTo(null);  /// center the window 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.GRAY);
		ball = new Ball(x/4,  y/4, 3, 3);
		paddle = new Paddle(x/4, y/2-40, x/2);
		for (int row = 0; row < rowBrick; row++) {
			brickList.add(new ArrayList<Brick>());
			for (int col = 0; col < colBrick; col++) {
				brickList.get(row).add(new Brick(row, col));
			}
		}
				
		JLabel scooreLabel = new JLabel();
		scooreLabel.setText("scoore :  " + scoore);
		scooreLabel.setBounds(0 ,0, 200, 50);
		
		myPannel = new JPanel() {
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				
				ball.DrawBall(g);
				paddle.DrawPaddle(g);
				for (ArrayList<Brick> arrayList : brickList) {
					for (Brick brick : arrayList) {
						brick.DrawBrick(g);
					}
				}
			}
		};
		myPannel.add(scooreLabel);
		this.setContentPane(myPannel);
		
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					paddle.moveR();
					if(!ball.moving) ball.moving = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
					paddle.moveL();
					if(!ball.moving) ball.moving = true;
				}
			}
			 @Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
					paddle.dx = 0;
				}
			}
		});
		
		horloge = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paddle.update();
				if(ball.update(myPannel.getWidth(), myPannel.getHeight(), paddle)) {
					restart();
					ball.moving = false;
				}
				for (int row = 0; row < rowBrick; row++) {
					for (int col = 0; col < colBrick; col++) {
						brickList.get(row).get(col).touch(ball) ;
					}
				}
				scoore = Brick.invisibleBricks;
				scooreLabel.setText("scoore :  " + scoore);
				repaint();
			}
			
		});
		horloge.start();

		this.setVisible(true);
	}
	
	void restart() {
		ball.restart(x/4,  y/4, 4, 4);
		paddle.restart(x/4, y/2-40, x/2);
		for (int row = 0; row < rowBrick; row++) {
			for (int col = 0; col < colBrick; col++) {
				brickList.get(row).get(col).restart(row, col);
			}
		}
		Brick.invisibleBricks = 0;
	}
	
	
	public static void main(String[] args) {
		new BreakOutWindow();

	}

}

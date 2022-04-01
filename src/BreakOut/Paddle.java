package BreakOut;
import java.awt.Color;
import java.awt.Graphics;

public class Paddle {

	int width = 100,
		heigth = 15,
		x,
		y,
		speed = 8,
		dx = 0,
		canvasW;
	
	public Paddle(int x, int y, int canvasW) {
		this.x = x - this.width/2 ;
		this.y = y - this.heigth -3;
		this.canvasW = canvasW;
	}

	void DrawPaddle( Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, heigth);
	}
	public void moveR() {
			this.dx = speed;
	}
	public void moveL() {
			this.dx = -speed ; 			
	}
	public void restart(int x, int y, int canvasW) {
		this.x = x - this.width/2 ;
		this.y = y - this.heigth -3;
		this.canvasW = canvasW;
	}
	public void update() {
		if (this.x > canvasW - width ) {
			this.x = canvasW - width;
		} else if ( this.x < 0) {
			this.x = 0;
		} else {
			this.x += dx;
		}
		
	}
}

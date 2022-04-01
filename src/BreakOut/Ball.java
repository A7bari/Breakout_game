package BreakOut;

import java.awt.Color;
import java.awt.Graphics;


public class Ball {
	int x,
		y,
		diam = 20,
		speed = 4,
		dx,
		dy;
	
	boolean moving = false;
	
	public Ball(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	void DrawBall( Graphics g ) {
		g.setColor(Color.red);
		g.fillOval(x, y, diam, diam);
	}
	
	boolean update(int w, int h, Paddle paddle) {
		if (moving) {
			if (x >= w - diam || x <= 0) {
				dx *= -1;
			}
			if (y <= 0 || (y + diam > paddle.y && (x > paddle.x && x < paddle.x + paddle.width))) {
				dy *= -1;
			}
			this.x += dx;
			this.y += dy;
			if (y >= h) {
				
				return true;
			}			
		}
		return false;
	}

	public void restart(int x, int y ,int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public void reverse() {
	
		dy *= -1;
	}
	
}

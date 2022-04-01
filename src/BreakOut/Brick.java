package BreakOut;

import java.awt.Color;
import java.awt.Graphics;

public class Brick {
	static int invisibleBricks = 0;
	int w = 90,
		h = 20,
		offssetX = 50 ,
		offssetY = 40 ,
		padding= 20 ,
		x,
		y;
	boolean isVisible = true;
	
	public Brick(int row, int col) {
		this.x = col* (w + padding) + offssetX;
		this.y = row* (h + padding) + offssetY;
	}
	
	void DrawBrick( Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, w, h);
	}
	void SetInvisible() {
		this.w = 0;
		this.isVisible = false;
	}

	public void restart(int row, int col) {
		this.w = 90;
		isVisible = true;
	}
	public void touch(Ball ball) {
		if (isVisible) {
			if (ball.x + ball.diam/2 > x && ball.x + ball.diam/2 < x + w) {
				if ((ball.y < y + h && ball.y > y) || (ball.y + ball.diam > y && ball.y + ball.diam < y + h )) {
					SetInvisible();
					ball.reverse();
					invisibleBricks++;
				}
			}			
		}
	}
}

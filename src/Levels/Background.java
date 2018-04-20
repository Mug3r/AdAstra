package Levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;

public class Background {

	private BufferedImage bg;
	private double x, y;
	private double dx, dy;

	public Background(BufferedImage b){
		bg = b;
		x = 0;
		y = 0;
		dx = 0;
		dy = -1;
	}

	public void Update(){

		x += dx;
		y += dy;

	}

	public void setdX(double dx){this.dx = dx;}
	public void setdY(double dy){this.dy = dy;}

	public void Render(Graphics2D g){
		g.drawImage(bg, (int) x, (int) y, null);
		if(dx != 0){
			if(x < 0){
				g.drawImage(bg, ((int) x + GamePanel.WIDTH*2 - 2),  (int) y, null);
			}
			else
			{
				if(x > 0){
					g.drawImage(bg, (int) x - GamePanel.WIDTH,  (int) y, null);
				}

			}

			if(x < -GamePanel.WIDTH*2){
				x = 0;
			}
		}
		if(dy != 0){
			if(y<0){
				g.drawImage(bg, (int) x ,  ((int) y + GamePanel.HEIGHT*2 - 2), null);
			} else
			{
				
				if(y > 0){
					g.drawImage(bg, (int)x, ((int)y - GamePanel.HEIGHT*2 - 2), null);
				}
				
			}
			
			if(y < -GamePanel.HEIGHT*2){
				y = 0;
			}
		}
	}

}

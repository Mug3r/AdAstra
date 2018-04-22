package levelManagement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;

public class Background {

	private BufferedImage bg;
	private double x, y;
	private double dx, dy;
	private int w, h;

	public Background(BufferedImage b){
		bg = b;
		x = 0;
		y = 0;
		dx = 0;
		dy = -1;
		w = b.getWidth();
		h = b.getHeight();
	}

	public void Update(){

		x += dx;
		y += dy;

	}

	public void setdX(double dx){this.dx = dx;}
	public void setdY(double dy){this.dy = dy;}
	public void setX(double x){this.x = x;}
	public void setY(double y){this.y = y;}

	public void Render(Graphics2D g){
		g.drawImage(bg, (int) x, (int) y, null);
		if(dx != 0){
			if(x < 0){
				g.drawImage(bg, ((int) x + (w - 2)),  (int) y, null);
			}
			else
			{
				if(x > 0){
					g.drawImage(bg, (int) x - w,  (int) y, null);
				}

			}

			if(x < -w){
				x = 0;
			}
		}
		if(dy != 0){
			if(y<0){
				g.drawImage(bg, (int) x ,  ((int) y + (h - 2)), null);
			} else
			{
				
				if(y > 0){
					g.drawImage(bg, (int)x, ((int)y - (h - 2)), null);
				}
				
			}
			
			if(y < -bg.getHeight()){
				y = 0;
			}
		}
	}

}

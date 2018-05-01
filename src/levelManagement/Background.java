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

	/*The image that scrolls in the back of the game throughout all menus and levels
	 * common to all levels and menus each adjusts the speed of the background
	 */
	
	public Background(BufferedImage b){
		bg = b;
		x = 0;
		y = 0;
		dx = 0;
		dy = -1;
		w = b.getWidth();
		h = b.getHeight();
	}
	//Move backgorund
	public void Update(){

		x += dx;
		y += dy;

	}

	//Draw to image
	public void Render(Graphics2D g){
		g.drawImage(bg, (int) x, (int) y, null);
		
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
	//Getters and setters
	public void setdX(double dx){this.dx = dx;}
	public void setdY(double dy){this.dy = dy;}
	public void setX(double x){this.x = x;}
	public void setY(double y){this.y = y;}


}

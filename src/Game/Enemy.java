package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Graphics.ImageManager;

public class Enemy extends MapObject {

	private int type, health;
	private double speed;
	private double x, y;
	private boolean lt = false;
	
	public Enemy(double x, double y, int t, ImageManager im) {
	
		super(x,y, im);
		
		this.x = x;
		this.y = y;
		this.type = t;
		
		switch(t) {
			
		case 0:
			r = 15;
			w = 0;
			h = 0;
			cw = 0;
			ch = 0;
			health = 1;
			speed = 1;
			lt = true;
			break;
		
		case 1:
			r = 30;
			w = 0;
			h = 0;
			cw = 0;
			ch = 0;
			health = 2;
			speed = 3;
			lt = false;
			break;
		
		}
		
	}
	
	public void update() {
		
		if(rotatingLt){
			angle += 1;
		}
		
		if((x+r) >= GamePanel.WIDTH) {
			
			lt = true; 
			y+=r;
			
		} else if((x) <= 0) {
			
			lt = false; 
			y+=r;
			
		}
		
		if(!lt) {x = (x+speed);} else {x = (x-speed);} 
		
	}
	
	public void draw(Graphics2D g) {
		switch(type) {
		
			case 0:
				g.setColor(new Color(120,100,0));
				g.fillOval((int)x, (int)y, r, r);
				break;
				
			case 1:
				g.setColor(new Color(220,0,120));
				g.fillOval((int)x, (int)y, r, r);
				break;
				
		}
		
		
	}
	public boolean getLt() {return lt;}
	public void setLt(boolean b) {lt = b;}
	
	public double getX() {return x; }
	public double getY() {return y;}
	public int getR() {return r;}

	
	
}

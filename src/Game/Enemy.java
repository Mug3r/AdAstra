package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Graphics.ImageManager;

public class Enemy extends MapObject {

	private int type;
	
	public Enemy(int x, int y, int t, ImageManager im) {
	
		super(x,y, im);
		
		this.type = t;
		sprite = im.alienSprites[t];
		switch(t) {
			
		case 0:
			r=0;
			w = 30;
			h = 30;
			cw = 0;
			ch = 0;
			health = 1;
			speed = 1;
			lt = true;
			break;
		
		case 1:
			r=0;
			w = 30;
			h = 30;
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
		
		
		if(!lt) {x +=speed;} else {x -= speed;} 
		
	}
	
		
		
	
	public boolean getLt() {return lt;}
	public void setLt(boolean b) {lt = b;}
	

	public int getR() {return r;}

	
	
}

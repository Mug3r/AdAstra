package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Graphics.ImageManager;

public class Enemy extends MapObject {

	private int type;
	
	public Enemy(int x, int y, int t) {
	
		super(x,y);
		
		this.type = t;
		sprite = ImageManager.alienSprites[t];
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
		
		if(health <= 0){
			isDead = true;
			return;
		}
		
		if(!isDead){
			
			if(rotatingLt){
				angle += 1;
			}
		
		
			if(!lt) {x +=speed;} else {x -= speed;} 
			
		} else{return;}
		
	}
	
		
	public boolean collidesWith(MapObject o){
		return CollisionDetection.collidesWith(this, o);
	}
	
	public boolean getLt() {return lt;}
	public void setLt(boolean b) {lt = b;}
	

	public int getR() {return r;}

	
	
}

package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Graphics.ImageManager;
import levelManagement.GameStateManager;

public class Enemy extends MapObject {

	private int type;
	
	public Enemy(int x, int y, int t) {
	
		super(x,y);
		
		this.type = t;
		if(t <= 3){
			sprite = ImageManager.alienSprites[t];
		}
		
		r=0;
		w = 100;
		h = 100;
		cw = 80;
		ch = 80;
		
		switch(t) {
			
		case 0:
			health = 1;
			speed = 1;
			lt = true;
			break;
		
		case 1:
			health = 2;
			speed = 3;
			lt = false;
			break;
			
		case 2:
			health = 1;
			speed = 4;
			lt = false;
			break;
			
		case 3:
			health = 5;
			speed = 1;
			lt = false;
			break;
			
		case 4:
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

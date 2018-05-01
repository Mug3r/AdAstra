package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import CollsionDetection.CollisionDetection;
import Graphics.ImageManager;
import levelManagement.GameStateManager;
import levelManagement.PlayerInfo;

public class Enemy extends MapObject {
	//type of enemy
	private int type;
	
	public Enemy(int x, int y, int t) {
		//calls super constructor to position enemy
		super(x,y);
		//enemy specific constructor
		this.type = t;
		if(t <= 3){
			sprite = ImageManager.alienSprites[t];
		}
		
		dy = 0;
		
		r=0;
		w = 100;
		h = 100;
		cw = 80;
		ch = 80;
		
		switch(t) {
			
		case 0:
			health = 1;
			dx = -1;
			lt = true;
			break;
		
		case 1:
			health = 3;
			dx = 3;
			lt = false;
			break;
			
		case 2:
			health = 2;
			dx = -4;
			lt = true;
			break;
			
		case 3:
			health = 1;
			dx = 3;
			lt = false;
			break;
			
		}
		
	}
	//if hit lose health and if dead give the player experience and score
	public void hit(int d){
		super.hit(d);
		if(health <= 0){
			isDead = true;
			PlayerInfo.incrementScore((type+1) * 5);
			GameStateManager.incrementPlayer((type+1)*2);
		}
	}
	
	public void update() {
		super.update();
		
		if(!isDead){
			
			if(rotatingLt){
				angle += 1;
			}
		
			
			if(!lt) {dx = Math.abs(dx) ;} else {dx = -(Math.abs(dx));} 
			x += dx;
			
		} else{return;}
		
	}
	//overrides the offscreen method becuase we only care if the enemies go out the bottom of the screen otherwise they will end up back on screen
	@Override
	public boolean isOffScreen(){		
		return (y + (h+15) > GamePanel.HEIGHT);
	}
		
	//getters and setters
	public boolean getLt() {return lt;}
	public void setLt(boolean b) {lt = b;}
	

	public int getR() {return r;}


	
	
}

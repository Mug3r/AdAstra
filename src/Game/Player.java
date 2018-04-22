package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Graphics.ImageManager;
import levelManagement.GameStateManager;

public class Player extends MapObject {

	private boolean lt = false, rt = false;
	private boolean firing = false;
	private int power = 0;
	
	


	public Player() {	

		super((GamePanel.WIDTH - 80)/2, (GamePanel.HEIGHT - (ImageManager.playerSprites[0].getHeight()/2 + 60)));
		level  = 0;
		health = 1;
		sprite = ImageManager.playerSprites[level];

		speed = 4;
		w = (int)(ImageManager.playerSprites[level].getWidth()/2);
		h  = (int)(ImageManager.playerSprites[level].getHeight()/2);
		cw = ImageManager.playerSprites[level].getWidth() - 10;
		ch = ImageManager.playerSprites[level].getHeight() - 5;

	}


	public void update() {

		if(lt) {
			if(x > 0) {
				x -= speed;
			} else {
				lt = false;
				x = 0;
			}
		}

		if(rt) {
			if((x + w) < GamePanel.WIDTH) {
				x += speed;
			} else {
				rt = false;
				x = GamePanel.WIDTH - w;
			}
		}

		if(rotatingRt){
			if(angle <= 88){
				angle += 2;
			} else {
				
				rotatingRt = false;
				angle = 88;
				
			}
		}
		if(rotatingLt){
			if(angle >= -88){
				angle -= 2;
			} else{
				
				rotatingLt = false;
				angle = -88;
				
			}
		}
		
		if(firing){			
			GameStateManager.addBullet();
		}

	}

	@Override
	public void draw(Graphics2D g){

		if(!isDead){

			rotate((int)(Math.abs(angle)/angle), angle, g);


		}

	}	
	
	public int getPower(){return power;}

	public void setLt(boolean b) {lt = b;}
	public void setRt(boolean b) {rt = b;}
	public void setFiring(boolean b){firing = b;}
	public void increasePower(int a){power += a; System.out.println(power);}
	public void levelUp(){power = 0; level++; sprite = ImageManager.playerSprites[level];}
}

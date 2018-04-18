package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Graphics.ImageManager;

public class Player extends MapObject {
	
	private boolean lt = false, rt = false;
	
	
	public Player(ImageManager im) {
		
		super(288, 690, im);
		level  = 0;
		sprite = im.playerSprites[level];
		
		speed = 4;
		w = 80;
		h  = 90;
		cw = 50;
		ch = 50;
		
	}


	public void update() {
		
		if(lt) {
			if((x - r) > 0) {
				x -= speed;
			} else {
				lt = false;
				x = 0 + r;
			}
		}
		
		if(rt) {
			if((x + r*2) < GamePanel.WIDTH) {
				x += speed;
			} else {
				rt = false;
				x = GamePanel.WIDTH - r*2;
			}
		}
		
	}
	
	public void setLt(boolean b) {lt = b;}
	public void setRt(boolean b) {rt = b;}

}

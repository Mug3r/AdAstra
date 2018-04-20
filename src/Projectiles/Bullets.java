package Projectiles;

import Game.MapObject;
import Graphics.ImageManager;

public class Bullets extends MapObject {

	private int damage = 1;
	
	public Bullets(int sx, int sy, ImageManager im) {
		super(sx, sy);
		
	}

	public int getDamage() {
		return damage;
	}

}

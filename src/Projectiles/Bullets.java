package Projectiles;

import java.awt.Graphics2D;

import Game.GamePanel;
import Game.MapObject;
import Game.Player;
import Graphics.ImageManager;

public class Bullets extends MapObject {

	private int damage = 1;
	
	
	public Bullets(Player p) {
		
		super(p.getX() + (p.getW()/2 - 15), p.getY());
		sprite = ImageManager.laser[p.getLevel()];
		angle = p.getAngle();
		dx = Math.cos(Math.toRadians(angle)- Math.PI/2)*8;
		dy = Math.sin(Math.toRadians(angle)- Math.PI/2)*8;
		w = sprite.getWidth();
		h = sprite.getHeight();
		cw = w - 5;
		ch = h - 31;

	}
	
	public void Update(){
		super.update();
		
	}
	
	public void draw(Graphics2D g){
		
		if(damage > 0){
			rotate((int)(Math.abs(angle)/angle), angle, g);
		}
	}
	
	public void setDamage(int d){
		damage = d;
	}
	public int getDamage() {
		return damage;
	}


}

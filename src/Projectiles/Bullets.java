package Projectiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.MapObject;
import Game.Player;
import Graphics.ImageManager;
import Main.GamePanel;

public class Bullets extends MapObject {

	private int damage = 1;
	private int BSpeed = 8;

/*
 * Bullets range from missles to lasers flying across the screen on impact with anything a bullet loses
 * damage only when it has a damage of 0 is it removed from the screen allowing bullets to with
 * for example 3 damage to hit 3 1 health enemies and then be removed 
 */
	public Bullets(Player p, int x, int y, int d) {

		super(x,y);
		sprite = ImageManager.laser[p.getLevel()];
		angle = p.getAngle();
		dx = Math.cos(Math.toRadians(angle)- Math.PI/2)*BSpeed;
		dy = Math.sin(Math.toRadians(angle)- Math.PI/2)*BSpeed;
		w = sprite.getWidth();
		h = sprite.getHeight();
		cw = w - 5;
		ch = h - 20;
		setDamage(d);
	}

	public void Update(){
		super.update();
		if(damage <= 0){
			damage = 0;
		}
	}

	public void draw(Graphics2D g){

		if(damage > 0){
			rotate((int)(Math.abs(angle)/angle), angle, g);
		}
	}

	public void hit(){damage--;}

	public void setDamage(int d){
		if(d >= 0) {
			damage = d;
		}
		else {
			damage = 0;
		}
	}
	public int getDamage() {
		return damage;
	}
	public BufferedImage getSprite(){return sprite;}
	public void setBSpeed(int a){speed = a;}


}

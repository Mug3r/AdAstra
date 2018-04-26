package Projectiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;
import Game.MapObject;
import Game.Player;
import Graphics.ImageManager;

public class Bullets extends MapObject {

	private int damage = 1;
	private int BSpeed = 8;

	public Bullets(Player p, int x, int y) {

		super(x,y);
		sprite = ImageManager.laser[p.getLevel()];
		angle = p.getAngle();
		dx = Math.cos(Math.toRadians(angle)- Math.PI/2)*BSpeed;
		dy = Math.sin(Math.toRadians(angle)- Math.PI/2)*BSpeed;
		w = sprite.getWidth();
		h = sprite.getHeight();
		cw = w - 5;
		ch = h - 20;
		damage = 1;
	}

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

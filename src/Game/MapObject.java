package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Graphics.ImageManager;

public abstract class MapObject {
	
	protected int x;
	protected int y;
	protected int speed;
	
	protected int health;
	
	protected boolean lt, rt;
	protected int level = 0;
	protected int type = 0;
	
	protected int w;
	protected int h;
	protected int cw;
	protected int ch;
	protected int r;
	
	protected double angle;
	protected boolean rotatingLt;
	protected boolean rotatingRt;
	protected boolean isDead;
	
	
	protected BufferedImage sprite;
	
	public MapObject(int sx, int sy){
		
		x = sx;
		y = sy;
		
		lt = false;
		rt = false;
		
		rotatingLt = false;
		rotatingRt = false;
		isDead = false;
		
	}
	
	public MapObject(int x, int y, int speed, int w, int h, int cw, int ch, int r, double angle){
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.w = w;
		this.h = h;
		this.cw = cw;
		this.ch = ch;
		this.r = r;
		this.angle = angle;
		
		isDead = false;
		
		rotatingLt = false;
		rotatingRt = false;
		
	}
	
	public void update(){}
	public void draw(Graphics2D g){
		
		if(!isDead){
			g.rotate(angle);
			g.drawImage(sprite, (int)x, (int)y, w, h, null);
			g.rotate(-(angle));
		}
	
	}	
	
	public boolean isRect(){		
		if((this.w == 0) && (this.h == 0)){return false;}
		else{return true;}
		
	}
	
	public void levelUp(int level){
		
		this.level += level;
		
	}
	
	public void hit(int d){health -= d;}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getSpeed() {return speed;}	
	public int getW() {return w;}	
	public int getH() {return h;}	
	public int getCw() {return cw;}	
	public int getCh() {return ch;}	
	public int getR() {return r;}
	public int getLevel(){return level;}
	
	
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setSpeed(int speed) {this.speed = speed;}
	public void setW(int w) {this.w = w;}
	public void setH(int h) {this.h = h;}
	public void setCw(int cw) {this.cw = cw;}
	public void setCh(int ch) {this.ch = ch;}
	public void setR(int r) {this.r = r;}
}

package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Graphics.ImageManager;

public abstract class MapObject {
	
	protected double x;
	protected double y;
	protected double speed;
	
	protected int w;
	protected int h;
	protected int cw;
	protected int ch;
	protected int r;
	
	protected double angle;
	protected boolean rotatingLt;
	protected boolean rotatingRt;
	
	private ImageManager im;
	
	protected BufferedImage sprite;
	
	public MapObject(double x, double y, ImageManager im){
		
		this.x = x;
		this.y = y;
		this.im = im;
		
		rotatingLt = false;
		rotatingRt = false;
		
	}
	
	public MapObject(double x, double y, double speed, int w, int h, int cw, int ch, int r, double angle, ImageManager im){
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.w = w;
		this.h = h;
		this.cw = cw;
		this.ch = ch;
		this.r = r;
		this.angle = angle;
		this.im = im;
		
		rotatingLt = false;
		rotatingRt = false;
		
	}
	
	public void update(){}
	public void draw(Graphics2D g){
		
		g.rotate(angle);
		g.drawImage(sprite, (int)x, (int)y, w, h, null);
		g.rotate(-(angle));
	
	}	
	
	public boolean isRect(){		
		if((this.w == 0) && (this.h == 0)){return false;}
		else{return true;}
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public double getSpeed() {return speed;}	
	public int getW() {return w;}	
	public int getH() {return h;}	
	public int getCw() {return cw;}	
	public int getCh() {return ch;}	
	public int getR() {return r;}
	
	
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
	public void setSpeed(double speed) {this.speed = speed;}
	public void setW(int w) {this.w = w;}
	public void setH(int h) {this.h = h;}
	public void setCw(int cw) {this.cw = cw;}
	public void setCh(int ch) {this.ch = ch;}
	public void setR(int r) {this.r = r;}
}

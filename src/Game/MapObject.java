package Game;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Graphics.ImageManager;

public abstract class MapObject {
	
	protected int x;
	protected int y;
	protected double dx, dy;
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
	
	protected double angle = 0;
	protected boolean rotatingLt;
	protected boolean rotatingRt;
	protected boolean isDead;
	private boolean OffScreen = false;
	
	
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
	
	public void update(){
		x += dx;
		y += dy;
		
		if(x + w < 0){OffScreen = true;}
		if(x > GamePanel.WIDTH){OffScreen = true;}
		if(y + h < 0){OffScreen = true;}
		if(y > GamePanel.HEIGHT){OffScreen = true;}
	}
	public void draw(Graphics2D g){
		
		if(!isDead){
			g.drawImage(sprite, (int)x, (int)y, (int)(w),(int) (h), null);
		}
	
	}	
	
	public boolean isRect(){		
		if((this.w == 0) && (this.h == 0)){return false;}
		else{return true;}
		
	}
	
	public void levelUp(int level){
		
		this.level += level;
		
	}
	

public void rotate(int direction, double deg, Graphics2D g) {		
		//create new context
		Graphics2D imgG2D = (Graphics2D) (g.create(x - w, y - h, w*4, h*4));
		int lx = w, ly = h;
		
		//rotate from the center using radians
		imgG2D.rotate(Math.toRadians(deg), w + (w/2), h + (w/2));
		
		imgG2D.drawImage(sprite, lx, ly, w, h, null);
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
	public int getHealth(){return health;}
	public double getAngle(){return angle;}
	
	
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setSpeed(int speed) {this.speed = speed;}
	public void setW(int w) {this.w = w;}
	public void setH(int h) {this.h = h;}
	public void setCw(int cw) {this.cw = cw;}
	public void setCh(int ch) {this.ch = ch;}
	public void setR(int r) {this.r = r;}
	public void setRLT(boolean b) {rotatingLt = b;}
	public void setRRT(boolean b) {rotatingRt = b;}
	public boolean isOffScreen(){return OffScreen;}
}

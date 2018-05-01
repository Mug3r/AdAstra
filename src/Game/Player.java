package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Graphics.ImageManager;
import Projectiles.Bullets;
import levelManagement.GameStateManager;
import levelManagement.Level;
import levelManagement.PlayerInfo;

public class Player extends MapObject {

	private boolean lt = false, rt = false;
	private boolean firing = false;
	private int power = 0;

	private long last, elapsed;
	private int tt = 400;
	private String user;


	public Player(String u) {	

		super((GamePanel.WIDTH - 80)/2, (GamePanel.HEIGHT - (ImageManager.playerSprites[0].getHeight()/2 + 60)));
		level  = 0;
		health = 0;
		sprite = ImageManager.playerSprites[level];

		speed = 4;
		w = (int)(ImageManager.playerSprites[level].getWidth()/2);
		h  = (int)(ImageManager.playerSprites[level].getHeight()/2);
		cw = ImageManager.playerSprites[level].getWidth() - 10;
		ch = ImageManager.playerSprites[level].getHeight() - 5;
		
		user = u;

	}


	public void update() {

		if(health > 5){
			health = 5;
			PlayerInfo.incrementScore(level*GameStateManager.getLevel());
		}
		
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
			addBullet(GameStateManager.getCurrentLevel(), this);
		}

	}

	@Override
	public void draw(Graphics2D g){

		if(!isDead){

			rotate((int)(Math.abs(angle)/angle), angle, g);


		}

	}

	public void addBullet(Level l, Player p) {
		int lx = x + (int)((double)(Math.sin(Math.toRadians((Math.abs(angle)/angle)*angle))));
		int ly = y + (int)((double)(Math.cos(Math.toRadians((Math.abs(angle)/angle)*angle))));
		int cx = lx + sprite.getWidth()/4 - ImageManager.laser[p.getLevel()].getWidth()/2;
		int cy = ly + sprite.getHeight()/4;
		elapsed = System.currentTimeMillis() - last;
		//if(elapsed > tt){

		switch(level){
		case 0:

			if(elapsed > tt){l.addBullet(this, cx, cy, PlayerInfo.bulletDamage[level], 8);last = System.currentTimeMillis();}
			break;
		case 1:
			if(elapsed > tt){l.addBullet(this,  cx, cy, PlayerInfo.bulletDamage[level], 5);last = System.currentTimeMillis();}					
			break;
		case 2:
			if(elapsed > tt){l.addBullet(this,  cx, cy, PlayerInfo.bulletDamage[level], 8);last = System.currentTimeMillis();}					
			break;
		case 3:
			if(elapsed > tt){l.addBullet(this,  cx, cy, PlayerInfo.bulletDamage[level], 10);last = System.currentTimeMillis();}			
			break;
		case 4:
			if(elapsed > tt){l.addBullet(this,  cx, cy, PlayerInfo.bulletDamage[level], 7);last = System.currentTimeMillis();}				
			break;
		case 5:
			if(elapsed > tt){l.addBullet(this,  cx, cy, PlayerInfo.bulletDamage[level], 6);	last = System.currentTimeMillis();}				
			break;
		case 6:
			if(elapsed > tt){l.addBullet(this,  cx, cy, PlayerInfo.bulletDamage[level], 8);last = System.currentTimeMillis();}					
			break;
		case 7:
			if(elapsed > tt){l.addBullet(this,  cx, cy, PlayerInfo.bulletDamage[level], 20);last = System.currentTimeMillis();}					
			break;
		case 8:
			if(elapsed > tt){l.addBullet(this, lx +30, cy, PlayerInfo.bulletDamage[level], 9);last = System.currentTimeMillis();					
			l.addBullet(this, cx + 35, cy, PlayerInfo.bulletDamage[level], 9);last = System.currentTimeMillis();}
			break;
		}

		//last = System.currentTimeMillis();
		//}

	}

	public void levelUp(){
		power = 0;
		level++; 
		sprite = ImageManager.playerSprites[level]; 
		w = sprite.getWidth()/2; h = sprite.getHeight()/2; 
		y = GamePanel.HEIGHT - (h +100);
	}
	
	public void setLevel(int l){
		power = 0;
		level = l; 
		sprite = ImageManager.playerSprites[level]; 
		w = sprite.getWidth()/2; h = sprite.getHeight()/2; 
		y = GamePanel.HEIGHT - (h +100);
	}
	
	public String getName() {
		
		return user;
		
	}
	
	public void setName(String n) {
		user = n;
	}

	public int getPower(){return power;}

	public void setLt(boolean b) {lt = b;}
	public void setRt(boolean b) {rt = b;}
	public void setFiring(boolean b){firing = b;}
	public void increasePower(int a){power += a; System.out.println(power);}

	public void setBulletDelay(int a){tt = a;}
}

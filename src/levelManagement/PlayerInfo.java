package levelManagement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;
import Game.Player;
import Graphics.ImageManager;

public class PlayerInfo {

	private Player p;
	private int lives;
	private int level;
	private int power;
	private long score;
	private BufferedImage life, powerLevel;
	private int[] requiredPower = {
			
			50, 50, 5, 5, 5, 10, 15, 20, 500 
			
	};
	
	
	public PlayerInfo(Player p){
		this.p = p;
		score = 0;
		lives = p.getHealth();
		level = p.getLevel();
		power = p.getPower();
		
		life = ImageManager.lives;
		powerLevel = ImageManager.power;
	}
	
	public void update(){
		lives = p.getHealth();
		level = p.getLevel();
		power = p.getPower();
		
		if(power >= requiredPower[level]){
			p.levelUp();
			level = p.getLevel();
			power = p.getPower();
		}
	}
	
	public void draw(Graphics2D g){
		
		g.setColor(new Color(255,255,255,10));
		g.drawRect(10, GamePanel.HEIGHT - 40, GamePanel.WIDTH - 25, 22);
		g.fillRect(10, GamePanel.HEIGHT - 40, GamePanel.WIDTH - 25, 22);
		g.setColor(new Color(255,246,0,200));
		g.drawRect(15, GamePanel.HEIGHT - 35, GamePanel.WIDTH -30, 17);
		g.setColor(new Color(255,246,0));
		g.fillRect(15, GamePanel.HEIGHT - 35,(int) ((double)(GamePanel.WIDTH -30)*((double)power/(double)requiredPower[level])), 17);
		g.drawString(" " + power, 0, 50);
		
		for(int i = 0; i < lives; i ++){
			g.drawImage(life, 20 + (i * life.getWidth()), 20,null);
		}
		
	}
	
	public void addScore(double a){
		score += a;
	}
	
}

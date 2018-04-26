package levelManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Game.GamePanel;
import Game.Player;
import Graphics.ImageManager;

public class PlayerInfo {

	private Player p;
	private static int lives;
	private int level;
	private int glevel;
	private int power;
	private int wave;
	private static int score = 0;
	private BufferedImage life, powerLevel;
	private int[] requiredPower = {
			
			100, 120, 150, 200, 210, 200, 250, 250, 500 
			
	};
	private int[] firingRates = {
		400, 700, 400, 1000, 300, 700, 600, 1000, 200	
	};
	public static int[] bulletDamage={
			1, 2, 1, 2, 1, 2, 2, 3, 1
	};
	public static String[] shipName = {
		"Savato S", "Agate B", "Agate L", "Agate SE", "Cortano L", "Savato X", "Agate MKII", "Anubis A", "Astrea F"
	};
	
	
	public PlayerInfo(Player p){
		this.p = p;
		score = 0;
		lives = p.getHealth();
		level = p.getLevel();
		glevel = GameStateManager.getLevel();
		power = p.getPower();
		p.setBulletDelay(firingRates[level]);
		life = ImageManager.lives;
		powerLevel = ImageManager.power;
	}
	
	public void update(){
		lives = p.getHealth();
		level = p.getLevel();
		power = p.getPower();
		glevel = GameStateManager.getLevel();
		if(level <= 7){
			
		if(power >= requiredPower[level]){
			p.levelUp();
			level = p.getLevel();
			power = p.getPower();
			p.setBulletDelay(firingRates[level]);
		}
		
		} else{
			power = requiredPower[8];
		}
	}
	
	public void draw(Graphics2D g){
		
	
		g.drawImage(ImageManager.topBar, 0, 0, GamePanel.WIDTH, ImageManager.topBar.getHeight(), null);
		
		g.setColor(new Color(255,255,255,100));
		g.drawRect(15, GamePanel.HEIGHT - 35, GamePanel.WIDTH -30, 17);
		g.setColor(new Color(255,246,0));
		g.fillRect(15, GamePanel.HEIGHT - 35,(int) ((double)(GamePanel.WIDTH -30)*((double)power/(double)requiredPower[level])), 17);
		
		for(int i = 0; i < lives; i ++){
			g.drawImage(life, (GamePanel.WIDTH/2 - lives*(life.getWidth()/2) + (life.getWidth()+10)*i), 35,null);
		}
	
		g.setFont(new Font("VeriBest Gerber 0", Font.PLAIN, 17));
		g.setColor(new Color(255,255,255));
		g.drawString("Damage:" + bulletDamage[level] , 740, 39);
		g.setFont(new Font("VeriBest Gerber 0", Font.PLAIN, 20));
		g.drawString(shipName[level] + "",750, 18);
		g.drawString(GameStateManager.getCurrentWave() +"/"+GameStateManager.getMaxWaves(), 450, 25);
		g.drawString(score + "", 90, 32);
	}
	public void loseLife(){p.hit(1);}
	public static int getLives(){return lives;}

	public static void incrementScore(int d) {score += d;}
	
}

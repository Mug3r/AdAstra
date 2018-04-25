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
	private int lives;
	private int level;
	private int power;
	private long score;
	private int wave;
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
		"Savato Standard", "Agate Basic", "Agate Light", "Agate Extended", "Cortano Light", "Savato Imperial", "Agate MKII", "Anubis A-Type", "Astrea F-Type"
	};
	
	
	public PlayerInfo(Player p){
		this.p = p;
		score = 0;
		lives = p.getHealth();
		level = p.getLevel();
		power = p.getPower();
		p.setBulletDelay(firingRates[level]);
		life = ImageManager.lives;
		powerLevel = ImageManager.power;
	}
	
	public void update(){
		lives = p.getHealth();
		level = p.getLevel();
		power = p.getPower();
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
		
		g.setColor(new Color(255,255,255,100));
		//g.drawRect(15, 15, GamePanel.WIDTH - 25, 22);
		g.fillRect(15, 15, life.getWidth()*lives + 10, life.getHeight()+10);
		g.setColor(new Color(255,246,0,200));
		g.drawRect(15, GamePanel.HEIGHT - 35, GamePanel.WIDTH -30, 17);
		g.setColor(new Color(255,246,0));
		g.fillRect(15, GamePanel.HEIGHT - 35,(int) ((double)(GamePanel.WIDTH -30)*((double)power/(double)requiredPower[level])), 17);
		
		for(int i = 0; i < lives; i ++){
			g.drawImage(life, 20 + (i * life.getWidth()), 20,null);
		}
		
		g.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
		g.setColor(new Color(255,255,255, 244));
		g.drawString("Ship: ",575, 53);
		g.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		g.drawString(shipName[level] + "",620, 55);
		g.drawString("Wave: " + GameStateManager.getCurrentWave() +"/"+GameStateManager.getMaxWaves(), 620, 80);
	}
	
	public void addScore(double a){
		score += a;
	}
	
}
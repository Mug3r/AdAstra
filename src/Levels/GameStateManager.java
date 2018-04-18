package Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Game.Cluster;
import Game.CollisionDetection;
import Game.Enemy;
import Game.GamePanel;
import Game.Player;
import Graphics.ImageManager;
import Projectiles.Bullets;

public class GameStateManager {
	
	private final int MENUSTATE = 0, GAMESTATE = 1, PAUSED = 2, VICTORY = 3, DEFEAT = 4;

	private final int[] STATES = {
			MENUSTATE,GAMESTATE,PAUSED, VICTORY, DEFEAT
	}; 
	private int state;
	private int level;
	private ArrayList<Enemy> e;
	private ArrayList<Cluster> c;
	private ArrayList<Bullets> bullets;
	private Player p;
	private CollisionDetection cd;
	private ImageManager im;
	
	private int choice = 0;
	
	public GameStateManager(ImageManager im) {
		
		state = STATES[0];
		level = 0;
		c = new ArrayList<Cluster>();
		bullets = new ArrayList<Bullets>();
		p = new Player(im);
		cd = new CollisionDetection();
		this.im = im;
	}
	
	public void loadLevel(int l) {
		
		state = GAMESTATE;
		level = l;
		
		switch(level) {
		
			case 0:
				
				c.add(new Cluster(10,10,im));

				break;
		
			case 1:
				

				break;
			
			case 2:
				break;
			
			case 3:
				break;
			
			case 4:
				break;
			
			case 5:
				break;
			
			case 6:
				state = STATES[3];
				level = 0;
				break;
		
		}
		
	}
	
	public void update() {
		
		if(state == MENUSTATE) {
			level = 0;
		}
		
		if(state == GAMESTATE) {
			
			if(c.size() <= 0) {
				loadLevel(level++);
			} else {
				
				
				for(int i =0; i < c.size(); i++){
				c.get(i).update();
				}
				
				
				p.update();
				
				
			}
			
			
			
		}
		
	}
	public void draw(Graphics2D g) {
		
		switch(state) {
		
			case 0:
				
				
				Font stringFont = new Font( "SansSerif", Font.PLAIN, 26 );
				
				g.setColor(new Color(0,12,128));
				g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
				
				
				g.setFont(stringFont);
				
				switch(choice) {
				
					case 0:
						g.setColor(new Color(255,255,0));
						g.drawString("Play", 280, 480);
						g.fillRect(230, 460, 20, 20);
						g.drawRect(240, 440, 120, 60);
						g.setColor(new Color(255,255,255));
						g.drawString("Quit", 280, 540);
							break;
					
					case 1:
						g.setColor(new Color(255,255,255));
						g.drawString("Play", 280, 480);
						g.setColor(new Color(255,255,0));
						g.drawString("Quit", 280, 540);
						g.fillRect(230, 520, 20, 20);
						g.drawRect(240, 500, 120, 60);
							break;
							
				}
				
				
				break;
				
			case 1:
				g.setColor(new Color(0,0,0));
				g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
				g.setColor(new Color(255,255,0));
				g.drawLine(0, 730, 600, 730);
				for(int i = 0; i < c.size(); i++) {
					c.get(i).draw(g);
				}
				p.draw(g);
				
				break;
				
			case 2:
				break;
			
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		switch(state) {
		
		case 0:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				choice--;
				if(choice < 0) {
					choice = 1;
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				choice++;
				if(choice > 1) {
					choice = 0;
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(choice == 0) {
					
					state = GAMESTATE;
					
				} else if(choice == 1) {
					System.exit(2);
				}
			}
			break;
		
		case 1:
			
			if(e.getKeyCode() == KeyEvent.VK_A) {p.setLt(true);}
			if(e.getKeyCode() == KeyEvent.VK_D) {p.setRt(true);}
			
			break;
		
		
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		if(state == 1) {
			if(e.getKeyCode() == KeyEvent.VK_A) {p.setLt(false);}
			if(e.getKeyCode() == KeyEvent.VK_D) {p.setRt(false);}
		}
		
	}
	
}

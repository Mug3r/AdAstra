package Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Game.Cluster;
import Game.CollisionDetection;
import Game.Enemy;
import Game.GamePanel;
import Game.Player;
import Graphics.ImageManager;
import Projectiles.Bullets;

public class GameStateManager {
	
	private static final int MENUSTATE = 0, GAMESTATE = 1, PAUSED = 2, OVER = 3;

	private static int state;
	private Player p;
	private CollisionDetection cd;
	private ImageManager im;
	
	private Level[] levels;
	private Level[] menus;
	
	private int menu = 0;
	private int level = 0;
	
	
	
	public GameStateManager() {
		
		menus = new Level[3];
		menus[0] = new MainMenu(im);
		menus[1] = new PauseMenu(im);
		menus[2]  = new GameOverMenu(im);
		
		levels = new Level[10];
		
		state = MENUSTATE;
		

		p = new Player();
	}
	
	public static void start() {
		
		state = GAMESTATE;	
		
	}
	
	public void update() {
		
		if(state == MENUSTATE) {
			menus[menu].Update();
		}
		
		if(state == GAMESTATE) {			
			levels[level].Update();
		}
		
	}
	public void draw(Graphics2D g) {		
			
		if(state == MENUSTATE) {
			menus[menu].draw(g);
		}
		
		if(state == GAMESTATE) {			
			levels[level].draw(g);
		}
				
	}
	
	public void keyPressed(KeyEvent e) {
		
		if(state == MENUSTATE) {
			menus[menu].keyPress(e);
		}
		
		if(state == GAMESTATE) {			
			levels[level].keyPress(e);
		}
		
		
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		
		if(state == GAMESTATE) {			
			levels[level].keyRelease(e);
		}
		
	}
	
	public static void escape(){
		
	}
	
	public static void exit(){
	    Object[] message = {"Are you sure you want to exit?"};


	    Object[] options = { "Yes", "No" };
	    int n = JOptionPane.showOptionDialog(new JFrame(),
	            message, "",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
	            options, options[1]);
	    if(n == JOptionPane.OK_OPTION){ // Afirmative
	        System.exit(2);
	    }

	}
	
}

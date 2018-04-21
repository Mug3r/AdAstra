package Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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

	private static int state,lastState;
	private Player p;
	private CollisionDetection cd;
	private ImageManager im;

	private Level[] levels;
	private Level[] menus;

	private static int menu = 0;
	private static int level = 0;



	public GameStateManager() {

		menus = new Level[3];
		menus[0] = new MainMenu(im);
		menus[1] = new PauseMenu(im);
		menus[2]  = new GameOverMenu(im);

		lastState = MENUSTATE;

		levels = new Level[10];

		state = MENUSTATE;


		p = new Player();
	}

	public static void start() {
		
		
		state = GAMESTATE;	

	}

	public static void pause(){
		menu = 1;
		lastState = state;
		state = PAUSED;
	}

	public static void resume(){
		state = lastState;
	}

	public static void nextLevel(){
		level++;
	}

	public static void escape(){
		if(state == MENUSTATE){
			exit();
		} else if(state == PAUSED){
			resume();
		} else if(state == GAMESTATE){
			pause();
		}
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

		if(state == PAUSED){
			levels[level].draw(g);
			menus[menu].draw(g);
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

	public static void exit(){
		Object[] message = {"Are you sure you want to exit?"};


		Object[] options = { "Yes", "No" };
		int n = JOptionPane.showOptionDialog(new JFrame(),
				message, "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[1]);
		if(n == JOptionPane.OK_OPTION){ // Affirmative
			System.exit(2);
		}

	}

	public void mousePressed(MouseEvent e) {

		if(state == MENUSTATE) {
			menus[menu].mousePress(e);
		}

		if(state == GAMESTATE) {			
			levels[level].mousePress(e);
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(state == MENUSTATE) {
			menus[menu].mouseMove(e);
		}

		if(state == GAMESTATE) {			
			levels[level].mouseMove(e);
		}
	}

	public void mouseClicked(MouseEvent e) {

		if(state == MENUSTATE) {
			menus[menu].mouseClick(e);
		}

		if(state == GAMESTATE) {			
			levels[level].mouseClick(e);
		}

	}

	public void mouseEntered(MouseEvent e) {

		if(state == MENUSTATE) {
			menus[menu].mouseEnter(e);
		}

		if(state == GAMESTATE) {			
			levels[level].mouseEnter(e);
		}

	}

	public void mouseExited(MouseEvent e) {

		if(state == MENUSTATE) {
			menus[menu].mouseExit(e);
		}

		if(state == GAMESTATE) {			
			levels[level].mouseExit(e);
		}

	}

	public void mouseReleased(MouseEvent e) {

		if(state == MENUSTATE) {
			menus[menu].mouseRelease(e);
		}

		if(state == GAMESTATE) {			
			levels[level].mouseRelease(e);
		}

	}

}

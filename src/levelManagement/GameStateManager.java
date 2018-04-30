package levelManagement;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Game.Player;
import Graphics.ImageManager;
import gameLevels.*;

public class GameStateManager {

	private static final int MENUSTATE = 0, GAMESTATE = 1, PAUSED = 2;

	private static int state,lastState;
	private ImageManager im;
	private static boolean transitioning = false;
	private static Transition T;

	private static Player p;
	private static PlayerInfo pI;
	private static String username;

	private static int tDelay = 3000;
	private static long lastTime;

	private static Level[] levels;
	private static Level[] menus;

	private static int menu = 0;
	private static int level = -1;



	public GameStateManager() {

		menus = new Level[5];
		menus[0] = new MainMenu();
		menus[1] = new PauseMenu();
		menus[2]  = new GameOverMenu();
		menus[3] = new VictoryMenu();


		lastState = MENUSTATE;

		levels = new Level[10];
		levels[0] = new level_1();
		levels[1] = new level_2();
		levels[2] = new level_3();
		levels[3] = new level_4();
		levels[4] = new level_5();
		levels[5] = new level_6();
		levels[6] = new level_7();
		levels[7] = new level_8();
		levels[8] = new level_9();
		levels[9] = new level_10();


		state = MENUSTATE;

		menus[menu].startLevel();

		username = JOptionPane.showInputDialog("Enter a username" , null);

		p = new Player(username);
		pI = new PlayerInfo(p);
	}

	public void update() {

		if(!transitioning){

			if(state == MENUSTATE || state == PAUSED) {
				menus[menu].Update();
				pI.update();
			}

			if(state == GAMESTATE) {			
				levels[level].Update();
				p.update();
				pI.update();
			}

		} 
		else{
			T.Update();
			if(tDelay < System.currentTimeMillis() - lastTime){
				nextLevel();
			}
		}
	}

	public void draw(Graphics2D g) {		

		if(!transitioning){

			if(state == MENUSTATE) {
				menus[menu].draw(g);
			}

			if(state == GAMESTATE) {			
				levels[level].draw(g);
				p.draw(g);
				pI.draw(g);
			}

			if(state == PAUSED){
				levels[level].draw(g);
				p.draw(g);
				menus[menu].draw(g);

			}
		}

		else{
			T.draw(g);
		}

	}


	public static void nextLevel(){

		transitioning = false;
		state = GAMESTATE;

		level++;
		levels[level].startLevel();

	}

	public static void win() {
		menu = 3;
		state = MENUSTATE;
	}

	public static void Lose() {
		pI.loseLife();
		pI.update();
		menu = 2;
		state = MENUSTATE;

	}

	public static void Highscores() {

		menus[4] = new HighscoresMenu(menu);
		menu = 4;
		lastState = state;
		state = MENUSTATE;

	}



	public static void resetLevel(){
		levels[level].reset();
		state = GAMESTATE;
		GameOverMenu.once = false;
		System.out.println("2");
	}

	public static void restartGame(){
		menus = null;
		levels = null;
		p = null;
		pI = null;

		menus = new Level[5];
		menus[0] = new MainMenu();
		menus[1] = new PauseMenu();
		menus[2]  = new GameOverMenu();
		menus[3] = new VictoryMenu();


		lastState = MENUSTATE;

		levels = new Level[10];
		levels[0] = new level_1();
		levels[1] = new level_2();
		levels[2] = new level_3();
		levels[3] = new level_4();
		levels[4] = new level_5();
		levels[5] = new level_6();
		levels[6] = new level_7();
		levels[7] = new level_8();
		levels[8] = new level_9();
		levels[9] = new level_10();

		menu = 0;
		level = 0;
		state = MENUSTATE;

		menus[menu].startLevel();

		username = JOptionPane.showInputDialog("Enter a username" , null);

		p = new Player(username);
		pI = new PlayerInfo(p);

	}




	public static void transition(int s, String m){

		tDelay = s;
		T = new Transition(m);		
		transitioning = true;
		lastTime = System.currentTimeMillis();
	}


	public static void pause(){
		menu = 1;
		lastState = state;
		state = PAUSED;
	}

	public static void resume(){
		state = lastState;
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

	public static void exit(){

		if(menu == 0) {
			Object[] message = {"Are you sure you want to exit?"};


			Object[] options = { "Yes", "No" };
			int n = JOptionPane.showOptionDialog(new JFrame(),
					message, "",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[1]);
			if(n == JOptionPane.OK_OPTION){ // Affirmative
				System.exit(2);
			}
		} else if(menu != 2 || menu != 3) {
			int t = state;
			menu = menus[menu].lastMenu;
			state = lastState;
			lastState = t;
		} else {
			restartGame();
		}

	}



	public void keyPressed(KeyEvent e) {

		if(!transitioning){
			if(state == MENUSTATE || state == PAUSED) {
				menus[menu].keyPress(e);
			}

			if(state == GAMESTATE) {

				if(e.getKeyCode() == KeyEvent.VK_A){

					p.setLt(true);

				}
				if(e.getKeyCode() == KeyEvent.VK_D){
					p.setRt(true);
				}

				if(e.getKeyCode() == KeyEvent.VK_LEFT){

					p.setRLT(true);

				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					p.setRRT(true);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					p.setFiring(true);
				}

				levels[level].keyPress(e);

			}
		}



	}

	public void keyReleased(KeyEvent e) {

		if(!transitioning){


			if(state == GAMESTATE) {

				if(e.getKeyCode() == KeyEvent.VK_A){

					p.setLt(false);

				}
				if(e.getKeyCode() == KeyEvent.VK_D){
					p.setRt(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){

					p.setRLT(false);

				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					p.setRRT(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					p.setFiring(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					p.levelUp();
				}
			}

		}

	}


	public static Level getCurrentLevel(){
		return levels[level];
	}

	public static int getCurrentWave(){
		return levels[level].getCurrentWave();
	}

	public static int getMaxWaves(){
		return levels[level].getWavesLeft();
	}

	public static int getLevel(){
		return level;
	}

	public static void incrementPlayer(int amount){p.increasePower(amount);}

}

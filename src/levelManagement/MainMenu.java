package levelManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Game.GamePanel;
import Graphics.ImageManager;
import Audio.SoundLoader;

public class MainMenu extends Level{

	private int choice;
	private Background strip;
	//Just for whether the player is looking at the instructions screen :)
	private boolean instructMeSenpai = false;
	
	/*The games main menu behaves like a standard menu 
	 * with a few specific choices, shows at the start of the game and takes
	 * the users name for scoring purposes(unimplemented)
	 */
	public MainMenu(){
		
		super(0);
		
		choice = 0;
		strip = new Background(ImageManager.MainMenuStrip);
		strip.setdX(0.6);
		strip.setdY(0);
		bgdx = -2;
		bgdy = 0;
		running = true;
		
	}

	public void Update(){
		super.Update();
		strip.Update();


	}

	public void draw(Graphics2D g){

		super.draw(g);
		strip.Render(g);
		g.drawImage(ImageManager.Title, 0,0,null);

		if(!instructMeSenpai){
			g.drawImage(ImageManager.menu[choice], 0, 0, null);	
		} else{
			g.drawImage(ImageManager.menu[choice], 0, 0, null);
		}
	}

	//Interrupts
	
	public void keyPress(KeyEvent e){

		
		if(!instructMeSenpai){
			super.keyPress(e);
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				choice--;
				if(choice < 1) {
					choice = 3;
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				choice++;
				if(choice > 3) {
					choice = 1;
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(choice == 0) {

				} else if(choice == 1){
					GameStateManager.transition(1000, "- Level 1 -");;
				} else if(choice == 2) {
					GameStateManager.exit();
				} else if(choice == 3){
					GameStateManager.Highscores();
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_I){
				instructMeSenpai = !instructMeSenpai;
				choice = 4;
			}

		} else{
			if(e.getKeyCode() == KeyEvent.VK_LEFT){

				if(choice <= 4){
					choice = 5;
				} else{
					choice--;
				}

			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){

				if(choice >= 5){
					choice = 4;
				} else{
					choice++;
				}

			}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){

				choice = 0;
				instructMeSenpai = false;
			}

		}


	}

	public void keyReleased(KeyEvent e){

	}

}

package levelManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Graphics.ImageManager;

public class GameOverMenu extends Level {

	private int choice = 0;
	private Background strip;
	private boolean cont = true;
	public static boolean once = false;
	/*Menu displayed when the player loses a life or is out of lives
	 *A bit buggy in terms of restarting at the main menu but everything works out somehow
	 */
	public GameOverMenu() {
		super(0);
		bgdx = 5;
		bgdy = 0;
		choice = 0;
		strip = new Background(ImageManager.MainMenuStrip);
		strip.setdX(0.6);
		strip.setdY(0);

		running = true;
	}

	public void Update(){
		super.Update();
		strip.Update();
		if(!once){
			if(PlayerInfo.getLives() < 0){
				cont = false;
			}
			once = true;
		}

	}

	public void draw(Graphics2D g){

		super.draw(g);
		strip.Render(g);
		g.drawImage(ImageManager.Title, 0,0,null);

		if(cont){
			g.drawImage(ImageManager.gameOver[choice], 0, 0, null);
		} else{
			g.drawImage(ImageManager.gameOver[choice + 3], 0, 0, null);
		}

	}

	public void keyPress(KeyEvent e){
		super.keyPress(e);
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			choice--;
			if(choice < 0) {
				choice = 2;
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) {

			choice++;
			if(choice > 2) {
				choice = 0;
			}
		}
		
		if(cont){
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(choice == 0){
					GameStateManager.resetLevel();
				}
				if(choice == 1){
					GameStateManager.exit();
				}
				if(choice == 2){
					GameStateManager.Highscores();
				}
				
			}
			
		} else{
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				
				if(choice == 0){
					System.out.println("We did done good");
					GameStateManager.restartGame();
					
				}
				if(choice == 1){
					GameStateManager.exit();
				}
				if(choice == 2){
					GameStateManager.Highscores();
				}
				
			}
			
		}

	}


}





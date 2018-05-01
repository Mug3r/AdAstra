package levelManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Graphics.ImageManager;

public class VictoryMenu extends Level {

	private int choice = 0;
	private Background strip;
	private boolean cont = true;
	public static boolean once = false;

	/*
	 * The menu shown when the player wins
	 */
	public VictoryMenu() {
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

	}

	public void draw(Graphics2D g){

		super.draw(g);
		strip.Render(g);
		g.drawImage(ImageManager.Title, 0,0,null);

		g.drawImage(ImageManager.victory, 0, 0, null);
		

	}

	public void keyPress(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameStateManager.restartGame();
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			GameStateManager.exit();
		}
	}


}





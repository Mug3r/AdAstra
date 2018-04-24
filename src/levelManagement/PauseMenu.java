package levelManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Game.GamePanel;
import Graphics.ImageManager;

public class PauseMenu extends Level {

	private int choice;
	private int a = 255, da = -5; 

	public PauseMenu() {
		super(0);
		choice = 0;
		bgdx = 5;
		bgdy = 0;
	}

	public void draw(Graphics2D g){

		g.setColor(new Color(255,255,255,100));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		Font titleFont = new Font("OCR A Extended", Font.PLAIN, 150);
		
		g.setFont(titleFont);
		
		g.setColor(new Color(255,255,255,180));
		g.fillRect(0, 150, GamePanel.WIDTH, 200);
		g.setColor(new Color(30,49,86, a));
		g.drawString("PAUSED", (GamePanel.WIDTH/2 - 250), 300);
		
		Font stringFont = new Font( "Segoe UI Light", Font.PLAIN, 40 );

		g.setFont(stringFont);

		switch(choice) {

		case 0:
			g.setColor(new Color(255,255,255,180));
			g.fillRect(0, 640, 900, 60);
			g.setColor(new Color(30,49,86));
			g.drawString("Resume", (GamePanel.WIDTH/2 - 75), 680);	
			g.setColor(new Color(255,255,255,180));
			g.drawString("Quit", (GamePanel.WIDTH/2 - 45), 740);
			break;

		case 1:
			g.setColor(new Color(255,255,255, 180));
			g.drawString("Resume", (GamePanel.WIDTH/2 - 75), 680);
			g.setColor(new Color(255,255,255,180));
			g.fillRect(0, 700, 900, 60);
			g.setColor(new Color(30,49,86));
			g.drawString("Quit", (GamePanel.WIDTH/2 - 45), 740);
			break;

		}
	}


	public void Update(){
		super.Update();
		if(a < 50){
			da = 5;
		} else if(a > 250){
			da= -5;
		}
		a += da;
	}

	public void keyPress(KeyEvent e){

		super.keyPress(e);

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
				GameStateManager.resume();
			} else if(choice == 1) {
				GameStateManager.exit();
			}
		}


	}
}

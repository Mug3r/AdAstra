package levelManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Game.GamePanel;
import Graphics.ImageManager;

public class Transition extends Level {

	private int a = 255, d = -5;
	
	public Transition(){
		bg = new Background(ImageManager.stars);
		bg.setdX(-1);
		bg.setdY(-0.3);
		
	}
	
	public void Update(){
		super.Update();
		a += d;
		if(a + d <= 0){
			d= 5;
		}
		if(a + d >= 255){
			d = -5;
		}
	}
	
public void draw(Graphics2D g){
		
		super.draw(g);
		
		//g.drawImage(ImageManager.Title, 0,150,null);
		
		Font stringFont = new Font( "Segoe UI Light", Font.PLAIN, 100 );

		g.setFont(stringFont);
		
			g.setColor(new Color(255,255,255, a));
			g.drawString("Loading...", 300, 380);	
			
		}

		
	
}

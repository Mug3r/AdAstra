package levelManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Graphics.ImageManager;
import Main.GamePanel;

public class Transition extends Level {

	private int a = 0, d = 1;
	private String Message = "Loading...";
	/*
	 * A screen shown between levels to show which level the player is on 
	 */
	public Transition(){
		super(0);
		bg.setdX(-1);
		bg.setdY(-0.3);
		running = true;
	}
	public Transition(String m){
		super(0);
		bg.setdX(-0.5);
		bg.setdY(-0.3);
		Message = m;
		running = true;
	}
	
	public void Update(){
		super.Update();
		a += d;
		if(a + d <= 0){
			d = 5;
		}
		if(a + d >= 255){
			d = -5;
		}
	}
	
public void draw(Graphics2D g){
		
		super.draw(g);
		
		//g.drawImage(ImageManager.Title, 0,150,null);
		
		Font stringFont = new Font( "OCR A Extended", Font.PLAIN, 90 );

		g.setFont(stringFont);
		
			g.setColor(new Color(255,255,255, a));
			g.drawString(Message, 110, 510);	
			
		}

		
	
}

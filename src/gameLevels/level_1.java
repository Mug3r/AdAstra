package gameLevels;

import Game.GamePanel;
import Graphics.ImageManager;
import levelManagement.Background;
import levelManagement.Level;
import levelManagement.Transition;

public class level_1 extends Level {

	private static final int STARTINGY = -100;

	public level_1(){
		super(1);
		bgdx = 0;
		bgdy = -0.3;
	
		diffConst = 2;
		rowsE = 2;
		colsE = 2;
		colsH = 3;
		rowsH = 3;
		
		maxWaves = 5;
		
		nextLevel = "- Level 2 -";
	}
	

}

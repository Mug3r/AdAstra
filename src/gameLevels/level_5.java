package gameLevels;

import levelManagement.Level;

public class level_5 extends Level {

	private static final int STARTINGY = -100;

	public level_5(){
		super(1);
		bgdx = 0;
		bgdy = -0.6;
	
		diffConst = 3;
		rowsE = 2;
		colsE = 3;
		colsH = 6;
		rowsH = 3;
		
		wave = 0;
		
		maxWaves = 15;
		
		nextLevel = "- Level 6 -";
	}
	
}

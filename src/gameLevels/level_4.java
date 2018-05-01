package gameLevels;

import levelManagement.Level;

public class level_4 extends Level {

	private static final int STARTINGY = -100;

	public level_4(){
		super(1);
		bgdx = 0;
		bgdy = -0.5;
	
		diffConst = 2;
		rowsE = 2;
		colsE = 4;
		colsH = 5;
		rowsH = 2;
		
		wave = 0;
		
		maxWaves = 10;
		
		nextLevel = "- Level 5 -";
	}
	
}

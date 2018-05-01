package gameLevels;

import levelManagement.Level;

public class level_2 extends Level {
	
	private static final int STARTINGY = -100;

	public level_2(){
		super(1);
		bgdx = 0;
		bgdy = -0.4;
	
		diffConst = 3;
		rowsE = 2;
		colsE = 2;
		colsH = 3;
		rowsH = 2;
		
		wave = 0;
		
		maxWaves = 7;
		
		nextLevel = "- Level 3 -";
	}
}

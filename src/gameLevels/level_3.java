package gameLevels;

import levelManagement.Level;

public class level_3 extends Level {

	private static final int STARTINGY = -100;

	public level_3(){
		super(1);
		bgdx = 0;
		bgdy = -0.5;
	
		diffConst = 2;
		rowsE = 1;
		colsE = 4;
		colsH = 4;
		rowsH = 2;
		
		wave = 0;
		
		maxWaves = 10;
		
		nextLevel = "- Level 4 -";
	}
	
}

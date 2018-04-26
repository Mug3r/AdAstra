package gameLevels;

import levelManagement.Level;

public class level_8 extends Level {

	public level_8(){
		super(1);
		bgdx = 0;
		bgdy = -0.7;
	
		diffConst = 3;
		rowsE = 4;
		colsE = 4;
		colsH = 6;
		rowsH = 3;
		
		wave = 0;
		
		maxWaves = 15;
		
		nextLevel = "- Level 9 -";
	}
	
}

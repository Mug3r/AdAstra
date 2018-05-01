package gameLevels;

import levelManagement.Level;

public class level_7 extends Level {

	public level_7(){
		super(1);
		bgdx = 0;
		bgdy = -0.8;
	
		diffConst = 3;
		rowsE = 3;
		colsE = 4;
		colsH = 5;
		rowsH = 4;
		
		wave = 0;
		
		maxWaves = 15;
		
		nextLevel = "- Level 8 -";
	}
	
}

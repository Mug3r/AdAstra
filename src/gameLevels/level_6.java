package gameLevels;

import levelManagement.Level;

public class level_6 extends Level {

	public level_6(){
		super(1);
		bgdx = 0;
		bgdy = -0.7;
	
		diffConst = 3;
		rowsE = 2;
		colsE = 4;
		colsH = 7;
		rowsH = 2;
		
		wave = 0;
		
		maxWaves = 15;
		
		nextLevel = "- Level 7 -";
	}
	
}

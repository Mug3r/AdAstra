package gameLevels;

import levelManagement.Level;

public class level_10 extends Level {

	public level_10(){
		super(1);
		bgdx = 0;
		bgdy = -0.6;
	
		diffConst = 4;
		rowsE = 1;
		colsE = 6;
		colsH = 6;
		rowsH = 2;
		
		wave = 0;
		
		maxWaves = 15;
		
		nextLevel = "VICTORY";
	}
	
}

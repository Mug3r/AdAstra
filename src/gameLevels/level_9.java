package gameLevels;

import levelManagement.Level;

public class level_9 extends Level {

	public level_9(){
		super(1);
		bgdx = 0;
		bgdy = -0.6;
	
		diffConst = 3;
		rowsE = 4;
		colsE = 6;
		colsH = 6;
		rowsH = 4;
		
		wave = 0;
		
		maxWaves = 15;
		
		nextLevel = "~ Level 10 ~";
	}
	
}

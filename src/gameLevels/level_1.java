package gameLevels;

import Graphics.ImageManager;
import levelManagement.Background;
import levelManagement.Level;
import levelManagement.Transition;

public class level_1 extends Level {

	private int in = 0;
	private static final int STARTINGY = 100;

	public level_1(){
		super(1);
		bg.setdY(-0.3);

		createCluster(20, STARTINGY, 2, 3, 0);


	}

	public void removedCluster(int index){
		super.removeCluster(index);
		if(index != in){
			in--;
		}
		in--;
	}

	public void Update(){
		super.Update();
		boolean allClear = true;
		for(int i = 0; i < Clusters.size(); i++){


			if(!Clusters.get(i).allDead){
				allClear = false;
			}
		}
		if(!allClear){
			if(in >= Clusters.size()){
				in = Clusters.size();
				in--;
			}
		}

		if(in <= 5){
			if(!allClear){
				if((Clusters.get(in).getY() > 200)){
					createCluster(50,STARTINGY, (int)(1+Math.random()*2), (int)(1+Math.random()*2), (int)(Math.random()*3));
					in++;
				}
			} else{

				createCluster(50,STARTINGY, (int)(1+Math.random()*3), (int)(1+Math.random()*5), (int)(Math.random()*2));

			}

		}
	}

}

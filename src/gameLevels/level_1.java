package gameLevels;

import Game.GamePanel;
import Graphics.ImageManager;
import levelManagement.Background;
import levelManagement.Level;
import levelManagement.Transition;

public class level_1 extends Level {

	private int in = 0, waves = 5;
	private static final int STARTINGY = -100;

	public level_1(){
		super(1);
		bgdx = 0;
		bgdy = -0.3;
		
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
		} else if(allClear){
			if(waves <= 0 ){
				levelComplete();
			}
		}

		if(in <= 5){
			int t = (int)(Math.random()*3);
			int r = (int)(1+Math.random()*2);
			int c = (int)(1+Math.random()*2);
			if(!allClear){
				if((Clusters.get(in).getY() > 200)){
					
					if(t == 0 || t == 3){
						createCluster((GamePanel.WIDTH - (c*ImageManager.alienSprites[0].getWidth() + 10)),(0 - (r*ImageManager.alienSprites[0].getHeight() + 10)), r, c, t);
						waves--;
					} else{
						createCluster(50,(0 - (r*ImageManager.alienSprites[0].getHeight() + 10)), r, c, t); 
						waves--;
					}
					in++;
				}
			} else{

				t = (int)(Math.random()*2);
				r = (int)(1+Math.random()*3);
				c = (int)(1+Math.random()*5);
				
				if(t == 0 || t == 3){
					createCluster((GamePanel.WIDTH - (c*ImageManager.alienSprites[0].getWidth() + 10)),(0 - (r*ImageManager.alienSprites[0].getHeight() + 10)), r, c, t);
					waves--;
				} else{
					createCluster(50,(0 - (r*ImageManager.alienSprites[0].getHeight() + 10)), r, c, t);
					waves--;
				}
				

			}

		}
		
	}

}

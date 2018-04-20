package Game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Graphics.ImageManager;
import Projectiles.Bullets;

public class Cluster extends MapObject {

	private Enemy[][] e;
	private int rows,columns;
	private int rightmostX;

	public Cluster(int sx, int sy, ImageManager im) {
		super(sx, sy);

		createStdCluster();

	}

	private void createStdCluster(){

		rows = 2;
		columns = 5;

		e = new Enemy[rows][columns];

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				e[i][j] = new Enemy((x + (j * (w + 35))), (y + (i*(h+35))),0);
				e[i][j].setLt(lt);
			}
		}
	}

	public void draw(Graphics2D g){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				e[i][j].draw(g);
			}
		}
	}

	public void update(ArrayList<Bullets> b){
		
		x = e[0][0].getX();
		y = e[0][0].getY();
		
		for(int i = 0; i < b.size(); i++){
			
			for(int j = 0; j < rows; j++){
				for(int k = 0; k < columns; k++){
					
					if(CollisionDetection.collidesWith(e[j][k], b.get(i))){						
						e[j][k].hit(b.get(i).getDamage());
						b.remove(i);						
					}
					
				}
			}
			
		}
		
		if(x <= 0){
			lt = false;
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					e[i][j].setY(e[i][j].getY()+e[i][j].getH());
					e[i][j].setLt(false);
				}
			}
		}
		
		if(((e[0][columns-1].getX() + e[0][columns-1].getW())) > GamePanel.WIDTH ){
			lt = true;
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					e[i][j].setY(e[i][j].getY()+e[i][j].getH());
					e[i][j].setLt(lt);
				}
			}
		}
		
		

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				e[i][j].update();
			}
		}

	}

}

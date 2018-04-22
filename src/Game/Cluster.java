package Game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Graphics.ImageManager;
import Projectiles.Bullets;
import levelManagement.GameStateManager;

public class Cluster extends MapObject {

	private Enemy[][] e;
	private int rows,columns;
	private int rightmostX;
	public boolean allDead = false;

	public Cluster(int sx, int sy, int rows, int cols, int type) {
		super(sx, sy);
		createCluster(rows, cols, type);
	}

	private void createCluster(int r, int c, int type){

		rows = r;
		columns = c;

		e = new Enemy[rows][columns];

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				e[i][j] = new Enemy((x + (j * (100))), (y + (i*(100))),type);
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
		
		
		for(int i = rows-1; i > -1; i--){
			for(int j = columns-1; j > -1; j--){
				if(!e[i][j].isDead){
					x = e[i][j].getX();
					y = e[i][j].getY();
				}
			}
		}
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(!e[i][j].isDead){
					rightmostX = Math.max(rightmostX, e[i][j].getX() + e[i][j].getW());
				}
			}
		}
		
		for(int i = 0; i < b.size(); i++){
			if(b.get(i).getDamage() == 0){
				b.remove(i);
			}
		}
		
		for(int i = 0; i < b.size(); i++){
			
			for(int j = 0; j < rows; j++){
				for(int k = 0; k < columns; k++){
					
					if(CollisionDetection.collidesWith(e[j][k], b.get(i))){						
						if(!e[j][k].isDead){
							e[j][k].hit(b.get(i).getDamage());
							b.get(i).setDamage(0);
							GameStateManager.incrementPlayer(5);
							}
						
					}
					
				}
			}
			
		}
		
		if(x <= 0){
			lt = false;
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					e[i][j].setY(e[i][j].getY()+(e[i][j].getH()/2));
					e[i][j].setLt(false);
				}
			}
		}
		
		if(rightmostX > GamePanel.WIDTH ){
			lt = true;
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					e[i][j].setY(e[i][j].getY()+(e[i][j].getH()/2));
					e[i][j].setLt(lt);
				}
			}
			rightmostX = x + e[0][0].getW();
		}
		
		allDead = true;

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				e[i][j].update();
				if(!e[i][j].isDead){
					allDead = false;
				}
			}
		}

	}

}

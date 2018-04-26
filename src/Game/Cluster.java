package Game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Graphics.ImageManager;
import Projectiles.Bullets;
import levelManagement.GameStateManager;
import levelManagement.PlayerInfo;

public class Cluster extends MapObject {

	private Enemy[][] e;
	private int rows,columns;
	private int rightmostX;
	public boolean allDead = false;
	private boolean elementOffScreen = false;

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
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(e[i][j].isOffScreen()){
					elementOffScreen = true;
				}
			}
		}
		
		if(elementOffScreen){
			GameStateManager.Lose();
		}

		if(y<100){
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					if(!e[i][j].isDead){
						e[i][j].setY(e[i][j].getY() + 2);
					}
				}
			}
		}

		for(int i = 0; i < b.size(); i++){
			if(b.get(i).getDamage() <= 0){
				b.remove(i);
			}
		}

		for(int i = 0; i < b.size(); i++){

			for(int j = 0; j < rows; j++){
				for(int k = 0; k < columns; k++){

					if(CollisionDetection.collidesWith(e[j][k], b.get(i))){						
						if(!e[j][k].isDead){
							int d = b.get(i).getDamage();
							int h = e[j][k].getHealth();
							e[j][k].hit(d);
							b.get(i).setDamage(d - h);
						}

					}

				}
			}

		}

		if(x <= 0){
			lt = false;
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					e[i][j].setY(e[i][j].getY()+(e[i][j].getH()/4));
					e[i][j].setLt(lt);
				}
			}
		}

		if(rightmostX > GamePanel.WIDTH ){
			lt = true;
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					e[i][j].setY(e[i][j].getY()+(e[i][j].getH()/4));
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

	public void reset(){
		
		x = 450;
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				e[i][j].setX(x + (j * (100)));
				e[i][j].setY(y + (i*(100)));
			}
		}
		elementOffScreen = false;
	}
}

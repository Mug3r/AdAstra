package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import CollsionDetection.CollisionDetection;
import Graphics.ImageManager;
import Projectiles.Bullets;
import levelManagement.GameStateManager;
import levelManagement.PlayerInfo;

public class Cluster extends MapObject implements CollisionDetection{

	
	//Resolves enemies into a dynamic nxm 2D array of enemies that move as a unit
	private Enemy[][] e;
	private int rows,columns;
	
	//rightmost boundary of the cluster
	private int rightmostX;
	//used to see if all enemies in cluster are dead or offScreen
	public boolean allDead = false;
	private boolean elementOffScreen = false;

	public Cluster(int sx, int sy, int rows, int cols, int type) {
		super(sx, sy);
		createCluster(rows, cols, type);
	}

	//creates a cluster of enemies of this size and type
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
	//draws
	public void draw(Graphics2D g){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				e[i][j].draw(g);
			}
		}
	}
	//Updates clusters checking for collisions with bullets or the player to remove health or lose a life respectively
	public void update(ArrayList<Bullets> b, Player p){

		//checks for the top right most enemy as the x position of the entire cluster for wall collision
		for(int i = rows-1; i > -1; i--){
			for(int j = columns-1; j > -1; j--){
				if(!e[i][j].isDead){
					x = e[i][j].getX();
					y = e[i][j].getY();
				}
			}
		}
		//Checks the rightmost x
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(!e[i][j].isDead){
					rightmostX = Math.max(rightmostX, e[i][j].getX() + e[i][j].getW());
				}
			}
		}
		//Checks if any enemies are at the bottom of the screen(Enemies overrides the isOffScreen method of Mapobject to only check for the bottom)
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(e[i][j].isOffScreen()){
					elementOffScreen = true;
				}
			}
		}
		//If an enemy is off screen lose a life
		if(elementOffScreen){
			GameStateManager.Lose();
		}
		//If the cluster is above a certain height make it mvoe down linearly
		if(y<100){
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					if(!e[i][j].isDead){
						e[i][j].setY(e[i][j].getY() + 2);
					}
				}
			}
		}
		//Checks for and removes spent bullets
		for(int i = 0; i < b.size(); i++){
			if(b.get(i).getDamage() <= 0){
				b.remove(i);
			}
		}
		for(int i = 0; i < b.size(); i++){
			if(b.get(i).isOffScreen()){
				b.remove(i);
			}
		}
		//bullet - enemy collision
		for(int i = 0; i < b.size(); i++){

			for(int j = 0; j < rows; j++){
				for(int k = 0; k < columns; k++){

					if(collidesWith(e[j][k], b.get(i))){						
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
		//Enenmy - Player collision
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(e[i][j] != null) {
					if(!e[i][j].isDead) {
						if(collidesWith(e[i][j], p)) {
							GameStateManager.Lose();
						}
					}
				}
			}
		}
		//Boundary value, moves the clsuter down if they are at a boundary (Left or right edge of screen)
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

		//Negative logic test for if all the enemies in a cluster are dead
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
	
	//resets clusters and moves enemies together for reseting the level

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
	//collsions
	@Override
	public boolean collidesWith(MapObject o1, MapObject o2) {


		switch(RectangularCollsion(o1,o2)){

		case 0:

			return circularCollision(o1,o2);

		case 1:

			return circleToRectCollision(o1, o2);

		case 2:

			return true;
		}

		return false;


	}

	@Override
	public boolean circularCollision(MapObject o1, MapObject o2) {

		double ax = o1.getX();
		double ay = o1.getY();
		double ar = o1.getR();

		ax = ax + (ar/2);
		ay = ay +(ar/2);

		double bx = o2.getX();
		double by = o2.getY();
		double br = o2.getR();

		bx = bx + (br/2);
		by = by +(br/2);

		double dx = bx - ax;
		double dy = by - ay;
		double dist = Math.sqrt((dx*dx)+(dy*dy));

		if(dist < br + ar){

			return true;

		}

		else {return false;}

	}

	@Override
	public boolean circleToRectCollision(MapObject o1, MapObject o2) {

		if(o1.isRect()){

			double ax = o1.getX();
			double ay = o1.getY();
			double aw = o1.getCw();
			double ah = o1.getCh();

			double bx = o2.getX();
			double by = o2.getY();
			double br = o2.getR();

			bx = bx + (br/2);
			by = by + (br/2);

			//B Circle A Rectangle

			double dx = bx - Math.max(ax, Math.min(bx, ax + aw));
			double dy = by - Math.max(ay, Math.min(by, ay + ah));

			return ((dx * dx + dy * dy) < (br * br));
		}

		else if(o2.isRect()){

			double ax = o2.getX();
			double ay = o2.getY();
			double aw = o2.getCw();
			double ah = o2.getCh();

			double bx = o1.getX();
			double by = o1.getY();
			double br = o1.getR();

			bx = bx + (br/2);
			by = by + (br/2);

			//B Circle A Rectangle

			double dx = bx - Math.max(ax, Math.min(bx, ax + aw));
			double dy = by - Math.max(ay, Math.min(by, ay + ah));

			return (dx * dx + dy * dy) < (br * br);
		}

		return false;
	}

	@Override
	public int RectangularCollsion(MapObject o1, MapObject o2) {
		Rectangle r1 = getRectangle(o1);
		Rectangle r2 = getRectangle(o2);

		if((r1.isEmpty()) && (r2.isEmpty())){
			return 0;
		} 

		else if(((r1.isEmpty()) && !(r2.isEmpty())) || ((!r1.isEmpty()) && (r2.isEmpty()))){
			return 1;
		}

		else if((r1.intersects(r2))){

			return 2;
		}

		return 3;
	}

	private static Rectangle getRectangle(MapObject o){
		if(o.isRect()){
			return new Rectangle((int)o.getX(), (int)o.getY(), o.getCw(), o.getCh());
		} else return new Rectangle(0,0,0,0);

	}
}

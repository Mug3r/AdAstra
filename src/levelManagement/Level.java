package levelManagement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.*;
import Graphics.ImageManager;
import Projectiles.Bullets;

public abstract class Level {

	protected int level;

	protected static Background bg;

	protected int lastMenu;
	protected ArrayList<Cluster> Clusters;
	protected ArrayList<Bullets> b;
	protected boolean running = false;
	protected int wave, maxWaves, in, diffConst, rowsE, rowsH, colsE, colsH;
	protected String nextLevel;


	protected double bgdx, bgdy;

	private int type = 0;

	/*Abstract class that manages all the levels in the game
	 * including menus and actual game levels
	 */

	public Level(int type){
		//determines if Menu or Level
		this.type = type;
		

		switch(type){

		case 0:
			bg = new Background(ImageManager.stars);
			break;

		case 1:
			Clusters = new ArrayList<Cluster>();
			b = new ArrayList<Bullets>();
			wave = 0;
			break;

		case 2:
			break;

		}

	}
	//Starts the current level and allows enemies/options to move/be selected
	public void startLevel(){
		running = true;
		bg.setdX(bgdx);
		bg.setdY(bgdy);
	}
	//used to advance the game with a default transition speed
	protected void levelComplete(){
		running = false;
		GameStateManager.transition(1700, nextLevel);
	}
	
	public void Update() {
		if(running){
			bg.Update();
		}
	}
	//Update the game Levels using a player object to check collsion with the player bullets and enemies
	public void Update(Player p){

		if(running){
			bg.Update();

				for(int i = 0; i < Clusters.size(); i++){
					Clusters.get(i).update(b,p);
					if(Clusters.get(i).allDead){
						removeCluster(i);
					}
				}

				for(int i = 0; i < b.size(); i++){
					b.get(i).update();
					if(b.get(i).getDamage() == 0){
						b.remove(i);
					}

				}

				for(int i = 0; i < b.size(); i++){
					if(b.get(i).isOffScreen()){
						b.remove(i);
					}
				}



				boolean allClear = true;
				for(int i = 0; i < Clusters.size(); i++){


					if(!Clusters.get(i).allDead){
						allClear = false;
					}
				}

				if(wave != maxWaves){
					if(!allClear){
						if(in >= Clusters.size()){
							in = Clusters.size();
							in--;
						}
					} else if(allClear){
						if(wave == maxWaves ){
							levelComplete();
						}
					}


					if(in <= 0) {
						in = 0;
					}

					if(in <= 5){
						int t = (int)(Math.random()*diffConst);
						int r = (int)(1+Math.random()*rowsE);
						int c = (int)(1+Math.random()*colsE);

						if(!allClear){
							if((Clusters.get(in).getY() > 200)){

								if(t == 0 || t == 3){
									createCluster((GamePanel.WIDTH - (c*ImageManager.alienSprites[0].getWidth() + 10)),(0 - (r*ImageManager.alienSprites[0].getHeight() + 10)), r, c, t);
									wave++;
								} else{
									createCluster(50,(0 - (r*ImageManager.alienSprites[0].getHeight() + 10)), r, c, t); 
									wave++;
								}
								in++;
							}
						} else{



							t = (int)(Math.random()*((diffConst-1)));
							r = (int)(1+Math.random()*rowsH);
							c = (int)(1+Math.random()*colsH);

							if(t == 0 || t == 3){
								createCluster((GamePanel.WIDTH - (c*ImageManager.alienSprites[0].getWidth() + 10)),(0 - (r*ImageManager.alienSprites[0].getHeight() + 10)), r, c, t);
								wave++;
							} else{
								createCluster(50,(0 - (r*ImageManager.alienSprites[0].getHeight() + 10)), r, c, t);
								wave++;
							}


						}
					}
				} else if(allClear){
					levelComplete();
				}

			
		}

	}
	//resets the level by moving all active clusters far up to the top of the screen and remove all the bullets on screen
	public void reset(){
		for(int i = 0; i < Clusters.size(); i++){
			Clusters.get(i).setY(-1205*i);
			Clusters.get(i).reset();
		}
		for(int i = 0; i < b.size(); i++){
			b.remove(i);
		}
		running = true;
	}
	
	public void draw(Graphics2D g){

		bg.Render(g);

		if(type == 1){

			for(int i = 0; i < Clusters.size(); i++){
				Clusters.get(i).draw(g);
			}	

			for(int i = 0; i < b.size(); i++){
				b.get(i).draw(g);
			}	
		}

	}
	
	/*Cluster controls to add or remove clusters of enemies, the in variable is used to mediate the
	 * the rate by which waves are spawned on  the screen and limit multiple waves spawning at once
	 * to two
	 */
	protected void createCluster(int x, int y, int rows, int col, int type){

		Clusters.add(new Cluster(x, y, rows, col, type));

	}
	protected void removeCluster(int index){
		Clusters.remove(index);
		if(index != in){
			in--;
		}
		in--;
	}

	//Getters
	
	public int getCurrentWave() {return wave;}
	public int getWavesLeft() {return maxWaves;}

	//Interrupts that are common to all levels and menus
	public void keyPress(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_Q){
			GameStateManager.exit();
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			GameStateManager.escape();
		}
	}
	//Adds a bulelt to the current level from the player and sets its speed
	public void addBullet(Player p, int x, int y, int d, int s) {

		b.add(new Bullets(p, x, y, d));
		b.get(b.size()-1).setBSpeed(s);
	}
	//disable the level if the player loses
	public void lose(){
		running = false;
	}
}

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
	protected ArrayList<Cluster> Clusters;
	protected ArrayList<Bullets> b;
	protected ArrayList<Enemy> sE;
	protected int mouseX, mouseY;
	protected Point mouse;
	protected boolean running = false;
	protected int wave, maxWaves, in, diffConst, rowsE, rowsH, colsE, colsH;


	protected double bgdx, bgdy;

	private int type = 0;

	public Level(){
		Clusters = new ArrayList<Cluster>();
		b = new ArrayList<Bullets>();
		sE = new ArrayList<Enemy>();
		mouse = new Point(0,0);
	}

	public Level(int type){

		this.type = type;
		mouse = new Point(0,0);

		switch(type){

		case 0:
			bg = new Background(ImageManager.stars);
			break;

		case 1:
			Clusters = new ArrayList<Cluster>();
			b = new ArrayList<Bullets>();
			sE = new ArrayList<Enemy>();
			wave = 0;
			break;

		case 2:
			break;

		}

	}

	public void startLevel(){
		running = true;
		bg.setdX(bgdx);
		bg.setdY(bgdy);
	}

	protected void levelComplete(){
		GameStateManager.nextLevel();
	}

	public void Update(){

		if(running){
			bg.Update();


			mouseX = (int) mouse.getX();
			mouseY = (int) mouse.getY();

			if(type == 1){


				for(int i = 0; i < Clusters.size(); i++){
					Clusters.get(i).update(b);
					if(Clusters.get(i).allDead){
						removeCluster(i);
					}
				}

				for(int i = 0; i < sE.size(); i++){
					for(int j = 0; j < b.size(); j++){

						if(CollisionDetection.collidesWith(b.get(j), sE.get(i))){
							sE.get(i).hit(b.get(j).getDamage());
							b.get(j).hit();
							b.remove(j);
						}

					}
				}

				for(int i = 0; i < sE.size(); i++){
					sE.get(i).update();
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

						t = (int)(Math.random()*(diffConst--));
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
			}

		}

	}

	public void draw(Graphics2D g){

		bg.Render(g);

		if(type == 1){


			for(int i = 0; i < sE.size(); i++){
				sE.get(i).draw(g);
			}

			for(int i = 0; i < Clusters.size(); i++){
				Clusters.get(i).draw(g);
			}	

			for(int i = 0; i < b.size(); i++){
				b.get(i).draw(g);
			}	
		}

	}
	public int getCurrentWave() {return wave;}
	public int getWavesLeft() {return maxWaves;}

	protected void removeCluster(int index){
		Clusters.remove(index);
		if(index != in){
			in--;
		}
		in--;
	}

	protected void createCluster(int x, int y, int rows, int col, int type){

		Clusters.add(new Cluster(x, y, rows, col, type));

	}

	protected void createSpecialEnemy(int x, int y, int type){

		sE.add(new Enemy(x,y,type));

	}

	public void keyPress(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_Q){
			GameStateManager.exit();
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			GameStateManager.escape();
		}
	}

	public void keyRelease(KeyEvent e){

	}

	public void mouseRelease(MouseEvent e) {

	}

	public void mouseExit(MouseEvent e) {

	}

	public void mouseEnter(MouseEvent e) {

	}

	public void mouseClick(MouseEvent e) {

	}

	public void mouseMove(MouseEvent e) {
		mouse = e.getPoint();
	}

	public void mousePress(MouseEvent e) {

	}

	public void addBullet(Player p, int x, int y, int d) {

		b.add(new Bullets(p, x, y, d));

	}

	public void addBullet(Player p, int x, int y) {

		b.add(new Bullets(p, x, y));

	}

}

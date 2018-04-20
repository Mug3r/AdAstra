package Levels;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.*;
import Graphics.ImageManager;
import Projectiles.Bullets;

public abstract class Level {

	protected int level;

	protected Background bg;
	protected ArrayList<Cluster> Clusters;
	protected ArrayList<Bullets> b;
	protected ArrayList<Enemy> sE;

	private int type = 0;

	public Level(){
		Clusters = new ArrayList<Cluster>();
		b = new ArrayList<Bullets>();
		sE = new ArrayList<Enemy>();
	}

	public Level(int type, int back){

		this.type = type;

		switch(type){

		case 0:
			bg = new Background(ImageManager.backgrounds[back]);
			break;

		case 1:
			Clusters = new ArrayList<Cluster>();
			b = new ArrayList<Bullets>();
			sE = new ArrayList<Enemy>();
			break;

		case 2:
			break;

		}

	}

	public void Update(){

		bg.Update();

		if(type == 1){

			for(int i = 0; i < Clusters.size(); i++){
				Clusters.get(i).update(b);
			}

			for(int i = 0; i < sE.size(); i++){
				for(int j = 0; j < b.size(); j++){

					if(CollisionDetection.collidesWith(b.get(j), sE.get(i))){
						sE.get(i).hit(b.get(j).getDamage());
						b.remove(j);
					}

				}
			}

			for(int i = 0; i < sE.size(); i++){
				sE.get(i).update();
			}

			for(int i = 0; i < b.size(); i++){
				b.get(i).update();
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

}

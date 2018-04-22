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

	protected Background bg;
	protected ArrayList<Cluster> Clusters;
	protected ArrayList<Bullets> b;
	protected ArrayList<Enemy> sE;
	protected int mouseX, mouseY;
	protected Point mouse;
	private long last, elapsed;
	private int tt = 400;

	private int type = 0;

	public Level(){
		Clusters = new ArrayList<Cluster>();
		b = new ArrayList<Bullets>();
		sE = new ArrayList<Enemy>();
		mouse = new Point(0,0);
		last = System.currentTimeMillis();
	}

	public Level(int type){

		this.type = type;
		mouse = new Point(0,0);

		switch(type){

		case 0:
			bg = new Background(ImageManager.MainMenu);
			break;

		case 1:
			Clusters = new ArrayList<Cluster>();
			b = new ArrayList<Bullets>();
			sE = new ArrayList<Enemy>();
			bg = new Background(ImageManager.stars);
			bg.setdX(0);
			break;

		case 2:
			break;

		}

	}

	public void Update(){

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
						b.remove(j);
					}

				}
			}

			for(int i = 0; i < sE.size(); i++){
				sE.get(i).update();
			}

			for(int i = 0; i < b.size(); i++){
				b.get(i).update();
				if(b.get(i).isOffScreen()){
					b.remove(i);
				}
			}
		}

	}

	public void draw(Graphics2D g){

		bg.Render(g);

		if(type == 1){
			
			g.setColor(new Color(235,235,235, 100));
			g.fillRect(15, 15, GamePanel.WIDTH - 30, 75);

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
	protected void removeCluster(int index){
		Clusters.remove(index);
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

	public void addBullet(Player p) {
		elapsed = System.currentTimeMillis() - last;
		if(elapsed > tt){
			b.add(new Bullets(p));
			last = System.currentTimeMillis();
		}

	}

}

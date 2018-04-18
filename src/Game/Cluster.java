package Game;

import java.awt.Graphics2D;

import Graphics.ImageManager;

public class Cluster extends MapObject {

	private Enemy[][] e;
	private int rows,columns;
	private int rightmostX;

	public Cluster(int sx, int sy, ImageManager im) {
		super(sx, sy, im);

		createStdCluster();

	}

	private void createStdCluster(){

		rows = 2;
		columns = 5;

		e = new Enemy[rows][columns];

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				e[i][j] = new Enemy((x + (j * (w + 35))), (y + (i*(h+35))),0, im);
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

	public void update(){
		
		x = e[0][0].getX();
		rightmostX = e[0][columns-1].getX();
		
		if(x <= 0){
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					e[i][j].setY(e[i][j].getY()+e[i][j].getH());
					e[i][j].setLt(false);
				}
			}
		}
		
		if(((e[0][columns-1].getX() + e[0][columns-1].getW())) > GamePanel.WIDTH ){
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					e[i][j].setY(e[i][j].getY()+e[i][j].getH());
					e[i][j].setLt(true);
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

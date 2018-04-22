package Game;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Graphics.ImageLoader;
import Graphics.ImageManager;
import levelManagement.GameStateManager;



public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{


	public static final int WIDTH = 900;
	public static int HEIGHT = 1000;
	public static final int SCALE = 1;


	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000/FPS;


	private BufferedImage image;
	private Graphics2D g;

	private GameStateManager gsm;
	private ImageManager im;


	public GamePanel() {

		super();

		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();

	}

	public void addNotify(){

		super.addNotify();
		if(thread == null){

			thread = new Thread(this);
			addKeyListener(this);
			thread.start();

		}
	}

	private void init(){

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if(screenSize.getHeight() < HEIGHT){
			HEIGHT = (int) screenSize.getHeight();
		}

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		im = new ImageManager();
		gsm = new GameStateManager();		

		running = true;


	}

	@Override
	public void run(){

		init();

		long start;
		long elapsed;
		long wait;


		while(running){

			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;

			wait = targetTime - elapsed / 1000000;
			if(wait < 0){wait = 5;}

			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();				
			}
		}

	}

	private void update(){

		gsm.update();

	}

	private void draw(){

		gsm.draw(g);

	}

	public void drawToScreen(){

		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH , HEIGHT , null);
		g2.dispose();

	}

	public void keyTyped(KeyEvent key){}
	public void keyPressed(KeyEvent key){gsm.keyPressed(key);}
	public void keyReleased(KeyEvent key){gsm.keyReleased(key);}


	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {gsm.mouseMoved(arg0);}	
	public void mouseClicked(MouseEvent arg0) {gsm.mouseClicked(arg0);}

	public void mouseEntered(MouseEvent arg0) {
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		gsm.mouseEntered(arg0);
	}

	public void mouseExited(MouseEvent arg0) {gsm.mouseExited(arg0);}
	public void mousePressed(MouseEvent arg0) {gsm.mousePressed(arg0);}
	public void mouseReleased(MouseEvent arg0) {gsm.mouseReleased(arg0);}

}

package Game;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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



public class GamePanel extends JPanel implements Runnable, KeyListener{


	public static final int WIDTH = 900;
	public static int HEIGHT = 1000;
	public static final int SCALE = 1;


	private Thread thread;
	private boolean running;
	private int FPS = 0, TPS = 0;

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

		 int frames = 0;
         int ticks = 0;        
         long startTime = System.nanoTime();
         final double ns = 1000000000.0 / 60.0; 
         double delta = 0;
     
         long timer = System.currentTimeMillis();
         requestFocus();
     
         while(running){
        
        long now = System.nanoTime();
        delta += (now - startTime) / ns;
        startTime = now;
        while (delta >= 1){
            update();
            ticks++;
            delta--;
        }
        
        draw();
        drawToScreen();
        frames++;
        
        if(System.currentTimeMillis() - timer > 1000){
            timer += 1000;
            
             FPS = frames;
             TPS = ticks;
            
            ticks = 0;
            frames = 0;
        }
     }

	}

	private void update(){

		gsm.update();

	}

	private void draw(){

		gsm.draw(g);
		g.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		g.drawString("FPS: " + FPS + " TPS: " +TPS, 15, 960);

	}

	public void drawToScreen(){

		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH , HEIGHT , null);
		g2.dispose();

	}

	public void keyTyped(KeyEvent key){}
	public void keyPressed(KeyEvent key){gsm.keyPressed(key);}
	public void keyReleased(KeyEvent key){gsm.keyReleased(key);}

}

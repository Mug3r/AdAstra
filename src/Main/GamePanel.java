package Main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Graphics.ImageManager;
import levelManagement.GameStateManager;


//GamePanel houses the game in general and runs on the main thread.
public class GamePanel extends JPanel implements Runnable, KeyListener{

	//Auto generated serialVersionID
	private static final long serialVersionUID = -478420113579111099L;
	
	//Dimensions of the screen area
	public static final int WIDTH = 900;
	public static int HEIGHT = 1000;
	public static final int SCALE = 1;

	//The thread on which the game runs
	private Thread thread;
	private boolean running;
	
	//Frames and Ticks(Updates) per second, used to keep track of how often thing on the screen are updated or drawn
	private int FPS = 0, TPS = 0;

	//The final "Image" that is drawn to the screen, other draw methods modify and add to this image before it is painted
	private BufferedImage image;
	private Graphics2D g;
	
	//GameStateManager, used to manage levels and the status of the game as a whole, I.E. player info like lives and score as well as which level to play when and whether or not the game is over or paused
	private GameStateManager gsm;

	//Constructor
	public GamePanel() {
		//Call the super constructor for the JPanel object
		super();
		//Setup
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();

	}
	
	//Super method for JPanels used to set up the window on the current operating system
	public void addNotify(){

		super.addNotify();
		//If a the thread for the game doesnt exist create a new one and set the game on that
		if(getThread() == null){

			setThread(new Thread(this));
			//Adds a new KeyListener for use
			addKeyListener(this);
			
			getThread().start();

		}
	}

	//Runs right at the start used to setup dimensions of the window and set up the image, GameStateManager and ImageManager
	private void init(){

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if(screenSize.getHeight() < HEIGHT){
			HEIGHT = (int) screenSize.getHeight();
		}

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gsm = new GameStateManager(this);		
		
		//Loop control
		running = true;

	}

	@Override
	public void run(){

		init();
		
		 //GameLoop controls
		 int frames = 0;
         int ticks = 0;        
         long startTime = System.nanoTime();
         final double ns = 1000000000.0 / 60.0; 
         double delta = 0;
     
         long timer = System.currentTimeMillis();
         requestFocus();
         
         //GameLoop
         while(running){
        
        long now = System.nanoTime();
        delta += (now - startTime) / ns;
        startTime = now;
        
        //Limit the number of times the game will update per second
        while (delta >= 1){
            update();
            ticks++;
            delta--;
        }
        //Draw to the screen as quickly as possible
        draw();
        drawToScreen();
        frames++;
        
        //Used to track the frames drawn/updates made in a second and display it
        if(System.currentTimeMillis() - timer > 1000){
            timer += 1000;
            
             FPS = frames;
             TPS = ticks;
            
            ticks = 0;
            frames = 0;
        }
     }

	}
	//Updates all objects in the game
	private void update(){

		gsm.update();

	}
	//Draws all objects onto the "Image"
	private void draw(){

		gsm.draw(g);
		g.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		g.drawString("FPS: " + FPS + " TPS: " +TPS, 15, 960);

	}

	//Draws the Image onto the screen
	public void drawToScreen(){

		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH , HEIGHT , null);
		g2.dispose();

	}

	//Key Inputs
	public void keyTyped(KeyEvent key){}
	public void keyPressed(KeyEvent key){gsm.keyPressed(key);}
	public void keyReleased(KeyEvent key){gsm.keyReleased(key);}

	public void restart() {
		GameStateManager.exit();
		System.gc();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boot.restart();
		
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

}

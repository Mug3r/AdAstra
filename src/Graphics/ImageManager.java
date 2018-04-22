package Graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

// this class loads resources on boot.
// spritesheets are taken from here.

public class ImageManager {
	
	public static BufferedImage[] playerSprites;
	public static BufferedImage[] alienSprites;
	public static BufferedImage[] laser;
	public static BufferedImage MainMenu, Title;
	public static BufferedImage MainMenuStrip;
	public static BufferedImage stars;
	public static BufferedImage basicLaser;
	public static BufferedImage power;
	public static BufferedImage lives;
	
	public ImageManager(){
		
		BufferedImage[] pSprites = load("/PlayerSprites/playerSprites.png", 200, 189);
		alienSprites = load("/alienSprites.png", 100, 100);
		MainMenu = ImageLoader.loadImage("/MainMenu.png");
		MainMenuStrip = ImageLoader.loadImage("/Strip.png");
		Title = ImageLoader.loadImage("/Title.png");
		stars = ImageLoader.loadImage("/stars.png");
		laser = load("/Projectiles/laser1.png", 30, 75);
		lives = ImageLoader.loadImage("/Projectiles/life.png");
		power = ImageLoader.loadImage("/Projectiles/power.png");
		
		playerSprites = new BufferedImage[pSprites.length + 2];
		for(int i = 0; i < playerSprites.length; i++){
			if(i != 7 && i != 8){
				playerSprites[i] = pSprites[i];
			}
			else if(i == 7){
				playerSprites[i] = ImageLoader.loadImage("/PlayerSprites/lvl8.png");
			}else if(i == 8){
				playerSprites[i] = ImageLoader.loadImage("/PlayerSprites/lvl9.png");
			}
			
		}
		
	}

	public static BufferedImage[] load(String s, int w, int h) {
		BufferedImage[] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(ImageManager.class.getResourceAsStream(s));
			int width = spritesheet.getWidth() / w;
			ret = new BufferedImage[width];
			
				for(int i = 0; i < width; i++) {
					ret[i] = spritesheet.getSubimage(i * w, 0, w, h);
					System.out.println("loaded");
				}
			
			return ret;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}
	
}
package Graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

// this class loads resources on boot.
// spritesheets are taken from here.

public class ImageManager {
	
	public static BufferedImage[] playerSprites;
	public static BufferedImage[] alienSprites;
	public static BufferedImage[] specialAliens;
	public static BufferedImage[] laser;
	public static BufferedImage[] menu;
	public static BufferedImage[] gameOver;
	public static BufferedImage MainMenu, Title;
	public static BufferedImage MainMenuStrip;
	public static BufferedImage stars;
	public static BufferedImage power;
	public static BufferedImage lives;
	public static BufferedImage topBar;
	public static BufferedImage highScores;
	public static BufferedImage victory;
	
	public ImageManager(){
		
		BufferedImage[] pSprites = load("/gfx/Sprites/PlayerSprites/playerSprites.png", 200, 189);
		alienSprites = load("/gfx/Sprites/AlienSprites/alienSprites.png", 100, 100);
		menu = load("/gfx/UI/MainMenu.png", 900, 1000);
		MainMenuStrip = ImageLoader.loadImage("/gfx/UI/Strip.png");
		Title = ImageLoader.loadImage("/gfx/UI/Title.png");
		stars = ImageLoader.loadImage("/gfx/UI/stars.png");
		laser = load("/gfx/Projectiles/laser1.png", 30, 75);
		lives = ImageLoader.loadImage("/gfx/Projectiles/life.png");
		power = ImageLoader.loadImage("/gfx/Projectiles/power.png");
		topBar = ImageLoader.loadImage("/gfx/UI/topBar.png");
		gameOver = load("/gfx/UI/gameOver.png", 900, 1000);
		highScores = ImageLoader.loadImage("/gfx/UI/highScores.png");
		victory = ImageLoader.loadImage("/gfx/UI/victory.png");
		
		playerSprites = new BufferedImage[pSprites.length + 2];
		
		for(int i = 0; i < playerSprites.length; i++){
			if(i != 7 && i != 8){
				playerSprites[i] = pSprites[i];
			}
			else if(i == 7){
				playerSprites[i] = ImageLoader.loadImage("/gfx/Sprites/PlayerSprites/lvl8.png");
			}else if(i == 8){
				playerSprites[i] = ImageLoader.loadImage("/gfx/Sprites/PlayerSprites/lvl9.png");
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
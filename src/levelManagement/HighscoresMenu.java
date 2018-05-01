package levelManagement;

import java.awt.Graphics2D;
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Graphics.ImageManager;

public class HighscoresMenu extends Level {

	private String currentScore;
	private int l = 0;
	private String[] names;
	private int[] scores;
	private int us;
	private String un;

	/*Unimplemented highscores menu that shows the players score against the highscores in the game
	 * scanner method wasnt working properly and ran out of time tbc later
	 */
	
	public HighscoresMenu(int lm) {
		super(0);
		bgdx = 5;
		bgdy = 0;

		lastMenu = lm;
		File hs = new File("/data/Highscores.txt");

		try {
			Scanner f = new Scanner(hs);

			while(f.hasNext()) {
				l++;
				f.nextLine();
			}
			names = new String[l];
			scores = new int[l];
			l = 0;
			f.close();
			loadScores();
		} catch(Exception e) {
			System.out.println("couldn't load Highscores");
			JOptionPane.showMessageDialog(null, "We couldnt load player high scores! :(");
			GameStateManager.escape();
		}

	}

	public void addScore(int score, String user){
		us = score;
		un = user;
		int rank = l;

		for(int i = 0; i < l; i++) {
			if(us > scores[i]) {
				rank = i;
			}
		}

		int[] t = new int[l+1];
		t[l] = us; 
		for(int i = 0; i < l+1; i++) {

			t[i] = scores[i];

		}


		for(int j = 0; j < l+1; j++) {
			for(int i = 1; i < l+1; i++) {
				if(t[i] > t[i--]) {
					int h = t[i--];
					t[i--] = t[i];
					t[i] = h;
				}
			}
		}

		scores = t;
		names = new String[l+1];
		
		for(int i = 0; i < l+1; i++) {
			
		}
		
	}



@SuppressWarnings("resource")
private void loadScores() {
	Scanner f = new Scanner("/data/Highscores.txt");

	while(f.hasNext()) {
		String i = f.nextLine();
		try {
			Scanner li = new Scanner(i).useDelimiter("#");
			scores[l] = li.nextInt();
			names[l] = li.next();
			l++;
			li.close();
		} catch(Exception e) {

		}
	}
	f.close();

}

public void draw(Graphics2D g) {

	g.drawImage(ImageManager.highScores, 0,0,null);

}

}

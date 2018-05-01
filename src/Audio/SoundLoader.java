package Audio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SoundLoader {
	
	public static void loadSound(String s){
		
		
		try{
			FileInputStream fileInputStream = new FileInputStream(s);
			Player player = new Player(fileInputStream);
			player.play();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	
}

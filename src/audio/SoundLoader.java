package audio;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundLoader {
	
	public static media loadMedia(String s){
	String bip = s;
	Media hit = new Media(new File(bip).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.play();
	}
}

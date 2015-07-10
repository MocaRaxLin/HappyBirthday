import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;


public class MP3 {
	private String filename;
	private Player player;
	public MP3() {
		this.filename = "src/sound/backSound.mp3";
	}
	public void close() {
		if (player != null)
			player.close();
	}
	public void play() {
		try {
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {	}

		new Thread() {
			public void run() {
				try {
					player.play();
				} catch (Exception e) {	}
			}
		}.start();
	}
}

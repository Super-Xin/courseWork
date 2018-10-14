package iLoveYou;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

public class loveCuiMusic {
	URL url;
	AudioClip bgMusic;
	File file = new File("music/bgmusic.wav");
	@SuppressWarnings("deprecation")
	public loveCuiMusic()
	{
		try {
			url = file.toURL();
			bgMusic = Applet.newAudioClip(url);
			startBgMusic();
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void startBgMusic()
	{
		bgMusic.loop();
	}

}

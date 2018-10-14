package day05;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Music {
	URL url;
	File f = new File("PlaneMusic/PlaneBgMusic.wav");
	AudioClip bgMusic;
	@SuppressWarnings("deprecation")
	public Music()
	{
		try {
			url = f.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bgMusic = Applet.newAudioClip(url);
		bgMusic.loop();
	}
}

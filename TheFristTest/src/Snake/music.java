package Snake;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class music {

	URL url1;
	File f = new File("snakemusic/bgmusic.wav");
	AudioClip bgmusic;
	AudioClip shibai;
	@SuppressWarnings("deprecation")
	public music()
	{
		try {
			url1 = f.toURL();
			bgmusic = Applet.newAudioClip(url1);
			f = new File("snakemusic/shibai.wav");
			url1 = f.toURL();
			shibai = Applet.newAudioClip(url1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void startBgMusic()
	{
		bgmusic.loop();
	}
	public void stoopBgMusic()
	{
		bgmusic.stop();
	}
	public void startShiBaiMusic()
	{
		shibai.play();
	}
}

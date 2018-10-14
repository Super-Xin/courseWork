package GoBangBoard;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Music {
	URL url;
	AudioClip bgMusic;
	AudioClip downBoard;
	AudioClip win;
	@SuppressWarnings("deprecation")
	public Music()
	{
		try {
			url = new File("GoBangMusic/win.wav").toURL();
			win = Applet.newAudioClip(url);
			url = new File("GoBangMusic/downboard.wav").toURL();
			downBoard = Applet.newAudioClip(url);
			url = new File("GoBangMusic/GoBangBgMusic.wav").toURL();
			bgMusic = Applet.newAudioClip(url);
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void startBgMusic()
	{
		bgMusic.loop();
	}
	public void stopBgMusic()
	{
		bgMusic.stop();
	}
	public void startWin()
	{
		win.play();
	}
	public void startDownBoard()
	{
		downBoard.play();
	}
}

package com.treehouse.test;

import javax.microedition.content.ContentHandler;
import javax.microedition.content.Invocation;
import javax.microedition.content.Registry;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

import net.rim.device.api.content.BlackBerryContentHandler;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.MainScreen;

public class LaunchMediaPlayer extends UiApplication {

	private EditField url;
	private EditField ef;
	private static LaunchMediaPlayer app;
	private MainScreen ms;

	public LaunchMediaPlayer() {
		url = new EditField("URL", "http://www.yo-yo.org/mp4/yu.mp4");
		ef = new EditField("Log:", "");
		ms = new MainScreen();
		ms.add(url);
		ms.add(ef);
		MenuItem mi1 = new MenuItem("Launch CHAPI", 1, 1) {
			public void run() {

				try {
					Registry r = Registry.getRegistry(LaunchMediaPlayer.class.getName());
					Invocation invocation = new Invocation(url.getText(), "video/3gpp",
		                    BlackBerryContentHandler.ID_MEDIA_CONTENT_HANDLER,
		                    false,
		                    ContentHandler.ACTION_OPEN);
		            r.invoke(invocation);
				} catch (Exception e) {
					log("Exception: " + e.getClass() + " - " + e.getMessage());
				}
				
//				Browser.getDefaultSession().displayPage(url.getText());
				
//				Registry registry = Registry.getRegistry(app.getClass().getName());
//				Invocation invocation = new Invocation(url.getText(), "video/mp4",
//						BlackBerryContentHandler.ID_MEDIA_CONTENT_HANDLER, false, ContentHandler.ACTION_OPEN);
//
//				invocation.setArgs(new String[] {BlackBerryContentHandler.MEDIA_ARGUMENT_VIEW_VIDEOS});
//
//				try {
//					registry.invoke(invocation);
//				} catch (Throwable t) {
//					log("Exception: " + t.getClass() + " - " + t.getMessage());
//				}
			}
		};
		MenuItem mi2 = new MenuItem("Launch Embedded", 1, 1) {

			public void run() {
				Player player;
				try {
				
	                Player p;
	                VideoControl vc;
	                
	                p = Manager.createPlayer("rtsp://en-ca.msmobvid.qt.msn.com/a1602/d2/PROD/ENGB_splash:ENGB_CelebCam/10101/6630abae-e7dd-42ee-960e-48c7e4083ceb.3gp");
	                        
	                p.realize();
	                p.prefetch();
	                p.start();    
					
//					player = Manager.createPlayer(url.getText());
//					player.realize();
//					player.prefetch();
//					
//					VideoControl vc = (VideoControl) player.getControl("VideoControl");
//					if (vc != null) {
//						Field videoField = (Field) vc.initDisplayMode(VideoControl.USE_GUI_PRIMITIVE, "net.rim.device.api.ui.Field");
//						vc.setDisplaySize(300, 300);
//						
//					}
//					player.start();

//					StreamConnection s = null;
//					s = (StreamConnection) Connector.open(url.getText());
//					HttpConnection c = (HttpConnection) s;
//					InputStream stream = c.openInputStream();
//					player = Manager.createPlayer(url.getText());
//					player.realize();
//					player.prefetch();
//					player.start();
				} catch (Throwable t) {
					log("Exception: " + t.getClass() + " - " + t.getMessage());
				}

			}
		};
		ms.addMenuItem(mi1);
		ms.addMenuItem(mi2);
		pushScreen(ms);

	}

	public static void main(String[] args) {
		app = new LaunchMediaPlayer();
		app.enterEventDispatcher();
	}

	public void log(final String msg) {
		invokeLater(new Runnable() {
			public void run() {
				ef.setText(ef.getText() + "\n" + msg);
			}
		});
	}

}
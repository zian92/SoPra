package de.glurak.frontend.mainFrame;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.feature.SliderPanelController;
import de.glurak.frontend.login.PromotionPanel;
import de.glurak.frontend.mainFrame.header.HeaderVController;
import de.glurak.frontend.mainFrame.navigation.NavigationVController;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueViewController;

public class MainFrameVController implements Observer{

	private MainFrameView view;
	private HeaderVController headerController;
	private PlayQueueViewController playerController;
	private NavigationVController navigationController;
	SliderPanelController con_slider;
	
	
	public MainFrameVController(){
		view = new MainFrameView();
		// testcases
	
		LinkedList<Medium> mediumList= new LinkedList<Medium>();

		Playlist pl= new Playlist();
		pl.setMediumList(mediumList);
		Medium m1 = new Medium(1,"PinkFluffyUniconrs","test2.mp3", null);
		Medium m2 = new Medium(2,"Blah","test.mp3", null);
		pl.getMediumList().add(m1);
		pl.getMediumList().add(m2);
		
		con_slider = new SliderPanelController();
		
		playerController= new PlayQueueViewController(pl);
		headerController= new HeaderVController();
		navigationController = new NavigationVController();
		
		view.getContent().add(con_slider.getView());
		view.getHeader().add(headerController.getView());
		view.getPlayer().add( playerController.getView());
		view.getNavigation().add(navigationController.getView());
	}


	// View Anpassen
	public void update(Observable o, Object arg) {
		System.out.println("UPADTE");
	}
	
}
package de.glurak.frontend.mainFrame.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import de.glurak.Query;
import de.glurak.data.User.Rights;
import de.glurak.data.User.User;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.adminlock.AdminLockViewController;
import de.glurak.frontend.mainFrame.content.message.MessageVController;
import de.glurak.frontend.mainFrame.content.news.PromotionVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileVController;
import de.glurak.frontend.mainFrame.content.upload.UploadVController;

import javax.imageio.ImageIO;
import javax.swing.*;

public class NavigationVController extends Observable {

	private NavigationView view;
	private ContentController contentController;
	private PromotionVController promotionVController;
    private Map<String,ContentController> map;
	
	/**
	 * Konstruktor
	 */
	public NavigationVController(ContentController promoVContr){
		
        map = new HashMap<String, ContentController>();
		promotionVController = (PromotionVController) promoVContr;
		
		ActionListener a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
                String name = e.getActionCommand();
                ContentController c;
                
                if (name=="Profil"){
                	c = new ProfileVController(null);
                }else if(name=="News"){
                	c = new PromotionVController();
                }else {
                	c = map.get(name);	
                }
                
                setContentController(c);
			}
		};
		
		
        User u = SessionThing.getInstance().getSessionUser();
        
        String imgFilename, username;
        imgFilename = u.getProfile().getPictureFileNameOrDefaultPictureName();
        username=u.getUsername();
        

        view = new NavigationView(a,username,u);
        addController(new ProfileVController(SessionThing.getInstance().getSessionUser()), "Profil",null);
        addController(new PlaylistVController(),"Playlist", Rights.MANAGE_PLAYLIST);
        addController(promotionVController,"News",null);
        addController(new MessageVController(),"Nachrichten",Rights.DO_MESSAGE);
        addController(new UploadVController(),"Upload",Rights.MANAGE_OWN_MEDIEN);
        addController(new AdminLockViewController(),"Medium sperren",Rights.LOCK_OTHER_MEDIEN);
	}


    /**
     * Fügt einen Button im View hinzu mit allen Callback
     * @param c der ContentController der angezeigt werden kann
     * @param name der Name der angezeigt werden soll (keine zwei Sachen mit den selben Namen!)
     * @param right das Recht das benötigt wird um das anzuzeigen, null falls kein Recht benötigt
     */
    public void addController(ContentController c, String name, String right){
        if (right!=null){
            User u = SessionThing.getInstance().getSessionUser();
            if (u!= null && !u.getProfile().hasRight(right))
                return;
        }
        map.put(name,c);
        view.addButton(name);
    }



	public NavigationView getView() {
		return view;
	}

	public void setView(NavigationView view) {
		this.view = view;
	}


	public ContentController getContentController() {
		return contentController;
	}


	public void setContentController(ContentController contentController) {
		this.contentController = contentController;
		setChanged();
		notifyObservers();
	}

}

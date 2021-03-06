package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.swing.*;

import de.glurak.data.Album;
import de.glurak.data.Playlist;
import de.glurak.data.User.ArtistProfile;
import de.glurak.data.User.Label;
import de.glurak.data.User.LabelManagerProfile;
import de.glurak.feature.Uploader;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.content.message.ApplicationVController;
import de.glurak.frontend.mainFrame.content.playlist.AlbumVController;

/**
 * Diese Klasse stellt dem LabelProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper, Daniel Papoutzis
 * Date: 28.02.2014
 */
public class LabelProfileVController extends Observable implements ContentController, ActionListener, NextContent{

    private LabelProfileView view;
    private Label label;
    private ContentController nextContent;

    /**
     * Konstruktor 
     * @param label das Label des anzuzeigenden Profils
     */
    public LabelProfileVController(Label label){
    	this.label = label;
    	boolean edit = false;
    	
    	// Überprüfen ob aktueller user LabelManager des Labels ist
    	for (LabelManagerProfile m: label.getManager()) {
    		if (SessionThing.getInstance().getSessionUser().equals(m.belongTo())) {
    			edit = true;
    		}
    	}
    	
        view = new LabelProfileView(label, getTop5Albums(), getTop5Artists(), edit);
        
        if (edit) {
        	view.b_edit.addActionListener(this);
        	view.b_upload.addActionListener(this);
        } else {
        	view.b_application.addActionListener(this);
        }
      
        
        for (int i=0;i<view.getB_artistArray().length; i++) {
        	view.getB_artistArray()[i].addActionListener(this);
		}
        
        for (int i=0;i<view.getB_playlistArray().length; i++) {
        	view.getB_playlistArray()[i].addActionListener(this);
		}
    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if (obj == view.b_edit){
        	this.label.getProfile().setAddress(view.getT_labeldescription().getText());
        	
        	nextContent = new LabelProfileVController(this.label);
			setChanged();
			notifyObservers();
        } else if (obj == view.b_application){
            setChanged();
            notifyObservers(new ApplicationVController(this.label,this));
            
        } else if (obj == view.b_upload){
			Uploader u = Uploader.getInstance();
			File file = u.selectSinglePicture(this.view);
			try {
                u.saveLabelProfilePicture(file, this.label.getProfile().getName(), this.label);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this.view, "Bitte versuch es mit einer anderen Datei.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
			
			this.view.repaint();
			this.view.revalidate();
			setChanged();
			notifyObservers(new LabelProfileVController(this.label));
		} else {
        	for (int i=0;i<view.getB_artistArray().length; i++) {
				if (obj == view.getB_artistArray()[i]) {
					nextContent = new ProfileVController(getTop5Artists().get(i).belongTo());
					setChanged();
					notifyObservers();
				}
			}
        	for (int i=0;i<view.getB_playlistArray().length; i++) {
				if (obj == view.getB_playlistArray()[i]) {
					nextContent = new AlbumVController((Album) getTop5Albums().get(i),this);
					setChanged();
					notifyObservers();
				}
			}
		}
    }
    
    
    /**
     * Gibt die top 5 hated Playlist/Alben der Artists des Labels zurück
     * @return top 5 hated Playlist
     */
    public List<Playlist> getTop5Albums() {
		List<Playlist> myAlbums = new ArrayList<Playlist>();
		
		for (ArtistProfile a: this.label.getProfile().getMyartists()) {
			for (Playlist p: a.belongTo().getMyPlaylists()) {
				myAlbums.add(p);
			}
		}
		
		Collections.sort(myAlbums);
		
		List<Playlist> returnList = new ArrayList<Playlist>();
		
		for (int i=0; i<myAlbums.size()&&i<5; i++) {
			if (myAlbums.get(i)!= null) {
				returnList.add(myAlbums.get(i));
			}
		}
		
		return returnList;
    }
    
    /**
     * Gibt die top 5 hated Artists eines Labels zurück
     * @return liste mit den 5 top hated Artists
     */
    public List<ArtistProfile> getTop5Artists() {
		List<ArtistProfile> myArtists = this.label.getProfile().getMyartists();
		
		Collections.sort(myArtists);
		
		List<ArtistProfile> returnList = new ArrayList<ArtistProfile>();
		
		for (int i=0; i<myArtists.size()&&i<5; i++) {
			if (myArtists.get(i)!= null) {
				returnList.add(myArtists.get(i));
			}
		}
		return returnList;
    }
    
    public JComponent getView() {
        return view;
    }

	public ContentController getNextContent() {
		return nextContent;
	}
	
	public void reload() {
		// TODO Auto-generated method stub
	}
}

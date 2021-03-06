package de.glurak.frontend.mainFrame.content.search;

import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.search.Searches.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Die SearchView ermöglicht die Erweiterte-Suche und die Ergebnisse von 
 * einer Suche werden hier angezeigt.
 * @author Entscheider
 *
 */
public class SearchView extends JPanel{
	
	private List<SearchTab> tabs = new ArrayList<SearchTab>();

    private List<NewControllerArrivedListener> new_controller_listener= new ArrayList<NewControllerArrivedListener>();

    public void addNewControllerArrivedListener(NewControllerArrivedListener ar){
        new_controller_listener.add(ar);
    }

    public void removeNewControllerArrivedListener(NewControllerArrivedListener ar){
        new_controller_listener.remove(ar);
    }

    /**
     * Benarichtigt alle NotifyArrivedListener den Content zu ändern
     * @param nController
     */
    protected void notifyNewControllerArrivedListener(ContentController nController){
       for (NewControllerArrivedListener a: new_controller_listener){
           a.gotNewController(nController);
       }
    }

    private JTabbedPane pane;
	
	public SearchView(){
		
		setLayout(new BorderLayout());
        pane = new JTabbedPane();
        add(pane, BorderLayout.CENTER);

        //Alle Tabs für das Suchen
        addEntry(new SearchTab(new MusicSearch(), "Musik"));
        addEntry(new SearchTab(new MusicInterpretSearch(), "Musik von Interpreten"));
        addEntry(new SearchTab(new MusicGenreSearch(), "Musik nach Genre"));
        addEntry(new SearchTab(new UserSearch(), "Benutzer"));
        addEntry(new SearchTab(new PlaylistSearch(), "Playlist"));
        addEntry(new SearchTab(new LabelSearch(), "Label"));
		
	}

    /**
     * Klasse für den doppeltklick auf die Liste in einer Suche
     * @param <T> die Suchklasse (z.B. Genre, Medium, User, Profile ,...)
     */
    private class TabMouseAdapter<T> extends MouseAdapter{
        private SearchTab<T> myTab;
        public TabMouseAdapter(SearchTab<T> tab){
            myTab=tab;
        }

        @Override
        public void mouseClicked(MouseEvent e){
            if (e.getClickCount()==2 && myTab.getSearchable()!=null){
                T val = myTab.getSelectedItem();
                if (val == null) return;
                myTab.getSearchable().otherDoubleClickAction(val);
                //Controller wechseln falls nötig
                ContentController c = myTab.getSearchable().getChangeController(val);
                if (c!=null)
                    notifyNewControllerArrivedListener(c);
            }
        }

    }

    /**
     * Gibt alle Tabs zu verstehen zu suchen
     */
    public void searchAll(){
        for (SearchTab s : tabs){
            s.search();
        }
    }

    /**
     * Fügt einen Tab und setzt alle Listener für SearchTab
     * @param s der SearchTab
     * @param <T> die Suchklasse
     */
    public <T> void addEntry(SearchTab<T> s){
       tabs.add(s);
       pane.add(s);
       s.addMouseListener(new TabMouseAdapter<T>(s));
    }

    /**
     * Setzt bei jeden SearchTab den Suchtext
     * @param text der neue Suchtext
     */
	public void setAllText(String text) {
		for (SearchTab s : tabs) {
			s.setSearchText(text);
		}
	}
	
}

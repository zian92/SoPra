package de.glurak.frontend.mainFrame.content.profile;

import javax.swing.*;

import java.awt.*;

/**
 * Die ProfileView zeigt dem User ein Profil an.
 * @author Christopher Distelkämper
 * Date: 26.02.2014
 */

public class ProfileView extends JPanel{

	// Panels
	private JPanel pan_profileview;
	private JPanel pan_profilepic;
	private JPanel pan_profiledata;
	private JPanel pan_topplaylists;
	private JPanel pan_likes;
	
	// Buttons
	protected JButton b_message;
	protected JButton b_follow;
	protected JButton b_edit;
	protected JButton b_moreplaylists;
	
	// TextFields profile_data
	protected JTextField t_username;
	protected JTextField t_firstname;
	protected JTextField t_lastname;
	protected JTextField t_birthdate;
	protected JTextField t_homecountry;
	
	// Labels profile_data
	private JLabel l_username;
	private JLabel l_firstname;
	private JLabel l_lastname;
	private JLabel l_birthdate;
	private JLabel l_homecountry;
	
	
	/**
	 * Constructor
	 * @param own Wird das eigene Profil angezeigt oder ein anderes?
	 */
	public ProfileView(boolean own){
		
		// Initialisieren Panel pan_profileview
		pan_profileview = new JPanel(new GridBagLayout());
		pan_profileview.setPreferredSize(new Dimension(724, 545));
		pan_profileview.setBackground(Color.black);
		
		// Layout-Restriktionen festlegen.
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);	
		
		// Initialisieren Panel pan_likes
		pan_likes = new JPanel(new GridBagLayout());
		pan_likes.setPreferredSize(new Dimension(350,200));
		pan_likes.setBackground(Color.black);
		pan_likes.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Initialisieren Panel pan_profilepic
		pan_profilepic = new JPanel(new GridBagLayout());
		pan_profilepic.setPreferredSize(new Dimension(350, 200));
		pan_profilepic.setBackground(Color.black);
		pan_profilepic.setBorder(BorderFactory.createLineBorder(Color.black));
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.HORIZONTAL;
			d.insets = new Insets(2,2,2,2);	
				
		    // Initialisieren der Buttons b_message, b_follow, b_edit
			
			if (own){  // Falls das eigene Profil angezeigt werden soll, nur b_edit anzeigen
			
		    	d.gridx = 1;
				d.gridy = 1;
				b_edit = new JButton("Bearbeiten");
				b_edit.setBackground(Color.black);
				b_edit.setForeground(Color.white);
				pan_profilepic.add(b_edit, d);
				
			}
			else{      // Falls ein anderes Profil angezeigt werden soll, b_message und b_follow anzeigen
				
				d.gridx = 0;
				d.gridy = 1;
			    b_message = new JButton("Nachricht");
			    b_message.setBackground(Color.black);
			    b_message.setForeground(Color.white);
			    pan_profilepic.add(b_message, d);
			    
			    d.gridx = 2;
			    d.gridy = 1;
			    b_follow = new JButton("Follow");
			    b_follow.setBackground(Color.black);
			    b_follow.setForeground(Color.white);
			    pan_profilepic.add(b_follow, d);
			    
			}
		// Initialisieren Panel pan_topplaylists
		pan_topplaylists = new JPanel(new GridBagLayout());	
		pan_topplaylists.setPreferredSize(new Dimension(350, 200));
		pan_topplaylists.setBackground(Color.black);
		pan_topplaylists.setBorder(BorderFactory.createLineBorder(Color.black));
		
			// Initialisieren des Buttons b_moreplaylists
			b_moreplaylists = new JButton("mehr");
			b_moreplaylists.setBackground(Color.black);
			b_moreplaylists.setForeground(Color.white);
			pan_topplaylists.add(b_moreplaylists);
					
		// Initialisieren Panel pan_profiledata
		pan_profiledata = new JPanel(new GridBagLayout());	
		pan_profiledata.setPreferredSize(new Dimension(350, 200));
		pan_profiledata.setBackground(Color.black);
		pan_profiledata.setBorder(BorderFactory.createLineBorder(Color.black));
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints e = new GridBagConstraints();
			e.fill = GridBagConstraints.HORIZONTAL;
			e.insets = new Insets(2,2,2,2);	

			// Label und Textfelder hinzufügen
			// Username
			e.gridx = 0;
			e.gridy = 0;
			e.weightx = 0.0;
			l_username = new JLabel("Username:");
			l_username.setForeground(Color.white);
			pan_profiledata.add(l_username, e);
			
			e.gridx = 1;
			e.gridy = 0;
			e.weightx = 1.0;
			t_username = new JTextField();
			t_username.setEditable(false);
			t_username.setBackground(Color.black);
			t_username.setForeground(Color.white);
			pan_profiledata.add(t_username, e);
			
			// Vorname
			e.gridx = 0;
			e.gridy = 1;
			e.weightx = 0.0;
			l_firstname = new JLabel("Vorname:");
			l_firstname.setForeground(Color.white);
			pan_profiledata.add(l_firstname, e);
			
			e.gridx = 1;
			e.gridy = 1;
			e.weightx = 1.0;
			t_firstname = new JTextField();
			t_firstname.setEditable(false);
			t_firstname.setBackground(Color.black);
			t_firstname.setForeground(Color.white);
			pan_profiledata.add(t_firstname, e);
			
			// Nachname
			e.gridx = 0;
			e.gridy = 2;
			e.weightx = 0.0;
			l_lastname = new JLabel("Nachname:");
			l_lastname.setForeground(Color.white);
			pan_profiledata.add(l_lastname, e);
			
			e.gridx = 1;
			e.gridy = 2;
			e.weightx = 1.0;
			t_lastname = new JTextField();
			t_lastname.setEditable(false);
			t_lastname.setBackground(Color.black);
			t_lastname.setForeground(Color.white);
			pan_profiledata.add(t_lastname, e);
			
			// Geburtstag
			e.gridx = 0;
			e.gridy = 3;
			e.weightx = 0.0;
			l_birthdate = new JLabel("Geburtstag:");
			l_birthdate.setForeground(Color.white);
			pan_profiledata.add(l_birthdate, e);
			
			e.gridx = 1;
			e.gridy = 3;
			e.weightx = 1.0;
			t_birthdate = new JTextField();
			t_birthdate.setEditable(false);
			t_birthdate.setBackground(Color.black);
			t_birthdate.setForeground(Color.white);
			pan_profiledata.add(t_birthdate, e);
			
			// Heimatland
			e.gridx = 0;
			e.gridy = 4;
			e.weightx = 0.0;
			l_homecountry = new JLabel("Heimatland:");
			l_homecountry.setForeground(Color.white);
			pan_profiledata.add(l_homecountry, e);
			
			e.gridx = 1;
			e.gridy = 4;
			e.weightx = 1.0;
			t_homecountry = new JTextField();
			t_homecountry.setEditable(false);
			t_homecountry.setBackground(Color.black);
			t_homecountry.setForeground(Color.white);
			pan_profiledata.add(t_homecountry, e);
			
			
		// Hinzufügen der Panels zum Panel pan_profileview
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		pan_profileview.add(pan_likes, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		pan_profileview.add(pan_profilepic, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		pan_profileview.add(pan_topplaylists, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		pan_profileview.add(pan_profiledata, c);
		
		// Hinzufügen des Panels zur ContentPane
		add(pan_profileview);		
		setVisible(true);
		
	}
		
	/**
	 * Erzeugt die ProfileView und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame profile = new JFrame("Profile");
		profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Registrationview in das Frame laden
		JComponent newContentPane = new ProfileView(false);
        newContentPane.setOpaque(true);
        profile.setContentPane(newContentPane);
        
        //Groesse des Frames festlegen
        profile.setPreferredSize(new Dimension(1000, 500));
        //Groesse des Frames soll nicht veraenderbar sein
        profile.setResizable(false);
        //Registrationview wird in der Mitte des Bildschirms geladen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        profile.setLocation(dim.width/2-profile.getSize().width/2-300, dim.height/2-profile.getSize().height/2-150);
		
		//Frame anpassen und sichtbar machen
		profile.pack();
		profile.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
}

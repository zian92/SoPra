package de.glurak;

import java.security.NoSuchAlgorithmException;

import javax.swing.SwingUtilities;

import de.glurak.data.Genre;
import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.User;
import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.login.LoginVController;

public class Glurak {

    private Glurak() {
        System.out.println("Started");
    }

    private void OpenInterface() {
        // Create GUI
        @SuppressWarnings("unused")
        LoginVController logControll = new LoginVController(Query.APPLICATION_NAME);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Glurak glumanda = new Glurak();
                SplashScreen splash = new SplashScreen();
                HibernateDB db = SessionThing.getInstance().getDatabase();

                if (db.allGenres().isEmpty()) {
                    glumanda.initialisiereDB(db);
                }
                splash.hideSplashScreen();
                glumanda.OpenInterface();
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                SessionThing.getInstance().getDatabase().save();
            }
        });
    }

    private void initialisiereDB(HibernateDB db) {
        Genre baseGenre = db.addGenre("Ohne Genre", null, null);
        db.addGenre("Metal", baseGenre, null);
        // userA
        User userA = new User();
        userA.setUsername("Olaf");
        /*
        try {
            userA.setPassword("olaf");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        */
        db.registrateUser(userA, null);
        ListenerProfile profileA = new ListenerProfile();
        db.registrateProfile(profileA, null);
        profileA.setFemale(false);
        profileA.setBirthdate("01.01.1992");
        profileA.setFirstname("Olaf");
        profileA.setLastname("Koehler");
        userA.setProfile(profileA);
    }
}

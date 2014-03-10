package de.glurak.data.User;

import de.glurak.data.Announcement;
import de.glurak.Query;
import de.glurak.data.NotEnoughRightException;

import java.io.File;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Oberklasse aller Profile eines Benutzers
 * 
 * @author Entscheider
 */
@Entity
public abstract class UserProfile extends Profile implements Serializable {
    protected String email;
    protected String firstname;
    protected String lastname;
    protected boolean isFemale;
    protected String country;

    public UserProfile(){
        super();
    }

    /**
     * Soll in den Unterklassen implementiert werden. Gibt die Rechte zurück die ein Nutzer mit diesem Profil besitzt
     * 
     * @return die Rechte des Nutzer des Profils
     */
    public abstract String[] myRights();

    @OneToOne(mappedBy = "profile")
    private User myUser;

    /**
     * Prüft ob der Benutzer das Recht right besitzt
     * 
     * @param right
     *            das zu überprüfende Recht
     * @return true, falls Recht besitzt, sonst false
     */
    public boolean hasRight(String right) {
        for (String r : myRights()) {
            if (r.equals(right)) return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean isFemale) {
        this.isFemale = isFemale;
    }

    @Override
    public User belongTo() {
        return myUser;
    }

    public void setUser(User u) {
        if (u == myUser) return;
        if (myUser != null) {
            User tmp = myUser;
            myUser = null;
            tmp.setProfile(null);
        }
        myUser = u;
        if (u != null) u.setProfile(this);
    }

    @Override
    public String getPictureFileNameOrDefaultPictureName() {
        if (pictureFileName.isEmpty() || !new File(pictureFileName).exists()) {
            if (isFemale) {
                return (Query.FOLDER_PICTURE_ICONS + "userf.jpg");
            } else {
                return (Query.FOLDER_PICTURE_ICONS + "userm.jpg");
            }
        }
        return pictureFileName;
    }

    @Override
    public void addAnnouncement(Announcement a) {
        NotEnoughRightException.throwIfNot(this, Rights.ANOUNCEMENTS_RIGHTS);
        super.addAnnouncement(a);
    }
}

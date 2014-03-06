package de.glurak.frontend.mainFrame.content.message;

import de.glurak.data.Message;
import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 * MessageView zeigt das Fenster an, wenn man einen anderen User des Systems eine Nachricht schreiben moechte.
 * 
 * @author Entscheider
 * 
 */
public class MessageView extends JPanel {
    private MessageViewWriter writer;
    private MessageViewList list;
    private DefaultListModel<Message> model;

    /**
     * Konstruktor
     * @param actionl der Listener für die Knöpfe im MessageViewWriter, null für keinen
     * @param listl der Listener falls in der Liste was selektiert wurd. null für keinen
     */
    public MessageView(ActionListener actionl, ListSelectionListener listl){
        writer = new MessageViewWriter(actionl);
        setLayout(new BorderLayout());
        add(writer, BorderLayout.SOUTH);
        list = new MessageViewList();
        initModel();
        if (listl!=null)
            list.addListSelectionListener(listl);
        list.setModel(model);
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    /**
     * Gibt die Naricht aus, die in der Liste in der Position p ist
     * @param p die Position
     * @return die Naricht, oder null falls die Position nicht gültig
     */
    public Message messageAtPos(int p){
        if (p<0||p>=model.getSize()) return null;
        return model.getElementAt(p);
    }

    private void initModel(){
        model = new DefaultListModel<Message>();
        HibernateDB db = SessionThing.getInstance().getDatabase();
        List<Message> myMessage = db.messageByReceiver(SessionThing.getInstance().getSessionUser());
        for (Message m: myMessage){
            model.addElement(m);
        }
    }

    /**
     * Gibt die Naricht zurück. Diese Message ist temporär und
     * nicht in der Datenbank. Sie ist dafür da sofort wieder gelöscht zu werden
     * @return die Naricht
     */
    public Message getEnteredMessage(){
        return writer.createMessage();
    }

    /**
     * Setzt die eingeben Naricht
     * @param m die Naricht, null für ein leeres Feld
     */
    public void setEnteredMessage(Message m){
        writer.setMessage(m);
    }
}

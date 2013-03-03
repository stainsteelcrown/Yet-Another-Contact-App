/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package services;
import domain.Contact;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrew
 */
public class ContactSvcSerializedImpl implements IContactSvc {

    public Contact store(Contact c){
        try {
            ObjectOutputStream output1 = new ObjectOutputStream(new FileOutputStream("SingleContact"));
            output1.writeObject(c);
            output1.flush();
            output1.close();
            } catch (IOException ex) {
        }
        return c;
    }
        
    public List retrieve (Contact r) {
        Contact contact = new Contact();
        List<Contact> contacts = new ArrayList<Contact>();
        try{
            ObjectInputStream input1 = new ObjectInputStream (new FileInputStream("SingleContact"));
            contact = (Contact)input1.readObject();
            contacts.add(contact);
            input1.close();
        } catch (Exception ex){

        }
        return contacts;
    }

    public Contact update(Contact u){
        //not yet implemented
        return null;
    }

    public Contact delete(Contact d) {
                Contact contactDelete = null;
        try{
            ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("SingleContact"));
            output2.writeObject(contactDelete);
            output2.flush();
            output2.close();
        }catch (Exception ex){

        }
        return contactDelete;
    }

    public List retrieve(String search) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

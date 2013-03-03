/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;
import domain.*;
import java.util.ArrayList;
import java.util.List;
import services.*;

/**
 *
 * @author Andrew
 */
public class ContactMgr extends Manager{

    public Contact create(Contact contact) throws ServiceLoadException{
        IContactSvc contactSvc = (IContactSvc)getService(IContactSvc.NAME);
        contactSvc.store(contact);
        return contact;
    }

    public List retrieve(Contact retrieve) throws ServiceLoadException{
        IContactSvc contactSvc = (IContactSvc)getService(IContactSvc.NAME);
        List<Contact> contacts = new ArrayList<Contact>();
        contacts = contactSvc.retrieve(retrieve);
        return contacts;
    }

    public List retrieve(String search) throws ServiceLoadException{
        IContactSvc contactSvc = (IContactSvc)getService(IContactSvc.NAME);
        List<Contact> contacts = new ArrayList<Contact>();
        contacts = contactSvc.retrieve(search);
        return contacts;
    }

    public Contact update(Contact update) throws ServiceLoadException{
        IContactSvc contactSvc = (IContactSvc)getService(IContactSvc.NAME);
        update = contactSvc.update(update);
        return update;
    }

    public Contact delete(Contact d) throws ServiceLoadException{
        IContactSvc contactSvc = (IContactSvc)getService(IContactSvc.NAME);
        d = contactSvc.delete(d);
        return d;
    }
}

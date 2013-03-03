/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

import domain.Address;
import domain.Contact;
import domain.Contact.AddressType;
import domain.Contact.ContactType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import managers.ContactMgr;
import services.ServiceLoadException;

/**
 *
 * @author Andrew
 */
public class GetData {

    GetData(){

    };

    private List<Contact> contacts = new ArrayList<Contact>();
    private JButton deleteBtn = new JButton("Delete");

    public Object[][] getData(String searchString, Object[][] newData){

        ContactMgr contactMgr = new ContactMgr();
        try {
            contacts = contactMgr.retrieve(searchString);
        } catch (ServiceLoadException ex) {
            Logger.getLogger(CreateContactUI1.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[][] oldData;

        for (Iterator it = contacts.iterator(); it.hasNext(); ){

            String contactString = "";

            Contact contact = (Contact) it.next();

            contactString = contact.getFirstName() + " " +
                         contact.getMiddleInitial() + " " +
                         contact.getLastName();

            String phoneString = "";
            int j = 0;
            String numbOfPhones = " ";

            if(contact.getContactValue(ContactType.HomePhone) != null)
                phoneString = contact.getContactValue(ContactType.HomePhone);

            if(contact.getContactValue(ContactType.BusinessPhone) != null &&
                    phoneString.equals(""))
                phoneString = contact.getContactValue(ContactType.BusinessPhone);

            if(contact.getContactValue(ContactType.Mobile) != null &&
                    phoneString.equals(""))
                phoneString = contact.getContactValue(ContactType.Mobile);

            for(ContactType ct : EnumSet.range(ContactType.HomePhone, ContactType.Mobile))
                if(contact.getContactValue(ct) != null)
                    j = j + 1;

            if(j > 1)
                numbOfPhones = numbOfPhones + "(+"+j+")";

            phoneString = phoneString + numbOfPhones;

            String emailString = "";
            int k = 0;
            String numbOfEmails = " ";

            if(contact.getContactValue(ContactType.HomeEmail) != null)
                emailString = contact.getContactValue(ContactType.HomeEmail);

            if(contact.getContactValue(ContactType.BusinessEmail) != null)
                emailString = contact.getContactValue(ContactType.BusinessEmail);

            for(ContactType ct1 : EnumSet.range(ContactType.HomeEmail, ContactType.BusinessEmail))
                if(contact.getContactValue(ct1) != null)
                    k = k + 1;

            if(j > 1)
                numbOfEmails = numbOfEmails + "(+"+k+")";

            emailString = emailString + numbOfEmails;

            String addressString = "";
            int l = 0;
            String numbOfAddresses = " ";

            for (AddressType at : EnumSet.range(AddressType.HomeAddress, AddressType.OtherAddress))
                    if(contact.getAddressObject(at) != null){
                        Address address = new Address();
                        address = contact.getAddressObject(at);
                        if(addressString.equals("")){
                            addressString = address.getStreetAddress() + " " +
                                address.getCity() + " " +
                                address.getState() + " " +
                                address.getZip() + " " +
                                address.getCountry();
                            l = l + 1;
                        }

                    }

            if(l > 1)
                numbOfAddresses = " (+"+l+")";

            addressString = addressString + numbOfAddresses;

            Object [][] newData1 = {
                {"","","","",-1}
            };

            int id = contact.getID();

            if(newData[0][1].equals(newData1[0][1]))
                newData[0] = new Object[]{contactString, phoneString, emailString,
                                            addressString, deleteBtn, id};
            else{
                oldData = newData;
                newData = new Object[oldData.length + 1][];

                System.arraycopy(oldData, 0, newData, 0, oldData.length);

                newData[oldData.length] = new Object[]{contactString, phoneString,
                                                    emailString, addressString, deleteBtn, id};
            }

        }

        return newData;

    }

}

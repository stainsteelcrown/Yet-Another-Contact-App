/**
 * @author                          : Andrew Castano
 * @Version                         : 1.0
 *
 * Development Environment          : NetBeans IDE 6.8
 *
 * Name of the File                 : Contact.java
 * Creation/Modification History    :
 *
 * Andrew   01/31/2011    Created
 *
 */

package domain;

import java.sql.ResultSet;
import java.util.*;

/**
 * Class is a collection of contact information using HashMap.
 *
 * @author Andrew Castano
 * @version 1.0
 *
 */


public class Contact {

    /**
     * Default constructor, takes no arguments
     */

    public Contact(){
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Contact)) return false;
        Contact c = (Contact) obj;
        if (c.id != id) return false;
        if (!c.firstName.equals(firstName)) return false;
        if (c.middleInitial != middleInitial) return false;
        if (!c.lastName.equals(lastName)) return false;
        for (ContactType ct : EnumSet.range(ContactType.HomePhone, ContactType.BusinessEmail))
            if(c.contactMap.get(ct) != null && this.contactMap.get(ct) != null)
                if(!c.contactMap.get(ct).equals(contactMap.get(ct)))return false;
        for (AddressType at : EnumSet.range(AddressType.HomeAddress, AddressType.OtherAddress))
            if(c.addressMap.get(at) != null && this.addressMap.get(at) != null)
                if(!c.addressMap.get(at).equals(addressMap.get(at)))return false;
        return true;
        }

    private int id;
    public void setID(int i){
        id = i;
    }
    public int getID(){
        return id;
    }
    private String firstName;

    public void setFirstName(String f){
        firstName = f;
    }

    public String getFirstName(){
        return firstName;
    }

    private char middleInitial;

    public void setMiddleInitial(char m){
        middleInitial = m;
    }

    public char getMiddleInitial(){
        return middleInitial;
    }

    private String lastName;

    public void setLastName(String l){
        lastName = l;
    }

    public String getLastName(){
        return lastName;
    }


    /**
     * Used enum to create own data type.  The name corresponds to the field.
     */

    public enum ContactType {
        HomePhone, BusinessPhone, Mobile, HomeEmail, BusinessEmail;
    }

    /**
     * Create a HashMap to hold the collection of contact information
     */

    private Map<ContactType,String> contactMap=new HashMap<ContactType,String>();

    /**
     * This method takes a key which is labled the field of the contact, and
     * a string value which is the information and puts it into the HashMap
     * we created.
     *
     * @param key
     * @param value
     */

    public void addContactValue(ContactType key,String value)
    {
        contactMap.put(key,value);
    }

    /**
     * This method takes the input of a key and returns a value from the
     * HashMap
     *
     * @param key
     * @return
     */

    public String getContactValue(ContactType key) {
        String value;
        if(contactMap.containsKey(key)){
            value = contactMap.get(key);
            return value;
        }
        return null;
    }
    
    /**
     * My intention is to create a separate key and hold a HashMap full of
     * address objects.
     */

    public enum AddressType {
        HomeAddress, BusinessAddress, OtherAddress;
    }

    /**
     * Create a HashMap to hold the Address objects
     */

    private Map<AddressType, Address> addressMap=new HashMap<AddressType, Address>();

    /**
     * Add an address object to the addressMap
     *
     * @param key
     * @param value
     */

    public Address addAddress(AddressType key)
    {
        Address addr1 = new Address();
        addAddressObject(key, addr1);
        return addr1;
    }

    public void addAddressObject(AddressType key, Address addressObject)
    {
        addressMap.put(key, addressObject);
    }

    public Address getAddressObject(AddressType key)
    {
        if(!addressMap.isEmpty()){
            Address addressObject;
            addressObject = addressMap.get(key);
            return addressObject;
        }
        return null;
    }

    public enum TotalContact{
        FirstName, LastName, HomePhone,
        BusinessPhone, Mobile, HomeEmail, BusinessEmail,
        HomeAddressStreet, HomeAddressCity, HomeAddressState,
        HomeAddressZip, HomeAddressCountry, BusinessAddressStreet, BusinessAddressCity,
        BusinessAddressState, BusinessAddressZip, BusinessAddressCountry, OtherAddressStreet,
        OtherAddressCity, OtherAddressState, OtherAddressZip, OtherAddressCountry;
    }

    private Map<TotalContact, String> totalContact = new HashMap<TotalContact, String>();

    private void addTotalContact(String key, String value){

        for (TotalContact tc : EnumSet.range(TotalContact.FirstName, TotalContact.OtherAddressCountry))
                if(tc.name().equals(key))
                    totalContact.put(tc, value);
    }

    public Map getTotalContact(){

        if(getFirstName() != null)
            addTotalContact("FirstName", getFirstName());

        if(getLastName() != null)
            addTotalContact("LastName", getLastName());

        for (ContactType ct : EnumSet.range(Contact.ContactType.HomePhone, Contact.ContactType.BusinessEmail))
            if(getContactValue(ct) != null)
                addTotalContact(ct.name() , getContactValue(ct));

        for (AddressType ta : EnumSet.range(AddressType.HomeAddress, AddressType.OtherAddress))
            if(getAddressObject(ta) != null){
                Address address = new Address();
                address = getAddressObject(ta);
                addTotalContact(ta.name() + "Street", address.getStreetAddress());
                addTotalContact(ta.name() + "City", address.getCity());
                addTotalContact(ta.name() + "State", address.getState());
                addTotalContact(ta.name() + "Zip", address.getZip());
                addTotalContact(ta.name() + "Country", address.getCountry());
            }


        return totalContact;
        }

    public boolean validate(){
        //if(id == 0) return false;
        if(firstName != null && firstName.length() > 20) return false;
        if(lastName != null &&lastName.length() > 20) return false;
        if(getContactValue(ContactType.HomePhone) != null && getContactValue(ContactType.HomePhone).length() > 20) return false;
        if(getContactValue(ContactType.BusinessPhone) != null &&
                getContactValue(ContactType.BusinessPhone).length() > 20) return false;
        if(getContactValue(ContactType.Mobile) != null &&
                getContactValue(ContactType.Mobile).length() > 20) return false;
        if(getContactValue(ContactType.HomeEmail) != null &&
                getContactValue(ContactType.HomeEmail).length() > 50) return false;
        if(getContactValue(ContactType.BusinessEmail) != null
                && getContactValue(ContactType.BusinessEmail).length() > 50) return false;

        for(AddressType ta : EnumSet.range(AddressType.HomeAddress, AddressType.OtherAddress))
            if(getAddressObject(ta) != null){
                Address addr = getAddressObject(ta);
                if(addr.getStreetAddress() != null &&
                        addr.getStreetAddress().length() > 20) return false;
                if(addr.getCity() != null &&
                        addr.getCity().length() > 20) return false;
                if(addr.getState() != null &&
                        addr.getState().length() > 20) return false;
                if(addr.getZip() != null &&
                        addr.getZip().length() > 20) return false;
                if(addr.getZip() != null &&
                        addr.getCountry().length() > 20) return false;
            }

        return true;
    }
}
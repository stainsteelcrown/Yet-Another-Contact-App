/**
 * @author                          : Andrew Castano
 * @Version                         : 1.0
 *
 * Development Environment          : NetBeans IDE 6.8
 *
 * Name of the File                 : Address.java
 * Creation/Modification History    :
 *
 * Andrew   01/31/2011    Created
 *
 */

package domain;

/**
 * Class is a container for individual address fields.
 *
 * @author Andrew Castano
 * @version 1.0
 *
 */

public class Address {

    public Address(){
        }
    
/**
 * 
 * @param obj
 * @return
 */

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Address)) return false;
        Address a = (Address) obj;
        if (!a.streetAddress.equals(streetAddress)) return false;
        if (!a.city.equals(city)) return false;
        if (!a.country.equals(country)) return false;
        if (!a.state.equals(state)) return false;
        if (!a.zip.equals(zip)) return false;
        return true;
    }

    private String streetAddress;

    public void setStreetAddress(String a){
        streetAddress = a;
    }

    public String getStreetAddress(){
        return streetAddress;
    }

    private String city;

    public void setCity(String c){
        city = c;
    }

    public String getCity(){
        return city;
    }

    private String state;

    public void setState(String s){
        state = s;
    }

    public String getState(){
        return state;
    }

    private String zip;

    public void setZip(String z){
        zip = z;
    }

    public String getZip(){
        return zip;
    }

    private String country;

    public void setCountry(String c){
        country = c;
    }

    public String getCountry(){
        return country;
    }

    public boolean validate(){
        if(streetAddress == null) return false;
        if(streetAddress.equals("")) return false;
        if(city == null) return false;
        if(city.equals("")) return false;
        if(state == null) return false;
        if(state.equals("")) return false;
        if(zip == null) return false;
        if(zip.equals("")) return false;
        if(country == null) return false;
        if(country.equals("")) return false;

        return true;
    }
}

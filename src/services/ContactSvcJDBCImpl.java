/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package services;
import domain.*;
import domain.Contact.AddressType;
import domain.Contact.ContactType;
import domain.Contact.TotalContact;
import java.sql.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.*;

/**
 *
 * @author Andrew
 */
public class ContactSvcJDBCImpl implements IContactSvc{

    String connString = "jdbc:mysql://localhost/contactapp?" +
                                "user=root&password=monkey256";

    
    public Contact store(Contact c){
        try {
            Connection conn = DriverManager.getConnection(connString);
            Statement stmt = conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
                                                  java.sql.ResultSet.CONCUR_UPDATABLE);

            String sql = "insert into person (firstName, middleInitial, lastName) values (\"" + 
                        c.getFirstName() + "\", \"" + c.getMiddleInitial() + "\", \"" +
                        c.getLastName() + "\")";

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            
            int id = -1;

            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()){
                c.setID(rs.getInt(1));
                id = rs.getInt(1);
            }
             
            String sql3 = "insert into contacttype (id) values (\"" + id + "\")";
            stmt.executeUpdate(sql3);
            for (ContactType ct : EnumSet.range(Contact.ContactType.HomePhone, Contact.ContactType.BusinessEmail))
                if(c.getContactValue(ct) != null){
                    String sql1 = "update contacttype set " + ct.name() +  " = \"" +
                            c.getContactValue(ct) + "\" where id = \"" + id + "\"";
                    stmt.executeUpdate(sql1);
                }


            for (AddressType at : EnumSet.range(AddressType.HomeAddress, AddressType.OtherAddress))
                if(c.getAddressObject(at) != null){
                    Address addr = c.getAddressObject(at);
                    String sql4 = "insert into address (id, addresstype, streetaddress, " +
                            "city, state, zip, country) values (\"" + id + "\", \"" +
                            at.name() + "\", \"" +
                            addr.getStreetAddress() + "\", \"" + addr.getCity() + "\", \"" +
                            addr.getState() + "\", \"" + addr.getZip() +
                            "\", \"" + addr.getCountry() + "\")";
                    stmt.executeUpdate(sql4);
                }



            stmt.close();
            conn.close();
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(ContactSvcJDBCImpl.class.getName()).log(Priority.DEBUG , ex);
        }

        return c;
    }
    
    private enum ColumnHeaders {
                firstname, lastname, homephone, businessphone,
                mobile, homeemail, businessemail, streetaddress, city, state, 
                zip, country;
            }

    public List retrieve(String test){

        List<Contact> contacts = new ArrayList<Contact>();
        String update = "where ";

        try {

            Connection conn = DriverManager.getConnection(connString);
            Statement stmt = conn.createStatement();

            if(test.equals("null"))
                update = " ";
            else{
                for (ColumnHeaders ch : EnumSet.range(ColumnHeaders.firstname, ColumnHeaders.zip))
                    update = update + ch.name() +  " = \"" + test + "\" or ";

                update = update + ColumnHeaders.country.name() +  " = \"" + test + "\"";
            }

            String sql = "select * from person left join contacttype on person.id = " +
                    "contacttype.id left join address on contacttype.id = address.id " +
                    update ;

            ResultSet rs1 = stmt.executeQuery(sql);
            while (rs1.next()) {
                Contact con = new Contact();
                con.setID(rs1.getInt("id"));
                con.setFirstName(rs1.getString("firstName"));
                con.setMiddleInitial(rs1.getString("middleInitial").charAt(0));
                con.setLastName(rs1.getString("lastName"));
                for (ContactType ct : EnumSet.range(Contact.ContactType.HomePhone, Contact.ContactType.BusinessEmail)) {
                    if (rs1.getString(ct.name()) != null) {
                        con.addContactValue(ct, rs1.getString(ct.name()));
                    }
                }
                for (AddressType at : EnumSet.range(AddressType.HomeAddress, AddressType.OtherAddress)) {
                    if (con.getAddressObject(at) != null) {
                        Address addr = con.getAddressObject(at);
                        addr.setStreetAddress(rs1.getString("streetaddress"));
                        addr.setCity(rs1.getString("city"));
                        addr.setState(rs1.getString("state"));
                        addr.setZip(rs1.getString("zip"));
                        addr.setCountry("country");
                        con.addAddressObject(at, addr);
                    }
                }
                contacts.add(con);
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ContactSvcJDBCImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                return contacts;
    }

    public List retrieve(Contact r) {
        List<Contact> contacts = new ArrayList<Contact>();
        try {
            Connection conn = DriverManager.getConnection(connString);
            Statement stmt = conn.createStatement();

            Map<TotalContact, String> mapOfContact = new HashMap<TotalContact, String>();

            mapOfContact = r.getTotalContact();

            String update = "where ";
            int count = 0;

            for(TotalContact complete : EnumSet.range(TotalContact.FirstName, TotalContact.BusinessEmail))
                if(mapOfContact.get(complete) != null){
                    count++;
                    String insert = mapOfContact.get(complete);
                    if(count > 1)
                        update = update + " and ";

                    update = update + complete.name() + " = \"" + insert + "\"";
                }

            int count1 = 0;
            String column = null;

            if(!update.equals("where "))
                update = update + " and ";

            for(TotalContact complete1 : EnumSet.range(TotalContact.HomeAddressStreet, TotalContact.OtherAddressCountry))
                if(mapOfContact.get(complete1) != null){

                    count1++;
                    

                    if(complete1.name().equals("HomeAddressStreet") ||
                       complete1.name().equals("BusinessAddressStreet") ||
                       complete1.name().equals("OtherAddressStreet")){

                          column = "streetaddress";

                    }

                    if(complete1.name().equals("HomeAddressCity") ||
                       complete1.name().equals("BusinessAddressCity") ||
                       complete1.name().equals("OtherAddressCity")){

                        column = "city";

                    }

                    if(complete1.name().equals("HomeAddressState") ||
                       complete1.name().equals("BusinessAddressState") ||
                       complete1.name().equals("OtherAddressState")){

                        column = "state";

                    }

                    if(complete1.name().equals("HomeAddressZip") ||
                       complete1.name().equals("BusinessAddressZip") ||
                       complete1.name().equals("OtherAddressZip")){

                        column = "zip";

                    }

                    if(complete1.name().equals("HomeAddressCountry") ||
                       complete1.name().equals("BusinessAddressCountry") ||
                       complete1.name().equals("OtherAddressCountry")){

                        column = "country";

                    }

                    if(count1 > 1){
                        update = update + " or ";
                    }

                    update = update + column + " = \"" + mapOfContact.get(complete1) + "\"";

                }


            if(update.equals("where "))
                update = " ";

            if(r == null)
                update = " ";


            String sql = "select * from person left join contacttype on person.id = " +
                    "contacttype.id left join address on contacttype.id = address.id " +
                    update ;

            
            ResultSet rs1 = stmt.executeQuery(sql);
            while (rs1.next()){
                Contact con = new Contact();
                con.setID(rs1.getInt("id"));
                con.setFirstName(rs1.getString("firstName"));
                con.setMiddleInitial(rs1.getString("middleInitial").charAt(0));
                con.setLastName(rs1.getString("lastName"));

                for (ContactType ct : EnumSet.range(Contact.ContactType.HomePhone, Contact.ContactType.BusinessEmail)){
                    if(rs1.getString(ct.name()) != null){
                        con.addContactValue(ct, rs1.getString(ct.name()));
                    }
                }

                for (AddressType at : EnumSet.range(AddressType.HomeAddress, AddressType.OtherAddress))
                    if(con.getAddressObject(at) != null){
                        Address addr = con.getAddressObject(at);
                        addr.setStreetAddress(rs1.getString("streetaddress"));
                        addr.setCity(rs1.getString("city"));
                        addr.setState(rs1.getString("state"));
                        addr.setZip(rs1.getString("zip"));
                        addr.setCountry("country");
                        con.addAddressObject(at, addr);
                    }
                contacts.add(con);
            }

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(ContactSvcJDBCImpl.class.getName()).log(Priority.DEBUG, ex);
        }
        return contacts;
    }

    public Contact update(Contact u) {
         try {
            Connection conn = DriverManager.getConnection(connString);
            Statement stmt = conn.createStatement();

            int id = u.getID();

            for (ContactType ct : EnumSet.range(Contact.ContactType.HomePhone, Contact.ContactType.BusinessEmail))
                if(u.getContactValue(ct) != null){
                    String sql1 = "update contacttype set " + ct.name() +  " = \"" +
                            u.getContactValue(ct) + "\" where id = \"" + id + "\"";
                    stmt.executeUpdate(sql1);
                }

            for (AddressType at : EnumSet.range(AddressType.HomeAddress, AddressType.OtherAddress))
                if(u.getAddressObject(at) != null){
                    Address addr = u.getAddressObject(at);
                    String sql4 = "update address set streetaddress = \"" +
                            addr.getStreetAddress() + "\", city = \"" + addr.getCity() +
                            "\", state = \"" +
                            addr.getState() + "\", zip = \"" + addr.getZip() +
                            "\", country = \"" + addr.getCountry() + "\" where id = \""
                            + id + "\" and " +
                            "addresstype = \"" + at.name() + "\"";
                    stmt.executeUpdate(sql4);
                }

            String sql = "update person set firstName = \"" +
                    u.getFirstName() + "\", middleInitial = \"" +
                    u.getMiddleInitial() + "\", lastName = \"" +
                    u.getLastName() + "\"  where id = \"" + id + "\"";

            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(ContactSvcJDBCImpl.class.getName()).log(Priority.DEBUG , ex);
        }

        return u;
    }

    

    public Contact delete(Contact d) {
         try {
            Connection conn = DriverManager.getConnection(connString);
            Statement stmt = conn.createStatement();

            int id = d.getID();

            String sql1 = "delete from contacttype where id = \"" + id + "\" ";

            stmt.executeUpdate(sql1);

            String sql3 = "delete from address where id = \"" + id + "\"";

            stmt.executeUpdate(sql3);

            String sql = "delete from person where id = \"" +
            id + "\"";
            stmt.executeUpdate(sql);

            
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            org.apache.log4j.Logger.getLogger(ContactSvcJDBCImpl.class.getName()).log(Priority.DEBUG , ex);
        }

        return d;
    }

}

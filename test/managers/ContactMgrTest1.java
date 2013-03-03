/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;

import domain.Address;
import domain.Contact;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrew
 */
public class ContactMgrTest1 {

    private static Contact contact = new Contact();

    public ContactMgrTest1() {
    }



    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class ContactMgr.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        contact.setFirstName("John");
        contact.setMiddleInitial('H');
        contact.setLastName("Doe");
        contact.addContactValue(Contact.ContactType.Mobile, "303-123-1234");
        contact.addContactValue(Contact.ContactType.HomePhone, "303-234-2345");
        Address ha = contact.addAddress(Contact.AddressType.HomeAddress);
        ha.setStreetAddress("330 Marshall Way");
        ha.setCity("Orem");
        ha.setState("Utah");
        ha.setZip("90210");
        ha.setCountry("USA");
        ContactMgr instance = new ContactMgr();
        Contact expResult = contact;
        Contact result = instance.create(contact);
        boolean test1 = result.validate();
        assertEquals("expected true", true, test1);
    }

    /**
     * Test of retrieve method, of class ContactMgr.
     */
    @Test
    public void testRetrieve() throws Exception {
        System.out.println("retrieve");
        ContactMgr instance = new ContactMgr();
        Contact expResult = contact;
        List result = instance.retrieve(contact);
        assertEquals(expResult, result.get(result.indexOf(contact)));
    }

    /**
     * Test of update method, of class ContactMgr.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        contact.setFirstName("Double");
        contact.setMiddleInitial('M');
        contact.setLastName("Trouble");
        Address hu = contact.addAddress(Contact.AddressType.HomeAddress);
        hu.setStreetAddress("330 Independance Pl");
        hu.setCity("Oremat");
        hu.setState("Utahafd");
        hu.setZip("9021033");
        hu.setCountry("USAff");
        ContactMgr instance = new ContactMgr();
        Contact expResult = contact;
        Contact result = instance.update(contact);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class ContactMgr.
     * */
     
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        ContactMgr instance = new ContactMgr();
        Contact expResult = contact;
        Contact result = instance.delete(contact);
        assertEquals(expResult, result);
    }
    
}
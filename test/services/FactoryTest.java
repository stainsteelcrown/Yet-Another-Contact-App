/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import domain.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class FactoryTest {

    public FactoryTest() {
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
     * Test of getContactSvc method, of class Factory.
     */
    @Test
    public void testFactoryIContactSvcImpl() throws ServiceLoadException {
        System.out.println("getContactSvc");

        Contact c = new Contact();


        c.setFirstName("John");
        c.setMiddleInitial('H');
        c.setLastName("Doe");

        c.addContactValue(Contact.ContactType.Mobile, "303-123-1234");
        c.addContactValue(Contact.ContactType.HomePhone, "303-234-2345");
        Address ha = c.addAddress(Contact.AddressType.HomeAddress);

        ha.setStreetAddress("330 Marshall Way");
        ha.setCity("Orem");
        ha.setState("Utah");
        ha.setZip("90210");
        ha.setCountry("USA");

        Factory instance = new Factory();

        IContactSvc contactSvc = (IContactSvc) instance.getService(IContactSvc.NAME);

        Contact c2 = contactSvc.store(c);

        assertNotNull(c2);

        List<Contact> contacts = new ArrayList<Contact>();

        contacts = contactSvc.retrieve(c);

 
        Contact  c3 = contacts.get(contacts.indexOf(c));
        
        Contact u = new Contact();
        u.setFirstName("Double");
        u.setMiddleInitial('M');
        u.setLastName("Trouble");
        u.addContactValue(Contact.ContactType.Mobile, "303-823-2345");
        u.addContactValue(Contact.ContactType.BusinessEmail, "monkey@monkey.com");
        Address hu = u.addAddress(Contact.AddressType.HomeAddress);
        hu.setStreetAddress("330 Independance Pl");
        hu.setCity("Oremat");
        hu.setState("Utahafd");
        hu.setZip("9021033");
        hu.setCountry("USAff");

        //Contact c5 = contactSvc.update(c, u);

        //Contact c4 = contactSvc.delete(c5);

        assertEquals("Expected True",c,c2);

        assertEquals("Expected True",c,c3);
        
     
    }
}
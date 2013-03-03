/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;


import domain.Contact.AddressType;
import domain.Contact.ContactType;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Contact;

/**
 *
 * @author Andrew
 */
public class ContactTest {

    public ContactTest() {
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
     * Test of addContactInfo method, of class Contact.
     */
    @Test
    public void testAddContactValue() {
        System.out.println("addContactInfo");
        ContactType key = ContactType.HomePhone;   // ct up here is the key
        String value = "303-123-1234";
        Contact instance = new Contact();
        instance.addContactValue(key,value); // Change our test add contact
        assertEquals("Expect true", instance.getContactValue(key), value);
        }

    /**
     * Test of getContactValue method, of class Contact.
     */
    @Test
    public void testGetContactValue() {
        System.out.println("getContactValue");
        ContactType key = ContactType.HomePhone; //key could be anything
        Contact instance = new Contact();
        instance.addContactValue(key, "303-123-1234");
        String expResult = "303-123-1234";
        String result = instance.getContactValue(key);
        assertEquals(expResult, result);  /// this is exactly what it looks like?
        }

    /**
     * Test of equals method, of class Contact.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Contact instance = new Contact();
        instance.setFirstName("John");
        instance.setLastName("Doe");
        instance.setMiddleInitial('H');
        instance.addContactValue(ContactType.Mobile, "303-123-1234");
        Address addr1 = new Address();
        addr1.setStreetAddress("330 Corona St");
        addr1.setCity("Denver");
        addr1.setState("CO");
        addr1.setZip("80218");
        addr1.setCountry("USA");
        instance.addAddressObject(AddressType.HomeAddress, addr1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        Contact obj2 = new Contact();
        obj2.setFirstName("John");
        obj2.setLastName("Doe");
        obj2.setMiddleInitial('H');
        obj2.addContactValue(ContactType.Mobile, "303-123-1234");
        obj2.addAddressObject(AddressType.HomeAddress, addr1);
        boolean expResult1 = true;
        boolean result1 = instance.equals(obj2);
        assertEquals("Expected True",expResult1, result1);
    }


    /**
     * Test of getAddressObject and addAddressObject method, of class Contact.
     */
    @Test
    public void testGetOrAddAddressObject() {
        System.out.println("getAddressValue");
        AddressType key = AddressType.HomeAddress;
        Contact instance = new Contact();
        Address expResult = new Address();
        expResult.setStreetAddress("1234 LionsGate Way");
        expResult.setCity("Salt Lake City");
        expResult.setCountry("USA");
        expResult.setState("UT");
        expResult.setZip("80218");
        instance.addAddressObject(key, expResult);
        Address result = instance.getAddressObject(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of setID method, of class Contact.
     
    @Test
    public void testSetID() {
        System.out.println("setID");
        int i = 0;
        Contact instance = new Contact();
        instance.setID(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Contact.
     
    @Test
    public void testGetID() {
        System.out.println("getID");
        Contact instance = new Contact();
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class Contact.
     
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String f = "";
        Contact instance = new Contact();
        instance.setFirstName(f);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class Contact.
     
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Contact instance = new Contact();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMiddleInitial method, of class Contact.
     *
    @Test
    public void testSetMiddleInitial() {
        System.out.println("setMiddleInitial");
        char m = ' ';
        Contact instance = new Contact();
        instance.setMiddleInitial(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMiddleInitial method, of class Contact.
     *
    @Test
    public void testGetMiddleInitial() {
        System.out.println("getMiddleInitial");
        Contact instance = new Contact();
        char expResult = ' ';
        char result = instance.getMiddleInitial();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class Contact.
     *
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String l = "";
        Contact instance = new Contact();
        instance.setLastName(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Contact.
     *
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Contact instance = new Contact();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAddress method, of class Contact.
     *
    @Test
    public void testAddAddress() {
        System.out.println("addAddress");
        AddressType key = null;
        Contact instance = new Contact();
        Address expResult = null;
        Address result = instance.addAddress(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAddressObject method, of class Contact.
     *
    @Test
    public void testAddAddressObject() {
        System.out.println("addAddressObject");
        AddressType key = null;
        Address addressObject = null;
        Contact instance = new Contact();
        instance.addAddressObject(key, addressObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddressObject method, of class Contact.
     *
    @Test
    public void testGetAddressObject() {
        System.out.println("getAddressObject");
        AddressType key = null;
        Contact instance = new Contact();
        Address expResult = null;
        Address result = instance.getAddressObject(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalContact method, of class Contact.
     *
    @Test
    public void testGetTotalContact() {
        System.out.println("getTotalContact");
        Contact instance = new Contact();
        Map expResult = null;
        Map result = instance.getTotalContact();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class Contact.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Contact instance = new Contact();
        instance.setID(134);
        instance.setFirstName("monkey");
        instance.addContactValue(ContactType.Mobile, "30241541");
        Address addr = instance.addAddress(AddressType.HomeAddress);
        addr.setCity("Denver");

        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);

    }

}
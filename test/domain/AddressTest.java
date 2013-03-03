/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import domain.Address;
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
public class AddressTest {

    public AddressTest() {
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
     * Test of setStreetAddress method, of class Address.
     */
    @Test
    public void testSetStreetAddress() {
        System.out.println("setStreetAddress");
        String a = "3321 Downing St";
        Address instance = new Address();
        instance.setStreetAddress(a);
        assertEquals("expected true", instance.getStreetAddress(), a);
    }

    /**
     * Test of getStreetAddress method, of class Address.
     */
    @Test
    public void testGetStreetAddress() {
        System.out.println("getStreetAddress");
        Address instance = new Address();
        String expResult = null;
        String result = instance.getStreetAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class Address.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String c = "";
        Address instance = new Address();
        instance.setCity(c);
        assertEquals("expected true", instance.getCity(), c);
    }

    /**
     * Test of getCity method, of class Address.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        Address instance = new Address();
        String expResult = null;
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class Address.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        String s = "";
        Address instance = new Address();
        instance.setState(s);
        assertEquals("expected true", instance.getState(), s);
    }

    /**
     * Test of getState method, of class Address.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Address instance = new Address();
        String expResult = null;
        String result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of setZip method, of class Address.
     */
    @Test
    public void testSetZip() {
        System.out.println("setZip");
        String z = "";
        Address instance = new Address();
        instance.setZip(z);
        assertEquals("expected true", instance.getZip(), z);
    }

    /**
     * Test of getZip method, of class Address.
     */
    @Test
    public void testGetZip() {
        System.out.println("getZip");
        Address instance = new Address();
        String expResult = null;
        String result = instance.getZip();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCountry method, of class Address.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String c = "";
        Address instance = new Address();
        instance.setCountry(c);
        assertEquals("expected true", instance.getCountry(), c);
    }

    /**
     * Test of getCountry method, of class Address.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Address instance = new Address();
        String expResult = null;
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Address.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Address instance = new Address();
        instance.setStreetAddress("3315 Downing St");
        instance.setCity("Denver");
        instance.setState("Colorado");
        instance.setZip("80223");
        instance.setCountry("USA");

        boolean result = instance.validate();
        assertEquals("expected true", true, result);
        instance.setStreetAddress("");
        result = instance.validate();
        assertEquals("expected false", false, result);
    }

    /**
     * Test of equals method, of class Address.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Address addr1 = new Address();
        addr1.setStreetAddress("330 Corona St");
        addr1.setCity("Denver");
        addr1.setState("CO");
        addr1.setZip("80218");
        addr1.setCountry("USA");
        boolean expResult = false;
        boolean result = addr1.equals(obj);
        assertEquals(expResult, result);
        Address addr2 = new Address();
        addr2.setStreetAddress("330 Corona St");
        addr2.setCity("Denver");
        addr2.setState("CO");
        addr2.setZip("80218");
        addr2.setCountry("USA");
        boolean expResult1 = true;
        result = addr1.equals(addr2);
        assertEquals(expResult1, result);
    }

}
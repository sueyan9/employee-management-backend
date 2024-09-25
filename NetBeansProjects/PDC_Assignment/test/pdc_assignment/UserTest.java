/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.SQLException;

/**
 *
 * @author xuyan
 */
public class UserTest {
    private DatabaseManager instance;
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
   
    }
    
    @After
    public void tearDown() {
        
        
    }
    
     /**
     * Test of insertUser method, of class DatabaseManager.
     */
    @Test
    public void testInsertUser() throws Exception {
        System.out.println("insertUser");
        String username = "111";
        String password = "111";
        String userGroup = "admin";
        DatabaseManager instance = new DatabaseManager();
        int expResult = 1;
        int result = instance.insertUser(username, password, userGroup);
        assertEquals(expResult, result);
    }

    /**
     * Test of userExists method, of class DatabaseManager.
     */
    @Test
    public void testUserExists() throws Exception {
        System.out.println("userExists");
        String username = "123";
        String password = "123";
        String userGroup = "admin";
        DatabaseManager instance = new DatabaseManager();
        
        int expResult = 1;
        int result = instance.userExists(username, password, userGroup);
        assertEquals(expResult, result);
   
    }

    
}

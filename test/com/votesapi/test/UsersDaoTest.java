
package com.votesapi.test;

import com.votesapi.dao.model.User;
import com.votesapi.dao.persistence.UserService;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author artem
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:/home/artem/VotesAPI/web/WEB-INF/applicationContext.xml" })
public class UsersDaoTest {
    
    @Resource(name="userService")
    private UserService userService;
    
    public UsersDaoTest() {
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
    
    @Test
    public void testUser(){
        User user = userService.get(Long.valueOf(1));
        assertNotNull(null, user);
    }

   
}

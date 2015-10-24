
package com.votesapi.test;

import com.votesapi.dao.model.Restoraunt;
import com.votesapi.dao.persistence.RestorauntService;
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
public class RestorauntDaoTest {
    
    @Resource(name="restorauntService")
    private RestorauntService restorauntService;
    
    public RestorauntDaoTest() {
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
    public void test(){
       Restoraunt rest = restorauntService.createRestoraunt("Astoria");
        assertNotNull(rest);
    }
}

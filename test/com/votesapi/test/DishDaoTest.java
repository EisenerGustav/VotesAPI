
package com.votesapi.test;

import com.votesapi.dao.model.Dish;
import com.votesapi.dao.persistence.DishService;
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
public class DishDaoTest {
    
    @Resource(name="dishService")
    private DishService dishService;
    
    public DishDaoTest() {
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
    public void testDishRetrieve(){
        Dish dish = dishService.get(Long.valueOf(1));
        assertNotNull(dish);
        assertNotNull(dish.getId());
    }
    
    @Test
    public void testDishCreate(){
        Dish dish = dishService.createDish("Salad 2", 10.75f);
        assertNotNull(dish);
        assertNotNull(dish.getId());
    }
}

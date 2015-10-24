
package com.votesapi.test;

import com.votesapi.dao.model.Dish;
import com.votesapi.dao.model.Menu;
import com.votesapi.dao.model.Restoraunt;
import com.votesapi.dao.persistence.DishService;
import com.votesapi.dao.persistence.MenueService;
import com.votesapi.dao.persistence.RestorauntService;
import java.util.List;

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
public class MenuDaoTest {
    
    @Resource(name="menueService")
    private MenueService menueService;
    
    @Resource(name="restorauntService")
    private RestorauntService restorauntService;
    
    @Resource(name="dishService")
    private DishService dishService;
    
    public MenuDaoTest() {
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
     public void testMenuCreate() {
     
         //getting restoraunt
         Restoraunt restoraunt = restorauntService.get(Long.valueOf(6));
         assertNotNull(restoraunt);
         assertNotNull(restoraunt.getId());
         
         Menu menu = menueService.createMenu("Menu 1", restoraunt);
         assertNotNull(menu);
         assertNotNull(menu.getId());
     }
     
     @Test
     public void testAddDish(){
         Dish dish = dishService.get(Long.valueOf(1));
         assertNotNull(dish);
         assertNotNull(dish.getId());
         
         Menu menu = menueService.get(Long.valueOf(1));
         assertNotNull(menu);
         assertNotNull(menu.getId());
         
         Menu menuUpdated = menueService.addDish(menu, dish);
         assertNotNull(menuUpdated);
         assertNotNull(menuUpdated.getId());
         assertNotNull(menuUpdated.getDishes());
         assertEquals(menuUpdated.getDishes().size(), 1);
         
         Dish relatedDish = ((List<Dish>)menuUpdated.getDishes()).get(0);
         assertNotNull(relatedDish);
         assertEquals(relatedDish, dish);
     }
}

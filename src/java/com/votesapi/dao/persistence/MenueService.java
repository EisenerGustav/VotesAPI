package com.votesapi.dao.persistence;

import com.votesapi.dao.model.Dish;
import com.votesapi.dao.model.Menu;
import com.votesapi.dao.model.Restoraunt;
import java.util.Vector;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 *
 * @author artem
 */

@Service("menueService")
public class MenueService extends AbstractService{
    
    public Menu createMenu(String name, Restoraunt restoraunt){
        Transaction tx = getSession().beginTransaction();
        Menu menu = new Menu();
        menu.setName(name);
        menu.setRestoraunt(restoraunt);
        try {
            getSession().persist(menu);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return menu;
    }
    
    
    public Menu addDish(Menu menu, Dish dish){
        Transaction tx = getSession().beginTransaction();
        try {
            if (menu.getDishes() == null){
                menu.setDishes(new Vector<Dish>());
            }
            menu.getDishes().add(dish);
            getSession().merge(menu);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            return menu;
        }
    }
    
    public Menu get(Long id){
        Menu menu = null;
        Transaction tx = getSession().beginTransaction();
        try {
            menu = (Menu)getSession().get(Menu.class, id);
            tx.commit();
        } catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            return menu;
        }
    }
}

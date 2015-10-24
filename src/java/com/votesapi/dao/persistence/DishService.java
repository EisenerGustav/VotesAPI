

package com.votesapi.dao.persistence;

import com.votesapi.dao.model.Dish;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 *
 * @author artem
 */
@Service("dishService")
public class DishService extends AbstractService {
    
    public Dish get(Long id){
        Transaction tx = getSession().beginTransaction();
        Dish dish = null;
        try {
            dish = (Dish)getSession().get(Dish.class, id);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            return dish;
        }
    }
    
    public Dish createDish(String name, float price){
        Dish dish = new Dish();
        Transaction tx = getSession().beginTransaction();
        try {
            dish.setName(name);
            dish.setPrice(price);
            getSession().persist(dish);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            return dish;
        }
    }

}

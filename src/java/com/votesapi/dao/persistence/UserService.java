
package com.votesapi.dao.persistence;

import com.votesapi.dao.model.User;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;


/**
 *
 * @author artem
 */

@Service("userService")
public class UserService extends AbstractService{
    
    public User get(Long id){
        User user = null;
        Transaction tx = getSession().beginTransaction();
        try {
            user = (User) getSession().get(User.class, id);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            return user;
        }
        
    }
    
}



package com.votesapi.dao.persistence;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
/**
 *
 * @author artem
 */
public abstract class AbstractService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}

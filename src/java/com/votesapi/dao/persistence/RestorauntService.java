
package com.votesapi.dao.persistence;

import org.springframework.stereotype.Service;
import com.votesapi.dao.model.Restoraunt;
import org.hibernate.Transaction;

/**
 *
 * @author artem
 */

@Service("restorauntService")
public class RestorauntService extends AbstractService{
    
    public Restoraunt createRestoraunt(String restorauntName) {
        Transaction tx = getSession().beginTransaction();
        Restoraunt rest = new Restoraunt();
        try {
            rest.setName(restorauntName);
            getSession().persist(rest);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return rest;
    }
    
    public Restoraunt get(Long id) {
        Restoraunt restoraunt = null;
        Transaction tx = getSession().beginTransaction();
        try {
            restoraunt = (Restoraunt) getSession().get(Restoraunt.class, id);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            return restoraunt;
        }

    }
}

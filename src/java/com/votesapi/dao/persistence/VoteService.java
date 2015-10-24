

package com.votesapi.dao.persistence;

import com.votesapi.dao.model.Restoraunt;
import com.votesapi.dao.model.User;
import com.votesapi.dao.model.Vote;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

/**
 *
 * @author artem
 */
@Service("voteService")
public class VoteService extends AbstractService{
    
    private static final int HOURS_BEFORE = 11;
    
    private static final int MINUTES_BEFORE = 0;
    
    public boolean isVoteUpdateable(Vote vote) {
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.HOUR_OF_DAY) < HOURS_BEFORE) {
            return true;
        } else {
            return (now.get(Calendar.HOUR_OF_DAY) == HOURS_BEFORE
                    && now.get(Calendar.MINUTE) == MINUTES_BEFORE);
        }
    }
    
    public Vote findVotes(User user, Restoraunt restoraunt){
        Transaction tx = getSession().beginTransaction();
        try {
            Criteria crit = getSession().createCriteria(Vote.class);
            crit.add(Restrictions.eq("user", user));
            crit.add(Restrictions.eq("restoraunt", restoraunt));
            Vote vote =  (Vote) crit.list().get(0);
            tx.commit();
            return vote;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }
    
    public Vote createVote(User user, Restoraunt restoraunt, Boolean voteValue){
        Vote vote = new Vote();
        vote.setVote(voteValue);
        vote.setUser(user);
        vote.setRestoraunt(restoraunt);
        vote.setVoteDate(new Date());
        Transaction tx = getSession().beginTransaction();
        try {
            getSession().persist(vote);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            
            return vote;
        }
    }
    
    public Vote updateVote(Vote vote){
        Transaction tx = getSession().beginTransaction();
        try {
            getSession().merge(vote);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
            
        } finally {
            return vote;
        }
    }

}

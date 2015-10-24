
package com.votesapi.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author artem
 */
@Entity
@Table(name = "votes")
public class Vote implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;
    
    @Type(type="org.hibernate.type.NumericBooleanType")
    private boolean vote;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="restoraunt_id")
    private Restoraunt restoraunt;
    
    @Column (name="vote_date")
    private Date voteDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getVote() {
        return vote;
    }

    public User getUser() {
        return user;
    }

     

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public Restoraunt getRestoraunt() {
        return restoraunt;
    }

    public void setRestoraunt(Restoraunt restoraunt) {
        this.restoraunt = restoraunt;
    }
    
    
    
}

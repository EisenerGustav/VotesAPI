
package com.votesapi.test;

import com.votesapi.dao.model.Restoraunt;
import com.votesapi.dao.model.User;
import com.votesapi.dao.model.Vote;
import com.votesapi.dao.persistence.RestorauntService;
import com.votesapi.dao.persistence.UserService;
import com.votesapi.dao.persistence.VoteService;
import java.util.Date;
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

public class VotesTest {
    
    @Resource(name="userService")
    private UserService userService;
    
    @Resource(name="restorauntService")
    private RestorauntService restorauntService;
    
    @Resource(name="voteService")
    private VoteService voteService;
    
    public VotesTest() {
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

    //@Test
    public void testVoteRetrieve(){
        //getting user
        User user = userService.get(Long.valueOf(1));
        Restoraunt rest = restorauntService.get(Long.valueOf(1));
        Vote vote = voteService.findVotes(user,rest);
        
        assertNotNull(vote);
        assertNotNull(vote.getId());
    }
    
   // @Test
    public void testVoteCreate(){
        //getting user and restoraut
        User user = userService.get(Long.valueOf(1));
        Restoraunt rest = restorauntService.get(Long.valueOf(2));
        
        Vote vote = voteService.createVote(user, rest, Boolean.TRUE);
        
        assertNotNull(vote);
        assertNotNull(vote.getId());
    }
    
    @Test
    public void testVoteUpdate(){
        User user = userService.get(Long.valueOf(1));
        Restoraunt rest = restorauntService.get(Long.valueOf(2));
        
        Vote vote = voteService.findVotes(user,rest);
        
        vote.setVote(false);
        vote.setVoteDate(new Date());
        
        vote = voteService.updateVote(vote);
        Vote vote2 = voteService.findVotes(user,rest);
        
        assertEquals(vote2.getVote(), false);
    }
}

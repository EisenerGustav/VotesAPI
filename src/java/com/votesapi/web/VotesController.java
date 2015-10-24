package com.votesapi.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.votesapi.dao.model.Restoraunt;
import com.votesapi.dao.model.StandardResponse;
import com.votesapi.dao.model.User;
import com.votesapi.dao.model.Vote;
import com.votesapi.dao.persistence.RestorauntService;
import com.votesapi.dao.persistence.VoteService;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author artem
 */
@RestController
public class VotesController extends AbstractController{
    
    @Resource(name="voteService")
    private VoteService  voteService;
    
    @Resource(name="restorauntService")
    private RestorauntService restorauntService;
    
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public String voteForRestoraunt(@RequestParam(value="user")String userId, 
            @RequestParam(value="restoraunt") String restorauntId, 
            @RequestParam(value="vote")String voteValue) throws JsonProcessingException {
        
        //checking if user is correct (user could be not only admin but regular one too)
        User user = getUserById(userId);
        if (user== null || user.getId() == null){
            return getWrongResponse(WRONG_USER_MESSAGE);
        }
        
        //checking if restoraunt id is correct
        Restoraunt restoraunt = restorauntService.get(Long.parseLong(restorauntId));
        if (restoraunt == null || restoraunt.getId()== null){
            return getWrongResponse(WRONG_REST_MESSAGE);
        }
        
        //checking if vote for specified user and restoraunt exist
        Vote vote = voteService.findVotes(user, restoraunt);
        boolean voteResult = Boolean.parseBoolean(voteValue);
        StandardResponse resp = new StandardResponse();
        if (vote == null || vote.getId()== null){
            //create vote
            vote = voteService.createVote(user, restoraunt, voteResult);
            resp.setResult(true);
            resp.setMessage(String.format(VOTE_SUCCESS_MESSAGE, 
                    (voteResult)?"for":"against", restoraunt.getName(),
                    vote.getId()));
        } else {
            if (voteService.isVoteUpdateable(vote)){
                vote.setVote(voteResult);
                vote.setVoteDate(new Date());
                voteService.updateVote(vote);
                resp.setResult(true);
                resp.setMessage(String.format(VOTE_SUCCESS_MESSAGE, 
                    (voteResult)?"for":"against", restoraunt.getName(),
                    vote.getId()));
            } else {
                resp.setResult(false);
                resp.setMessage(WRONG_VOTE_MESSAGE);
            }
        }
        return getObjectMapper().writeValueAsString(resp);
    }
    
    
}

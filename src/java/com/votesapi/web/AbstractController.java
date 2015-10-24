

package com.votesapi.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.votesapi.dao.model.StandardResponse;
import com.votesapi.dao.model.User;
import com.votesapi.dao.persistence.UserService;
import javax.annotation.Resource;

/**
 *
 * @author artem
 */
public abstract class AbstractController {
    
    private ObjectMapper objectMapper;

    @Resource(name="userService")
    private UserService userService;
    
    protected static final String REST_SUCCESS_CREATE_MESSAGE = 
            "Restoraunt is successfully created, id: %d";
    
    protected static final String MENU_SUCCESS_CREATE_MESSAGE = 
            "Menu for %s Restoraunt is successfully created, id: %d";
    
    protected static final String DISH_SUCCESS_CREATE_MESSAGE = 
            "Dish %s for %s Menu is successfully created, id: %d";
    
    protected static final String VOTE_SUCCESS_MESSAGE = 
            "You have successfully voted %s restoraunt '%s' , vote id is %d";
    
    protected static final String WRONG_USER_MESSAGE = 
            "Specified user is null or not admin";
    
    protected static final String WRONG_REST_MESSAGE = 
            "Restoraunt with specified ID does not exist";
    
    protected static final String WRONG_MENU_MESSAGE = 
            "Restoraunt with specified ID does not exist";
    
    protected static final String WRONG_VOTE_MESSAGE = "It is too late to change your mind";
    
    protected User getUserById(String userId){
        return userService.get(Long.parseLong(userId));
    }
    
    protected ObjectMapper getObjectMapper(){
        if (objectMapper == null)
            objectMapper = new ObjectMapper();
        return objectMapper;
    }
    
    protected String getWrongResponse(String message) throws JsonProcessingException{
        StandardResponse resp = new StandardResponse();
        resp.setResult(false);
        resp.setMessage(message);
        return getObjectMapper().writeValueAsString(resp);
    }
    
}

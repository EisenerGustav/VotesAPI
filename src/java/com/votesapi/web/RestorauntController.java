package com.votesapi.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.votesapi.dao.model.Dish;
import com.votesapi.dao.model.Menu;
import com.votesapi.dao.model.Restoraunt;
import com.votesapi.dao.model.StandardResponse;
import com.votesapi.dao.model.User;
import com.votesapi.dao.persistence.DishService;
import com.votesapi.dao.persistence.MenueService;
import com.votesapi.dao.persistence.RestorauntService;
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
public class RestorauntController extends AbstractController{
    
    @Resource(name="restorauntService")
    private RestorauntService restorauntService;
    
    @Resource(name="menueService")
    private MenueService menuService;
    
    @Resource(name="dishService")
    private DishService dishService;
    
    @RequestMapping(value="/restoraunts/add", method = RequestMethod.GET)
    public String addRestoraunt(@RequestParam(value="name") String name, 
            @RequestParam(value="user") String userId) throws JsonProcessingException{
        
        StandardResponse resp = new StandardResponse();
        
        //checking the user
        User user = getUserById(userId);
        if (user == null || !user.getIsAdmin()){
            return getWrongResponse(WRONG_USER_MESSAGE);
        }
        
        try {
            Restoraunt restoraunt = restorauntService.createRestoraunt(name);
            resp.setResult(true);
            resp.setMessage(String.format(REST_SUCCESS_CREATE_MESSAGE, restoraunt.getId()));
            
        }catch (Exception e){
            resp.setResult(false);
            resp.setMessage(e.getMessage());
        } finally {
            return getObjectMapper().writeValueAsString(resp);
        }
    }
    
    @RequestMapping(value="/menu/add", method = RequestMethod.GET)
    public String addMenu(@RequestParam(value="name") String name, 
            @RequestParam(value="user") String userId, 
            @RequestParam(value="restoraunt")String restorauntId) throws JsonProcessingException {
        
        StandardResponse resp = new StandardResponse();
        
        //checking the user
        User user = getUserById(userId);
        if (user == null || !user.getIsAdmin()){
            return getWrongResponse(WRONG_USER_MESSAGE);
        }
        
        //check the restoraunt
        Restoraunt restoraunt = restorauntService.get(Long.parseLong(restorauntId));
        if (restoraunt == null || restoraunt.getId() == null){
            return getWrongResponse(WRONG_REST_MESSAGE);
        }
        
        try {
            Menu menu = menuService.createMenu(name, restoraunt);
            resp.setResult(true);
            resp.setMessage(String.format(MENU_SUCCESS_CREATE_MESSAGE, 
                    restoraunt.getName(), menu.getId()));
        } catch (Exception e){
            resp.setResult(false);
            resp.setMessage(e.getMessage());
        } finally {
            return getObjectMapper().writeValueAsString(resp);
        }
    }
    
    @RequestMapping(value="/dish/add", method = RequestMethod.GET)
    public String addDish(@RequestParam(value="name") String name, 
           @RequestParam(value="price") String price, 
           @RequestParam(value="user")String userId, 
           @RequestParam(value="menu")String menuId) throws JsonProcessingException {
        
        //checking the user
        User user = getUserById(userId);
        if (user == null || !user.getIsAdmin()){
            return getWrongResponse(WRONG_USER_MESSAGE);
        }
        
        //checking the menu
        Menu menu = menuService.get(Long.parseLong(menuId));
        if (menu == null || menu.getId()==null){
            return getWrongResponse(WRONG_MENU_MESSAGE);
        }
        
        StandardResponse resp = new StandardResponse();
        
        try {
            Dish dish = dishService.createDish(name, Float.parseFloat(price));
            menuService.addDish(menu, dish);
            resp.setResult(true);
            resp.setMessage(String.format(DISH_SUCCESS_CREATE_MESSAGE, name, menu.getName(), dish.getId()));
        } catch (Exception e){
            resp.setResult(false);
            resp.setMessage(e.getMessage());
        } finally {
            return getObjectMapper().writeValueAsString(resp);
        }
    }
    
}

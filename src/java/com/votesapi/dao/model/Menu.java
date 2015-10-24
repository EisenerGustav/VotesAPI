
package com.votesapi.dao.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author artem
 */
@Entity
@Table (name="menues")
public class Menu implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;
    
    private String name;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="restoraunt_id")
    private Restoraunt restoraunt;
    
    @ManyToMany(fetch=FetchType.EAGER)
       @JoinTable(name="dishes_menues",
               joinColumns=
               @JoinColumn(name="menu_id"),
         inverseJoinColumns=
               @JoinColumn(name="dish_id"))
    private Collection<Dish> dishes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRestoraunt(Restoraunt restoraunt) {
        this.restoraunt = restoraunt;
    }

    public String getName() {
        return name;
    }

    public Restoraunt getRestoraunt() {
        return restoraunt;
    }

    public Collection<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Collection<Dish> dishes) {
        this.dishes = dishes;
    }
    
    
    
}

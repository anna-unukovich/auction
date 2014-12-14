package com.unukovich.auction.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity 
@Table (name="stat")
public class Stat {
    
    private static String DEFAULT_DESCRIPTION = "default description";

    /**
     * @return the DEFAULT_DESCRIPTION
     */
    public static String getDEFAULT_DESCRIPTION() {
        return DEFAULT_DESCRIPTION;
    }

    /**
     * @param aDEFAULT_DESCRIPTION the DEFAULT_DESCRIPTION to set
     */
    public static void setDEFAULT_DESCRIPTION(String aDEFAULT_DESCRIPTION) {
        DEFAULT_DESCRIPTION = aDEFAULT_DESCRIPTION;
    }
    
    private int id;
    private Date date;
    private String description;
    
    
    public Stat(){
        id = 0;
        date = new Date();
        description = DEFAULT_DESCRIPTION;
    }
    
    public Stat(String description){
        id = 0;
        date = new Date();
        this.description = description;
    }

    
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column (name="date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Column (name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    
    
}

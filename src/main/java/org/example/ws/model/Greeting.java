package org.example.ws.model;

import javax.persistence.Entity;

/**
 * The Greeting class is an entity model object.
 * this class do not have ID or referenceID cause the TransactionalEntity has it
 * Entity annotation is for JPA to recognise it as a table in DB
 *
 * @author Matt Warman
 */
@Entity
public class Greeting extends TransactionalEntity {

    private static final long serialVersionUID = 1L;//the SENDER and RECIEVER should have same class version for Serializable
    private String text;


    //this empty constructor is for JPA
    public Greeting()
    {

    }

    public String getText()
    {
        return text;
    }


    public void setText(String text)
    {
        this.text = text;
    }

}

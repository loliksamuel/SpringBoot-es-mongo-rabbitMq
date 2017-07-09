package org.example.ws.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The Account class is an entity model object.
 * An Account describes the security credentials and authentication flags
 * that permit access to application functionality.
 * this class do not have ID or referenceID cause the TransactionalEntity has it
 * Entity annotation is for JPA to recognise it as a table in DB
 *
 * @author Matt Warman
 */
@Entity
public class Account extends TransactionalEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private boolean enabled = true;

    @NotNull
    private boolean credentialsexpired = false;

    @NotNull
    private boolean expired = false;

    @NotNull
    private boolean locked = false;

    /**many to many relationship between role and account
    * there are many roles  to  an account (joe can be a USER and and ADMIN)
    * there are many account with ADMIN role(natia and sam are ADMIN)
     */
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "AccountRole",
            joinColumns = @JoinColumn(
                    name = "accountId",
                    referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(
                    name = "roleId",
                    referencedColumnName = "id") )
    private Set<Role> roles;

    public Account() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialsexpired() {
        return credentialsexpired;
    }

    public void setCredentialsexpired(boolean credentialsexpired) {
        this.credentialsexpired = credentialsexpired;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}

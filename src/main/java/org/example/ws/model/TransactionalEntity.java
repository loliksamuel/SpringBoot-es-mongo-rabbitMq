package org.example.ws.model;

import org.example.ws.util.RequestContext;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * The parent class for all transactional persistent entities.
 * MappedSuperclass annotation instucts the JPA framework to map the attributes of this class
 * Notes:
 * a mapped superclass has no separate table defined for it;
 * mapping information may be overridden in such subclasses by using the @AttributeOverride and @AssociationOverride annotations or corresponding XML elements.
 * directly to the tables in db that uses it
 * @see ReferenceEntity
 * 
 * @author Matt Warman
 */
@MappedSuperclass
public class TransactionalEntity implements Serializable {

    /**  The default serial version UID.  */
    private static final long serialVersionUID = 1L;

    /**  The primary key identifier.  */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * A secondary unique identifier which may be used as a reference to this
     * entity by external systems.
     * advantages:
     * more portable-easier to migrate db engine
     * more secure  -hacker cannot guess the id's
     */
    @NotNull
    private String referenceId = UUID.randomUUID().toString();

    /**
     * The entity instance version used for optimistic locking(better performance) on updating a row.
     */
    @Version
    private Integer version;

    /**
     * A reference to the entity or process which created this entity instance.
     */
    @NotNull
    private String createdBy;

    /**
     * The timestamp when this entity instance was created. this type is from package org.joda.time
     */
    @NotNull
    private DateTime createdAt;

    /**
     * A reference to the entity or process which most recently updated this
     * entity instance.
     */
    private String updatedBy;

    /**
     * The timestamp when this entity instance was most recently updated.
     */
    private DateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * A listener method which is invoked on instances of TransactionalEntity
     * (or their subclasses) prior to initial persistence. Sets the
     * <code>created</code> audit values for the entity. Attempts to obtain this
     * thread's instance of a username from the RequestContext. If none exists,
     * throws an IllegalArgumentException. The username is used to set the
     * <code>createdBy</code> value. The <code>createdAt</code> value is set to
     * the current timestamp.
     */
    @PrePersist
    public void beforePersist() {
        String username = RequestContext.getUsername();
        if (username == null) {
            throw new IllegalArgumentException(
                    "Cannot persist a TransactionalEntity without a username "
                            + "in the RequestContext for this thread.");
        }
        setCreatedBy(username);

        setCreatedAt(new DateTime());
    }

    /**
     * A listener method which is invoked on instances of TransactionalEntity
     * (or their subclasses) prior to being updated. Sets the
     * <code>updated</code> audit values for the entity. Attempts to obtain this
     * thread's instance of username from the RequestContext. If none exists,
     * throws an IllegalArgumentException. The username is used to set the
     * <code>updatedBy</code> value. The <code>updatedAt</code> value is set to
     * the current timestamp.
     */
    @PreUpdate
    public void beforeUpdate() {
        String username = RequestContext.getUsername();
        if (username == null) {
            throw new IllegalArgumentException(
                    "Cannot update a TransactionalEntity without a username "
                            + "in the RequestContext for this thread.");
        }
        setUpdatedBy(username);

        setUpdatedAt(new DateTime());
    }

    /**
     * Determines the equality of two TransactionalEntity objects. If the
     * supplied object is null, returns false. If both objects are of the same
     * class, and their <code>id</code> values are populated and equal, return
     * <code>true</code>. Otherwise, return <code>false</code>.
     * 
     * @param that An Object
     * @return A boolean
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (this.getClass().equals(that.getClass())) {
            TransactionalEntity thatTE = (TransactionalEntity) that;
            if (this.getId() == null || thatTE.getId() == null) {
                return false;
            }
            if (this.getId().equals(thatTE.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the hash value of this object.
     * 
     * @return An int
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        if (getId() == null) {
            return -1;
        }
        return getId().hashCode();
    }
}

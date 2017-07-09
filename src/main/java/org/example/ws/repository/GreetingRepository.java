package org.example.ws.repository;

import org.example.ws.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**<Greeting, Long> the 1st param is the entity, the 2nd param is the primary identifier type(private static final long serialVersionUID = 1L;)
the interface can be empty cause
 the JpaRepository gives all CRUS operation for free
 */
@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}

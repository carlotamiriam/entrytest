package support;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.domain.Sort;



@FunctionalInterface
public interface QueryCallback<E> {

    Query doWithEntityManager(EntityManager entityManager, boolean isCountQuery, Sort sortOptions);
    
}
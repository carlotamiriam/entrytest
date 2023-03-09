package support;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.domain.Sort;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class QueryCallbackBuilder<E> implements QueryCallback<E> {
	
	protected abstract void sqlBuilder(final StringBuilder builder,  final Map<String, Object> params);

	public Query doWithEntityManager(EntityManager entityManager, boolean isCountQuery, Sort sortOptions) {

		StringBuilder nativeQuery = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		
		// Build SQL and params
		this.sqlBuilder(nativeQuery, params);
		
		// Build query
		Query query;
		
		if (isCountQuery) {
			// Transform query into count one
			query = buildCountQuery(entityManager, nativeQuery);
		} else { 
			query = buildQuery(entityManager, sortOptions, nativeQuery);
		}
		
		// Inject parameters
		for (Map.Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		
		return query;

	}

	private Query buildQuery(EntityManager entityManager, Sort sortOptions, StringBuilder nativeQuery) {
		
		
		String stringNativeQuery;
		
		if (sortOptions != null && sortOptions.isSorted()) {
			
			stringNativeQuery = addSort(sortOptions, nativeQuery);
			
		} else {
			
			stringNativeQuery = nativeQuery.toString();
		}
		
		// Set type and add sort if any
		Class<?> persistentClass = (Class<?>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		return entityManager.createNativeQuery(stringNativeQuery, persistentClass);
	}

	private String addSort(Sort sortOptions, StringBuilder nativeQuery) {
		
		// Add Sort order by
		nativeQuery.insert(0, "SELECT * FROM (");
		nativeQuery.append(") as orderedQuery");
		
		String orderByString = CustomQueryUtils.applySorting(nativeQuery.toString(), sortOptions, "orderedQuery");
		
		if (orderByString != null && !orderByString.isEmpty()) {
			nativeQuery.append(" order by ").append(orderByString);
		}
		
		return nativeQuery.toString();
	}

	private Query buildCountQuery(EntityManager entityManager, StringBuilder nativeQuery) {
		Query query;
		nativeQuery.insert(0, "SELECT COUNT (*) FROM (").append(") sqlCount");
		
		// Remove any 'orders by' to speedup count query and prevent sorting query malformation
		CustomQueryUtils.removeAnyOrderBy(nativeQuery);
		
		query = entityManager.createNativeQuery(nativeQuery.toString());
		return query;
	}

}
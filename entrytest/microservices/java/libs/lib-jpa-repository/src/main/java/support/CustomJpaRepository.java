package support;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaRepository<T> {

	Optional<T> findOne(QueryCallback<T> callback);

	List<T> findAll(QueryCallback<T> callback);

	Page<T> findAll(QueryCallback<T> callback, Pageable pageable, boolean disableItemsCount);

	Page<T> findAll(QueryCallback<T> callback, Pageable pageable);
	
	Page<T> findAllByRawSql(QueryCallback<T> callback, Pageable pageable);
}
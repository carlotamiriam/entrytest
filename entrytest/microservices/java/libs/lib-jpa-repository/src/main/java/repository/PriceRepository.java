package repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import support.CustomJpaRepository;

import model.PriceEntity;
import queries.GenericFilterPriceQueryCallbackBuilder;


@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer> , CustomJpaRepository<PriceEntity>, 
	JpaSpecificationExecutor<PriceEntity>{
	
	
	
	public default List<PriceEntity> findFilterPrice(List<Date> startDate, List<Integer> productId, List<Integer> brandId){
		return findAll(GenericFilterPriceQueryCallbackBuilder.build(startDate, productId, brandId));

	}
	
	
}

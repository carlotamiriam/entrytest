package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.PriceResponseDto;
import lombok.extern.log4j.Log4j2;
import modelo.PriceEntity;
import repository.PriceRepository;

@Service
@Log4j2
public class PriceService {
	
	
	@Autowired
	private PriceRepository priceRepository;
	
	public List<PriceResponseDto> getMLocationFiltered(List<Date> startDate, List<Integer> productId, List<Integer> brandId) {

		log.debug("Starting MastersService getMLocation with filters");
		List<PriceEntity> priceEntityList=null;
		
		priceEntityList = priceRepository.findFilterPrice(startDate, productId, brandId);
				
		List<PriceResponseDto> mPriceResponseDtoList = frompriceEntityListToGenericResponseDtoList(priceEntityList);

		return mPriceResponseDtoList;
	}
	
	
	//Builders
	private List<PriceResponseDto> frompriceEntityListToGenericResponseDtoList(List<PriceEntity> priceEntityList) {
		List<PriceResponseDto> priceResponseDtoList = new ArrayList<>();
		for (PriceEntity priceEntity : priceEntityList) {
			PriceResponseDto priceOutputResponseDto = this.fromPriceEntityToPriceOutputResponseDto(priceEntity);
			priceResponseDtoList.add(priceOutputResponseDto);
		}
		return priceResponseDtoList;
	}
	
	private PriceResponseDto fromPriceEntityToPriceOutputResponseDto(PriceEntity priceEntity) {
		return PriceResponseDto.builder()
				.id(priceEntity.getId())
				.brandId(priceEntity.getBrandId())
				.startDate(priceEntity.getStartDate())
				.endDate(priceEntity.getEndDate())
				.priceList(priceEntity.getPriceList())
				.productId(priceEntity.getProductId())
				.price(priceEntity.getPrice())
			.build();
	}
	
	
	

}

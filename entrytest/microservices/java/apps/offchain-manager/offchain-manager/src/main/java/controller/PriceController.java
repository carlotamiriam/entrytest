package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dto.GenericDto;
import dto.PriceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import service.PriceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
@Tag(name = "Network", description = "Network")
@RestController
@RequestMapping("/price")
public class PriceController {
	
	@Autowired 
	private PriceService priceService;
	
	@Operation(summary = "Find prices by param filters", description = "Find prices by param filters", tags = { "Find prices by param filters" })
    @ApiResponses(value = {
    	@ApiResponse(responseCode = "200", description = "Successful operation")
    })
	@Transactional(readOnly=true)
	@GetMapping(value = "")
	public @ResponseBody ResponseEntity<GenericDto<List<PriceResponseDto>>> getPrices(
			@PageableDefault(page=0, size=Integer.MAX_VALUE, sort={"id"}, direction=Direction.DESC) Pageable pageable,
			@RequestParam(name = "start-date", required = false) List<Date> startDate,
			@RequestParam(name = "product-id", required = false) List<Integer> productId,
			@RequestParam(name = "brand-id", required = false) List<Integer> brandId
	) { 
		
		log.debug("Starting MastersController getMLocation with filters");
		
		List<PriceResponseDto> pricesPage = priceService.getMLocationFiltered(startDate, productId, brandId);
		
		GenericDto<List<PriceResponseDto>> responseDto = GenericDto.build(pricesPage);
		
		log.debug("Returning response from PriceController getPrices with filters");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	
	
	
	
	
	
	
}

package dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder 
public class PriceOutputResponseDto {
	
	private int id;
	private int brandId;
	private Date startDate;
	private Date endDate;
	private int priceList;
	private long productId;
	private int priority;
	private double price;
	private String currency;
	
}

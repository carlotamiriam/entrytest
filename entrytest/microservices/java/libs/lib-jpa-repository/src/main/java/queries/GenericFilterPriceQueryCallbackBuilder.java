package queries;

import java.util.Date;
import java.util.List;
import java.util.Map;

import support.QueryCallback;
import support.QueryCallbackBuilder;

import lombok.experimental.UtilityClass;
import model.PriceEntity;
import support.QueryCallbackUtilsPrice;

	@UtilityClass

	public class GenericFilterPriceQueryCallbackBuilder {

		public static QueryCallback<PriceEntity> build(List<Date> startDateList, List<Integer> productIdList, List<Integer> brandIdList) {
			
			return new QueryCallbackBuilder<PriceEntity>() {
				@Override
				protected void sqlBuilder(StringBuilder builder, Map<String, Object> params) {
					
					//builder.delete(0, builder.length()); // Clear for debug
					
					builder.append(" SELECT p.*");
					builder.append(" FROM price p");
					builder.append(" WHERE ");
					
					if(startDateList!=null && !startDateList.isEmpty()) {
						builder.append(" p.start_date in :startDateList AND");
						params.put("startDateList", startDateList);
					}
					if(productIdList!=null&& !productIdList.isEmpty()) {
						builder.append(" p.product_id in :productIdList AND");
						params.put("productIdList", productIdList);
					}
					if(brandIdList!=null&& !brandIdList.isEmpty()) {
						builder.append(" p.brand_id :brandIdList AND");
						params.put("brandIdList", brandIdList);
					}
						
					QueryCallbackUtilsPrice.removeWhereAndSql(builder);
				}
				
			};
		}
		
}
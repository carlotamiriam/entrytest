package support;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@UtilityClass
@Log4j2
public class QueryCallbackUtilsPrice {
	
	public static void removeWhereAndSql(StringBuilder builder) {
		if (builder.substring(builder.length()-6, builder.length()).equalsIgnoreCase("WHERE ")){
			builder.delete(builder.length()-7, builder.length()); // Delete WHERE
		}
		if (builder.substring(builder.length()-5, builder.length()).equalsIgnoreCase("WHERE")){
			builder.delete(builder.length()-6, builder.length()); // Delete WHERE
		}
	}
	
}

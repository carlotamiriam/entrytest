package utils;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectPage<T> {
	private List<T> response;
	private int totalPages;
	private long totalElements;
	private int page;
	private int size;
	
	public static <T> ObjectPage<T> build(List<T> listResponse, Page<?> page){
		return new ObjectPage<>(listResponse, page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());
	}
	
	public static <T> ObjectPage<T> build(List<T> listResponse, int totalPages, long totalElements, int page, int size){
		return new ObjectPage<>(listResponse, totalPages, totalElements, page, size);
	}

}
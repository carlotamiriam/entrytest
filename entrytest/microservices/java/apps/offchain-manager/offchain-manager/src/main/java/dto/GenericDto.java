package dto;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class GenericDto<T> {

	protected String returnMessage;
	protected T data;
	protected Integer totalPages;
	protected Long totalElements;
	protected Integer page;
	protected Integer size;
	
	public GenericDto() {
	}
	
	public GenericDto(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	
	public GenericDto(String returnMessage, T data) {
		this.returnMessage = returnMessage;
		this.data = data;
	}
	
	public GenericDto(String returnMessage, T data, Page<?> objectPage) {
		
		this.returnMessage = returnMessage;
		this.data = data;
		
		if (objectPage != null) { 
			this.totalPages = objectPage.getTotalPages();
			this.totalElements = objectPage.getTotalElements();
			this.page = objectPage.getNumber();
			this.size = objectPage.getSize();
		}
	}
	
	public GenericDto(String returnMessage, T data, ObjectPage<?> objectPage) {
		
		this.returnMessage = returnMessage;
		this.data = data;
		
		if (objectPage != null) { 
			this.totalPages = objectPage.getTotalPages();
			this.totalElements = objectPage.getTotalElements();
			this.page = objectPage.getPage();
			this.size = objectPage.getSize();
		}
	}
	
	public GenericDto(String returnMessage, T data, Integer page, Integer size, Integer totalPages, Long totalElements ) {
		this.returnMessage = returnMessage;
		this.data = data;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.page = page;
		this.size = size;
	}
	
	public static GenericDto<String> build(String returnMessage) {
		return new GenericDto<>(returnMessage);
	}
	
	public static <T> GenericDto<T> build(T data) {
		return new GenericDto<>(null, data);
	}
	
	public static <T> GenericDto<T> build(T data, ObjectPage<?> page) {
		return new GenericDto<>(null, data, page.getPage(), page.getSize(), page.getTotalPages(), page.getTotalElements());
	}
	
	public static <T> GenericDto<T> build(T data, Page<?> page) {
		return new GenericDto<>(null, data, page);
	}
	
	public static <T> GenericDto<T> build(String returnMessage, T data) {
		return new GenericDto<>(returnMessage, data);
	}
	
	public static <T> GenericDto<T> build(String returnMessage, T data, Page<?> page) {
		return new GenericDto<>(returnMessage, data, page);
	}
	
	public static <T> GenericDto<T> build(T data, Integer page, Integer size, Integer totalPages, Long totalElements) {
		return new GenericDto<>(null, data, page, size, totalPages, totalElements);
	}
	
	public static <T> GenericDto<T> build(String returnMessage, T data, Integer page, Integer size, Integer totalPages, Long totalElements) {
		return new GenericDto<>(returnMessage, data, page, size, totalPages, totalElements);
	}
	
}

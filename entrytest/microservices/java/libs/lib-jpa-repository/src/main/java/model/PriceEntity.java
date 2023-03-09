package model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import lombok.Data;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;



@Data
@Entity
@NamedQuery(name = "PriceEntity.findAll", query = "SELECT b FROM PriceEntity b")
@Table(name = "price")
public class PriceEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// ********
	// Columns
	// ********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "brand_id", unique = false, nullable = false)
	private int brandId;

	@Column(name = "start_date", unique = false, nullable = true)
	private Date startDate;

	@Column(name = "end_date", unique = false, nullable = true)
	private Date endDate;

	@Column(name = "price_List", unique = false, nullable = true)
	private int priceList;

	@Column(name = "product_id", unique = false, nullable = true)
	private long productId;

	@Column(name = "priority", unique = false, nullable = true)
	private int priority;

	@Column(name = "price", unique = false, nullable = true)
	private double price;

	@Column(name = "currency", unique = false, nullable = true)
	private String currency;
}

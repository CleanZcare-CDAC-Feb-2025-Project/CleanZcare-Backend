package com.homecleaning.Entity;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SlideData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	// imageUrl which is uploaded on the cloudinary
	private String image;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "category_id")
//	private Category category;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
    @JsonManagedReference(value = "category-slideData")
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "row_section_data_id")
	@JsonIgnore // This breaks the circular reference
	private RowSectionData rowSectionData;

	private double rating;
	private String reviews;
	private BigDecimal price;
	@Column(length = 3, nullable = false)
	private String currency="INR"; // e.g. "INR", "USD"
//////
//	public void setCurrency(String currency) {
////		java.util.Currency.getInstance(currency); // validate ISO code
//		this.currency = currency;
//	}

}

package com.homecleaning.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class RowSectionData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String subTitle;
//	@OneToMany(mappedBy = "rowSectionData", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<SlideData> data = new ArrayList<>();
	@OneToMany(mappedBy = "rowSectionData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SlideData> data = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "row_section_id")
	@JsonIgnore // This breaks the circular reference
	private RowSection rowSection;
}

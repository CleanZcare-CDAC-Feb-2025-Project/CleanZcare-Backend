package com.homecleaning.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "row_section")
@ToString
public class RowSection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "`index`")
	private int index;
	private String type;
//	@OneToMany(mappedBy = "rowSection", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<RowSectionData> data = new ArrayList<>();
	@OneToMany(mappedBy = "rowSection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RowSectionData> data = new ArrayList<>();
}

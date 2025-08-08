package com.homecleaning.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTableRequestDTO {

	private ServiceTableDto parentService;
	private List<ServiceTableDto> subServices;
}
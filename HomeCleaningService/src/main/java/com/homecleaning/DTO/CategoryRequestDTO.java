
package com.homecleaning.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRequestDTO {
    private String title;
    private String description;
    private List<Long> group; // Only IDs are needed from frontend
}

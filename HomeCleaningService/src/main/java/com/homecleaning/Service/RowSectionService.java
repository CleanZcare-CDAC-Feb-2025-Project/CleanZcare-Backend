package com.homecleaning.Service;

import com.homecleaning.DTO.RowSectionDTO;
import com.homecleaning.Entity.RowSection;

import java.util.List;

public interface RowSectionService {

    RowSection createRowSection(RowSectionDTO request) throws Exception;

    List<RowSection> getAllRowSections();

    RowSection getRowSectionById(Long id);

    RowSection updateRowSection(Long id, RowSectionDTO request) throws Exception;

    boolean deleteRowSection(Long id);
}

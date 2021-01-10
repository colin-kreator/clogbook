package com.pilotair.clogbook.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface CsvFileTreatmentService {
	int importFlightsFromCsv( MultipartFile file, Integer userId );
}

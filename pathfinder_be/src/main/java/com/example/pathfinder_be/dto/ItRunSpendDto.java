package com.example.pathfinder_be.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="pathfinder_itrun_spend")
public class ItRunSpendDto {

	private String id;
	private double baseLineYearSpend;
	
	private List<ItRunYearlyDto> runCalc;
}

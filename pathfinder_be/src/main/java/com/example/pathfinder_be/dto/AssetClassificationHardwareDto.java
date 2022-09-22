package com.example.pathfinder_be.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="pathfinder_asset_classification_hardware")
public class AssetClassificationHardwareDto {

	private long spendIncreaseWithInflation_h;
	private float eliminate_reducdancyElimination;
	private float eliminate_retireDecommision;
	private float consolidate_realignRedistribute;
	private float rationalize_automateMl;
	
	private float total_eliminateReduc;
	private float total_eliminateRetire;
	private float total_consolidate;
	private float total_rationalize;
	private float total_perc_h;
	private float total_value_h;
	
	private float savingSubtotal_perc_h;
	private float savingSubtotal_value_h;
}

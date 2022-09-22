package com.example.pathfinder_be.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection="pathfinder_asset_classification_software")
public class AssetClassificationSoftwareDto {

	private long spendIncreaseWithInflation_s;
	private float rationalize_refactor;
	private float std_platform;
	private float transform_distributed_agile;
	private float eliminate_redundancy;
	
	private float total_rationalize;
	private float total_std_platform;
	private float total_transform;
	private float total_eliminate;
	private float total_perc_s;
	private float total_value_s;
	
	private float savingSubtotal_perc_s;
	private float savingSubtotal_value_s;
}

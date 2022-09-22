package com.example.pathfinder_be.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="pathfinder_asset_classification_hosted")
public class AssetClassificationHostedCbsDto {

	private long spendIncreaseWithInflation_host;
	private float transform_cloud;
	
	private float total_transform_cloud;
	private float total_perc_host;
	private float total_value_host;
	
	private float savingSubtotal_perc_host;
	private float savingSubtotal_value_host;
	
}

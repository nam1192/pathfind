package com.example.pathfinder_be.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="pathfinder_asset_classification_managed")
public class AssetClassificationManagedServicesDto {

	private long spendIncreaseWithInflation_m;
	private float industrialize_shiftleft;
	private float industrialize_automate;
	private float industrialize_realtime;
	private float synergize_selfservice;
	
	private float total_ishiftleft;
	private float total_std_iautomate;
	private float total_irealtime;
	private float total_synergize;
	private float total_perc_m;
	private float total_value_m;
	
	private float savingSubtotal_perc_m;
	private float savingSubtotal_value_m;
}

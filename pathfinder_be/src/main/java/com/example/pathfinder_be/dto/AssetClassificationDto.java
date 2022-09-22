package com.example.pathfinder_be.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="pathfinder_asset_classification")
public class AssetClassificationDto {

	private double perc_split_hardware;
	private long baseLine_hardware;
	
	private double perc_split_software;
	private long baseLine_software;
	
	private double perc_split_managed_services;
	private long baseLine_managed_services;
	
	private double perc_split_hosted_cbs;
	private long baseLine_hosted_cbs;
	
	private long itspendonassets_baseline;
	
	
	private List<AssetClassificationHardwareDto> hardwareCalculations;
	private List<AssetClassificationSoftwareDto> softwareCalculations;
	private List<AssetClassificationManagedServicesDto> managed_servicesCalculations;
	private List<AssetClassificationHostedCbsDto> hosted_cbsCalculations;
	
	ArrayList<ItSpendOnAssetsYearlyDto> itspendcalc;

}

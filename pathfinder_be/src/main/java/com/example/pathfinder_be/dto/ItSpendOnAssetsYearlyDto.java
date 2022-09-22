package com.example.pathfinder_be.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="pathfinder_itspend_asset_yearly")
public class ItSpendOnAssetsYearlyDto {

	private long itspendonassets_year;
	private long saving_lervers;
	private long cot_partner_share;
	private long total_savings_model;
	private long run_businessit;
	
	private long itspendon_run_personel;
	private long yearly_runrate;
	private long itpersonel_spend_inflation;
	private long cot_client_share;
	
	private long total_itpersonel_spend_inflation;
	private long total_cot_client_share;
	private long total_yearly_runrate;
	
	
	
}

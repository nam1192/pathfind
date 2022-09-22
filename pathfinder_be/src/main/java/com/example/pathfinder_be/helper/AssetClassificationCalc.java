package com.example.pathfinder_be.helper;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.pathfinder_be.dto.AssetClassificationDto;
import com.example.pathfinder_be.dto.AssetClassificationHardwareDto;
import com.example.pathfinder_be.dto.AssetClassificationHostedCbsDto;
import com.example.pathfinder_be.dto.AssetClassificationManagedServicesDto;
import com.example.pathfinder_be.dto.AssetClassificationSoftwareDto;
import com.example.pathfinder_be.dto.CostofTransformationDto;
import com.example.pathfinder_be.dto.InputTablesDto;
import com.example.pathfinder_be.dto.ItRunSpendDto;
import com.example.pathfinder_be.dto.ItRunYearlyDto;
import com.example.pathfinder_be.dto.ItSpendCategoriesDto;
import com.example.pathfinder_be.dto.ItSpendOnAssetsYearlyDto;
import com.example.pathfinder_be.dto.ItPersonalCostDto;
import com.example.pathfinder_be.dto.ItPersonnelDto;
import com.example.pathfinder_be.dto.YearBasedOutsourcingDto;
import com.example.pathfinder_be.dto.YearBasedTransformationDto;
import com.example.pathfinder_be.repo.CostofTransformationRepo;

@Component
public class AssetClassificationCalc {

	@Autowired
	CostofTransformationRepo cotRepo;
	
	public ItRunSpendDto yearBasedCalculation(ItRunSpendDto run, ItPersonnelDto itp, InputTablesDto inp, CostofTransformationDto cot) {
	
		ItRunSpendDto obj=new ItRunSpendDto();
		
       obj.setBaseLineYearSpend(inp.getRun_business_value()-(itp.getIt_spend_on_personal_perc()*inp.getRun_business_value()));
       
      // CostofTransformationDto step=cotRepo.findById(id).get();
       List<YearBasedTransformationDto> listOfYears=cot.getYearBaseCostCalculations();
       
       
        ArrayList<ItRunYearlyDto> yrBasedCalc=new ArrayList<ItRunYearlyDto>();
        long initial_itSpend =0;
   	 	long initial_cotClientShare =0;
   	 	long initial_yearlyRunRate =0;
   	 	int j=1;
   	 	
        for(ItRunYearlyDto obj1:    run.getRunCalc()) {
            
        	 double prevyearvalue= obj.getBaseLineYearSpend();
        	 long client_share= listOfYears.get(j).getClient_share_value();
            
        	ItRunYearlyDto  a = new ItRunYearlyDto();
            a.setItSpendWithInflation(obj1.getItSpendWithInflation());
            a.setCotClientShare(obj1.getCotClientShare());
            a.setYearlyRunRate(obj1.getYearlyRunRate());
            a.setTotal_itSpendWithInflation(obj1.getTotal_itSpendWithInflation());
            a.setTotal_cotClientShare(obj1.getTotal_cotClientShare());
            a.setTotal_yearlyRunRate(obj1.getTotal_yearlyRunRate());
            
                a.setItSpendWithInflation(prevyearvalue+(inp.getAnnual_inflation_perc()*prevyearvalue));
                a.setTotal_itSpendWithInflation(initial_itSpend+a.getItSpendWithInflation());
                initial_itSpend = (long) a.getTotal_itSpendWithInflation();
                prevyearvalue = a.getItSpendWithInflation();
      
           
           if(obj1.getYear()==2) {
                a.setCotClientShare(1-(itp.getIt_spend_on_personal_perc()*client_share));
                	System.out.println("cot client share "+ a.getCotClientShare());
                a.setTotal_cotClientShare(initial_cotClientShare+a.getCotClientShare());
                initial_cotClientShare= (long) a.getTotal_cotClientShare();
            } 
            	
            	a.setYearlyRunRate(a.getItSpendWithInflation()+a.getCotClientShare());
            	a.setTotal_yearlyRunRate(initial_yearlyRunRate+a.getYearlyRunRate());
            	initial_yearlyRunRate= (long) a.getTotal_yearlyRunRate();
            	yrBasedCalc.add(a);
            	j++;
            
}
		return obj;
	
	}
	
	
//Hardware Asset Classification
	
	public AssetClassificationDto hardwareCalculation(AssetClassificationDto acl, AssetClassificationHardwareDto asc,ItRunSpendDto run, InputTablesDto inp, ItSpendCategoriesDto isc) {
		
		AssetClassificationDto obj1= new AssetClassificationDto();
		
		obj1.setPerc_split_hardware(isc.getHardware());
		obj1.setBaseLine_hardware((long) (acl.getBaseLine_hardware()*run.getBaseLineYearSpend()));
		
		ArrayList<AssetClassificationHardwareDto> hardcalc=new ArrayList<AssetClassificationHardwareDto>();
		
		long initial_spend_increase =acl.getBaseLine_hardware();
		long savingSubTotalValue = 0;
		
		for(AssetClassificationHardwareDto obj2:    acl.getHardwareCalculations()) {			
			
			obj2.setSpendIncreaseWithInflation_h((long) (initial_spend_increase+(initial_spend_increase*inp.getAnnual_inflation_perc())));
			initial_spend_increase = obj2.getSpendIncreaseWithInflation_h();
				System.out.println("spend increase with infl "+ obj2.getSpendIncreaseWithInflation_h());
			
			
			obj2.setSavingSubtotal_perc_h(asc.getEliminate_reducdancyElimination()+asc.getEliminate_retireDecommision()+asc.getConsolidate_realignRedistribute()+asc.getRationalize_automateMl());
			obj2.setSavingSubtotal_value_h(asc.getSavingSubtotal_perc_h()*initial_spend_increase);
			hardcalc.add(obj2);
				System.out.println("saving sub toatalperc "+ obj2.getSavingSubtotal_perc_h() + ", saving sub toatal value "+ obj2.getSavingSubtotal_value_h());
			
			obj2.setTotal_value_h(savingSubTotalValue+obj2.getSavingSubtotal_value_h());  //1+2
			savingSubTotalValue = (long) obj2.getTotal_value_h();  
				System.out.println("total value " +obj2.getTotal_value_h());
			
			
			obj2.setTotal_perc_h(asc.getTotal_eliminateReduc()+asc.getTotal_eliminateRetire()+asc.getTotal_consolidate()+asc.getTotal_rationalize());
				System.out.println("total perc "+ obj2.getTotal_perc_h());
		}
			
		return obj1;

		
	}
	
//Software Asset Classification
	
 public AssetClassificationDto softwareCalculation(AssetClassificationDto acl, AssetClassificationSoftwareDto asw,ItRunSpendDto run, InputTablesDto inp, ItSpendCategoriesDto isc) {
		
		AssetClassificationDto obj3= new AssetClassificationDto();
		
		obj3.setPerc_split_software(isc.getSoftware());
		obj3.setBaseLine_software((long) (acl.getBaseLine_software()*run.getBaseLineYearSpend()));
		
		ArrayList<AssetClassificationSoftwareDto> softcalc=new ArrayList<AssetClassificationSoftwareDto>();
		
		long initial_spend_increase =acl.getBaseLine_software();
		long savingSubTotalValue = 0;
		
		for(AssetClassificationSoftwareDto obj4:    acl.getSoftwareCalculations()) {			
			
			obj4.setSpendIncreaseWithInflation_s((long) (initial_spend_increase+(initial_spend_increase*inp.getAnnual_inflation_perc())));
			initial_spend_increase = obj4.getSpendIncreaseWithInflation_s();
				System.out.println("spend increase with infl "+ obj4.getSpendIncreaseWithInflation_s());
			
			
			obj4.setSavingSubtotal_perc_s(asw.getRationalize_refactor()+asw.getStd_platform()+asw.getTransform_distributed_agile()+asw.getEliminate_redundancy());
			obj4.setSavingSubtotal_value_s(asw.getSavingSubtotal_perc_s()*initial_spend_increase);
			softcalc.add(obj4);
				System.out.println("saving sub toatalperc "+ obj4.getSavingSubtotal_perc_s() + ", saving sub toatal value "+ obj4.getSavingSubtotal_value_s());
			
			obj4.setTotal_value_s(savingSubTotalValue+obj4.getSavingSubtotal_value_s()); 
			savingSubTotalValue = (long) obj4.getTotal_value_s();  
				System.out.println("total value " +obj4.getTotal_value_s());
			
			
			obj4.setTotal_perc_s(asw.getTotal_rationalize()+asw.getTotal_std_platform()+asw.getTotal_transform()+asw.getTotal_eliminate());
				System.out.println("total perc "+ obj4.getTotal_perc_s());
		}
			
		return obj3;

		
	}

// Managed Services Asset Classification
 
 public AssetClassificationDto managedCalculation(AssetClassificationDto acl, AssetClassificationManagedServicesDto acm,ItRunSpendDto run, InputTablesDto inp, ItSpendCategoriesDto isc) {
		
		AssetClassificationDto obj5= new AssetClassificationDto();
		
		obj5.setPerc_split_managed_services(isc.getManagedServices());
		obj5.setBaseLine_managed_services((long) (acl.getBaseLine_managed_services()*run.getBaseLineYearSpend()));
		
		ArrayList<AssetClassificationManagedServicesDto> softcalc=new ArrayList<AssetClassificationManagedServicesDto>();
		
		long initial_spend_increase =acl.getBaseLine_managed_services();
		long savingSubTotalValue = 0;
		
		for(AssetClassificationManagedServicesDto obj6:    acl.getManaged_servicesCalculations()) {			
			
			obj6.setSpendIncreaseWithInflation_m((long) (initial_spend_increase+(initial_spend_increase*inp.getAnnual_inflation_perc())));
			initial_spend_increase = obj6.getSpendIncreaseWithInflation_m();
				System.out.println("spend increase with infl "+ obj6.getSpendIncreaseWithInflation_m());
			
			
			obj6.setSavingSubtotal_perc_m(acm.getIndustrialize_shiftleft()+acm.getIndustrialize_automate()+acm.getIndustrialize_realtime()+acm.getSynergize_selfservice());
			obj6.setSavingSubtotal_value_m(acm.getSavingSubtotal_perc_m()*initial_spend_increase);
			softcalc.add(obj6);
				System.out.println("saving sub toatalperc "+ obj6.getSavingSubtotal_perc_m() + ", saving sub toatal value "+ obj6.getSavingSubtotal_value_m());
			
			obj6.setTotal_value_m(savingSubTotalValue+obj6.getSavingSubtotal_value_m()); 
			savingSubTotalValue = (long) obj6.getTotal_value_m();  
				System.out.println("total value " +obj6.getTotal_value_m());
			
			
			obj6.setTotal_perc_m(acm.getTotal_ishiftleft()+acm.getTotal_std_iautomate()+acm.getTotal_irealtime()+acm.getTotal_synergize());
				System.out.println("total perc "+ obj6.getTotal_perc_m());
		}
			
		return obj5;

		
	}
	
	
 
 
// Hosted Cbs Asset Classification
 
 public AssetClassificationDto hostedCalculation(AssetClassificationDto acl, AssetClassificationHostedCbsDto host,ItRunSpendDto run, InputTablesDto inp, ItSpendCategoriesDto isc) {
		
		AssetClassificationDto obj7= new AssetClassificationDto();
		
		obj7.setPerc_split_hosted_cbs(isc.getHosted_cbs());
		obj7.setBaseLine_hosted_cbs((long) (acl.getBaseLine_hosted_cbs()*run.getBaseLineYearSpend()));
		
		ArrayList<AssetClassificationHostedCbsDto> hostcalc=new ArrayList<AssetClassificationHostedCbsDto>();
		
		long initial_spend_increase =acl.getBaseLine_hosted_cbs();
		long savingSubTotalValue = 0;
		
		for(AssetClassificationHostedCbsDto obj8:    acl.getHosted_cbsCalculations()) {			
			
			obj8.setSpendIncreaseWithInflation_host((long) (initial_spend_increase+(initial_spend_increase*inp.getAnnual_inflation_perc())));
			initial_spend_increase = obj8.getSpendIncreaseWithInflation_host();
				System.out.println("spend increase with infl "+ obj8.getSpendIncreaseWithInflation_host());
			
			
			obj8.setSavingSubtotal_perc_host(host.getTransform_cloud());
			obj8.setSavingSubtotal_value_host(host.getSavingSubtotal_perc_host()*initial_spend_increase);
			hostcalc.add(obj8);
				System.out.println("saving sub toatalperc "+ obj8.getSavingSubtotal_perc_host() + ", saving sub toatal value "+ obj8.getSavingSubtotal_value_host());
			
			obj8.setTotal_value_host(savingSubTotalValue+obj8.getSavingSubtotal_value_host()); 
			savingSubTotalValue = (long) obj8.getTotal_value_host();  
				System.out.println("total value " +obj8.getTotal_value_host());
			
			
			obj8.setTotal_perc_host(host.getTotal_transform_cloud());
				System.out.println("total perc "+ obj8.getTotal_perc_host());
		}
			
		return obj7;

		
	}
 
 
 
// It Spend on Assets
 
 public AssetClassificationDto assetCalculation(AssetClassificationDto acl,AssetClassificationHardwareDto hard, AssetClassificationSoftwareDto soft, AssetClassificationManagedServicesDto mang, AssetClassificationHostedCbsDto host, InputTablesDto inp, ItPersonnelDto itp, CostofTransformationDto cot, ItRunSpendDto run) {
		
		AssetClassificationDto obj9= new AssetClassificationDto();
		
		obj9.setItspendonassets_baseline(acl.getBaseLine_hardware()+acl.getBaseLine_software()+acl.getBaseLine_managed_services()+acl.getBaseLine_hosted_cbs());
		
			ArrayList<ItSpendOnAssetsYearlyDto> itspendcalc=new ArrayList<ItSpendOnAssetsYearlyDto>();
		
			for(ItSpendOnAssetsYearlyDto obj10:    acl.getItspendcalc()) {		
		
				obj10.setItspendonassets_year(hard.getSpendIncreaseWithInflation_h()+soft.getSpendIncreaseWithInflation_s()+mang.getSpendIncreaseWithInflation_m()+host.getSpendIncreaseWithInflation_host());
				System.out.println("It spend on Asset "+ obj10.getItspendonassets_year());
				
				obj10.setSaving_lervers((long) (hard.getSavingSubtotal_value_h()+soft.getSavingSubtotal_value_s()+mang.getSavingSubtotal_value_m()+host.getSavingSubtotal_value_host()));
			
				obj10.setCot_partner_share((long) ((1-itp.getIt_spend_on_personal_perc())*cot.getPartner_value()));
				
				obj10.setTotal_savings_model(obj10.getSaving_lervers()+obj10.getCot_partner_share());
			
//				obj10.setRun_businessit((long) (run.getYearlyRunRate()-obj10.getTotal_savings_model()));
						
//				J69 = I23 - I69 left
			}
			
			
			ArrayList<ItSpendOnAssetsYearlyDto> itspendcalculation=new ArrayList<ItSpendOnAssetsYearlyDto>();
			
			for(ItSpendOnAssetsYearlyDto obj10:    acl.getItspendcalc()) {		
		
			obj10.setItspendon_run_personel(itp.getRun_fte_personal());
			obj10.setItpersonel_spend_inflation((long) (obj10.getItspendon_run_personel()+(inp.getAnnual_inflation_perc()*obj10.getItspendon_run_personel())));
		
			
			
			
			
			

		
		
		
		
		
		
		
		
		
		
 }
			return obj9;
}
}
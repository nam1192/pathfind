package com.example.pathfinder_be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pathfinder_be.dto.AssetClassificationDto;
import com.example.pathfinder_be.dto.AssetClassificationHardwareDto;
import com.example.pathfinder_be.dto.CostofTransformationDto;
import com.example.pathfinder_be.dto.InputTablesDto;
import com.example.pathfinder_be.dto.ItPersonnelDto;
import com.example.pathfinder_be.dto.ItRunSpendDto;
import com.example.pathfinder_be.dto.ItSpendCategoriesDto;
import com.example.pathfinder_be.helper.AssetClassificationCalc;
import com.example.pathfinder_be.helper.CalculationHelper;
import com.example.pathfinder_be.repo.AssetClassificationRepo;
import com.example.pathfinder_be.repo.CostofTransformationRepo;
import com.example.pathfinder_be.repo.InputTablesRepo;
import com.example.pathfinder_be.repo.ItSpendCategoriesRepo;
import com.example.pathfinder_be.repo.ItpersonelRepo;

@Service
public class AssetClassificationService {

	@Autowired
	InputTablesRepo inputRepo;
	
	@Autowired
	AssetClassificationCalc help;
	
	@Autowired
	CostofTransformationRepo cotRepo;
	
	@Autowired
	ItpersonelRepo  itpersonalRepo;
	@Autowired
	AssetClassificationRepo assetRepo;
	@Autowired
	ItRunSpendDto runRepo;
	@Autowired
	ItSpendCategoriesRepo spendRepo;
	
	
	public ItRunSpendDto itRunSpend_calc(ItRunSpendDto run, String id1, String id2, String id3) {
	
		InputTablesDto obj=inputRepo.findById(id1).get();
		CostofTransformationDto obj1=cotRepo.findById(id2).get();
		ItPersonnelDto obj2= itpersonalRepo.findById(id3).get();
		ItRunSpendDto obj3= help.yearBasedCalculation(run,obj2, obj, obj1);
		obj3=assetRepo.save(obj3);
	    
		return obj3;	

	}


//	public AssetClassificationDto hardware_calculation(AssetClassificationDto a,String id1, String id2, String id3) {
//		
////		AssetClassificationHardwareDto obj3= assetRepo.findById(id3);
////		ItRunSpendDto obj1= runRepo.
////		InputTablesDto obj=inputRepo.findById(id1).get();
////		Optional<ItSpendCategoriesDto> obj2= spendRepo.findById(id2);
////		AssetClassificationDto obj4 = help.hardwareCalculation(a,obj, obj2, );
////		obj4=assetRepo.save(obj4);
//		
//		return null;
//	}
//
//
//	public AssetClassificationDto software_calculation(AssetClassificationDto b, String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	public AssetClassificationDto managed_calculation(AssetClassificationDto c, String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	public AssetClassificationDto hosted_calculation(AssetClassificationDto d, String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
}

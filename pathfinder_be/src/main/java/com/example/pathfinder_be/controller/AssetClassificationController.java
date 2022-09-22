package com.example.pathfinder_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pathfinder_be.dto.AssetClassificationDto;
import com.example.pathfinder_be.dto.CostofTransformationDto;
import com.example.pathfinder_be.dto.InputTablesDto;
import com.example.pathfinder_be.dto.ItPersonnelDto;
import com.example.pathfinder_be.dto.ItRunSpendDto;
import com.example.pathfinder_be.service.AssetClassificationService;


@RestController
@RequestMapping("/asset")
public class AssetClassificationController {
	
	@Autowired
	AssetClassificationService assetService;

	@PostMapping("/itrun/{id}")
	public ResponseEntity<?> itRunSpend_calc(@RequestBody ItRunSpendDto run,String id1, String id2, String id3, @PathVariable String id) {
    	ItRunSpendDto input = assetService.itRunSpend_calc(run,id1, id2, id3);
        return ResponseEntity.ok(input);
        
    }
	
	
//	@PostMapping("/hardware/{id}")
//	public ResponseEntity<?> hardware_calc(@RequestBody AssetClassificationDto a, @PathVariable String id) {
//    	AssetClassificationDto val = assetService.hardware_calculation(a,id, id, id);
//        return ResponseEntity.ok(val);
//        
//    }
//	
//	@PostMapping("/software/{id}")
//	public ResponseEntity<?> software_calc(@RequestBody AssetClassificationDto b, @PathVariable String id) {
//    	AssetClassificationDto val = assetService.software_calculation(b,id);
//        return ResponseEntity.ok(val);
//        
//    }
//	
//	@PostMapping("/managed/{id}")
//	public ResponseEntity<?> managed_calc(@RequestBody AssetClassificationDto c, @PathVariable String id) {
//    	AssetClassificationDto val = assetService.managed_calculation(c,id);
//        return ResponseEntity.ok(val);
//        
//    }
//	
//	@PostMapping("/hosted/{id}")
//	public ResponseEntity<?> hosted_calc(@RequestBody AssetClassificationDto d, @PathVariable String id) {
//    	AssetClassificationDto val = assetService.hosted_calculation(d,id);
//        return ResponseEntity.ok(val);
//        
//    }
	
}

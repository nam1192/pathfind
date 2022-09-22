package com.example.pathfinder_be.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pathfinder_be.dto.AssetClassificationDto;
import com.example.pathfinder_be.dto.CostofTransformationDto;
import com.example.pathfinder_be.dto.ItRunSpendDto;
import com.example.pathfinder_be.dto.YearBasedTransformationDto;

@Repository
public interface AssetClassificationRepo extends MongoRepository<ItRunSpendDto, String >{

	@Query("{ 'pathfinder_cost_of_transformation': { $in: { 'client_share_value': ?3 } } }")List<CostofTransformationDto> findByInstock(long client_share_value);

	AssetClassificationDto save(AssetClassificationDto obj4);

}

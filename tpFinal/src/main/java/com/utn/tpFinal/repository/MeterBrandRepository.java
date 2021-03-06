package com.utn.tpFinal.repository;

import com.utn.tpFinal.model.MeterBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterBrandRepository extends JpaRepository<MeterBrand, Integer>, JpaSpecificationExecutor<MeterBrand> {

}

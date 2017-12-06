package com.jrcx.ads.domain.dao;

import com.jrcx.ads.domain.PieceProgramme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Administrator.
 * @date 2017/11/23
 */
public interface PieceProgrammeRepository extends JpaRepository<PieceProgramme, Long>{

    List<PieceProgramme> findByProgrammeIdOrderByIndexNumAsc(Long programmeId);

}

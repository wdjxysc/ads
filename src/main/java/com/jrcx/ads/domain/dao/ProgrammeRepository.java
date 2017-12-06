package com.jrcx.ads.domain.dao;

import com.jrcx.ads.domain.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Administrator.
 * @date 2017/11/23
 */
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    Programme findByName(String name);

    Programme findBySn(String sn);

    List<Programme> findByCreateBy(String username);

}

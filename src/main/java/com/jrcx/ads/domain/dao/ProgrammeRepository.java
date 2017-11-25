package com.jrcx.ads.domain.dao;

import com.jrcx.ads.domain.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator.
 * @date 2017/11/23
 */
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    Programme findByName(String name);
}

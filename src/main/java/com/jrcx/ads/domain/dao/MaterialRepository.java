package com.jrcx.ads.domain.dao;

import com.jrcx.ads.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/11/22
 */
public interface MaterialRepository extends JpaRepository<Material, Long> {

    Material findByName(String name);

    Material findByCreateDateBetween(Date beginDate, Date endDate);
}

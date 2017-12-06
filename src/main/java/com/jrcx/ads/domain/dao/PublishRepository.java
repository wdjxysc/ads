package com.jrcx.ads.domain.dao;

import com.jrcx.ads.domain.Publish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator.
 * @date 2017/12/5
 */
public interface PublishRepository extends JpaRepository<Publish, Long> {
    public Publish findTopByOrderByCreateDateDesc();

    public Publish findTopByDeviceIdOrderByCreateDateDesc(Long deviceId);
}

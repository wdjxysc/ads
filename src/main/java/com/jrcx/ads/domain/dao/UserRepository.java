package com.jrcx.ads.domain.dao;


import com.jrcx.ads.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Administrator.
 * @date 2017/11/23
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);

    User findByCreateDateAfter(Date createDate);


    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update User u set u.updateDate=:updateDate where u.name=:name")
    int update(@Param("updateDate") Date updateDate, @Param("name") String name);
}

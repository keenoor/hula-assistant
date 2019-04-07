package com.keenor.hulaassistant.dao;


import com.keenor.hulaassistant.entity.MemberPO;
import com.keenor.hulaassistant.entity.VotePO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-07 15:27
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

public interface MemberDao extends JpaRepository<MemberPO, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM t_member")
    List<MemberPO> findMember();

}

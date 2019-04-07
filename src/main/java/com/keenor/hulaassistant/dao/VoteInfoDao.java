package com.keenor.hulaassistant.dao;


import com.keenor.hulaassistant.entity.VotePO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-07 15:27
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

public interface VoteInfoDao extends JpaRepository<VotePO, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM t_vote v WHERE !(v.name='上海' AND v.nickname='111') " +
            " AND !(v.name='zhejiang' AND v.amount=0) " +
            " AND v.amount>0;")
    List<VotePO> findValid();

    @Query(nativeQuery = true, value = "SELECT * FROM t_vote v WHERE v.name='zhejiang' AND v.amount=0 limit 1")
    VotePO fixValid();

    @Query(nativeQuery = true, value = "SELECT * FROM t_vote v WHERE v.name='上海' AND v.nickname='111' limit 1")
    VotePO fixValid2();

    @Query(nativeQuery = true, value = "SELECT * FROM t_vote v WHERE v.name='上海' AND v.nickname='111' limit 1")
    VotePO findMember();

}

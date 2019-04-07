package com.keenor.hulaassistant.entity;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-07 15:22
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_member")
public class MemberPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}

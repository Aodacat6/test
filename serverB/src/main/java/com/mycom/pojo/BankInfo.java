/*
 *    Copyright (c) 2018-2025, hrois All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: hrois
 */

package com.mycom.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  
 *
 * @author yangjunbin
 * @date 2021-07-07 10:33:34
 */
@Data
public class BankInfo  {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 开证行名称
     */
    private String bankName;

    /**
     * 开证行唯一标识swift
     */
    private String swift;

    /**
     * 国家
     */
    private String country;

    /**
     * 开证行额度
     */
    private BigDecimal bankLines;

    /**
     * 额度币种
     */
    private String moneyKind;

    /**
     * 开证行类型1LC/2L 保函/3G
     */
    private Integer type;

    /**
     * 是否高危国家0否1是
     */
    private Integer highRiskFlag;

    /**
     * 有效标识 0无效 1有效
     */
    private Integer activeFlag;

    /**
     * 备注
     */
    private String memo;


	private String customerName;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;





}

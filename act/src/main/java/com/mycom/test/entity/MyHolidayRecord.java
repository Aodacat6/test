package com.mycom.test.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author ：songdalin
 * @date ：2022/6/14 下午 6:57
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Data
@TableName("my_holiday_record")
public class MyHolidayRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String processId;
}

package com.ihnsod.basics.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Ihnsod
 * @create: 2019/01/19 16:09
 **/
@Data
@Accessors(chain = true)
public class TestInsert {
    private String name;
    private Integer age;
    private Integer sex;
    private String address;
}

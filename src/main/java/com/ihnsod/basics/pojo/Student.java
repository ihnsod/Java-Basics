package com.ihnsod.basics.pojo;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author: Ihnsod
 * @create: 2019/05/01 23:49
 **/
@Data
@Builder
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer age;
    private String name;
}

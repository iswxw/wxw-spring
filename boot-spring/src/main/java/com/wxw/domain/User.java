package com.wxw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wxw
 * @date: 2021-04-15-0:45
 * @link:
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;

    private String userName;

    private String addr;
}

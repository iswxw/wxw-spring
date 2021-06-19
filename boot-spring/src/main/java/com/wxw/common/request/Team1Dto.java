package com.wxw.common.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 队伍实体
 * 数据传输对象
 */
@Data
public class Team1Dto {
    /**
     * 队伍名称
     */
    @NotBlank(message = "队伍名称不能为空！")
    private String name;
    /**
     * 队伍人员
     */
    @NotNull(message = "队伍人员不能为空！")
    @Valid
    private List<User1Dto> userList;

    /**
     * 队伍负责人
     */
    @NotNull(message = "队伍负责人不能为空！")
    @Valid
    private User1Dto user;
}
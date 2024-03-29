package com.zj.entities.sys.dto;

import com.zj.entities.sys.dto.insert.Insert;
import com.zj.entities.sys.dto.update.Update;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/13 14:19
 */
@Data
public class RoleDto {
    @NotEmpty(message = "角色id不能为空",groups = Update.class)
    private String id;
    @NotEmpty(message = "角色名称不能为空",groups = Insert.class )
    private String roleName;

    private String belongId;

    @NotEmpty(message = "介绍信息不能为空",groups = Insert.class )
    private String introduce;
}

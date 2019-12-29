package org.jeecg.modules.shop.model;

import lombok.Data;
import org.jeecg.modules.shop.entity.Assistant;
import org.jeecg.modules.system.entity.SysUser;

import java.util.Date;

@Data
public class AssistantModel {
    private Assistant assistant;
    private SysUser user;
}

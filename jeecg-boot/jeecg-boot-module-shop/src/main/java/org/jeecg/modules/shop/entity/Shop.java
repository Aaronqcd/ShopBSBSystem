package org.jeecg.modules.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 店铺
 * @Author: aaron
 * @Date:   2019-12-16
 * @Version: V1.0
 */
@Data
@TableName("tb_shop")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_shop对象", description="店铺")
public class Shop {
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
	private String id;
	/**店铺名称*/
	@Excel(name = "店铺名称", width = 15)
    @ApiModelProperty(value = "店铺名称")
	private String name;
	/**店铺地址*/
	@Excel(name = "店铺地址", width = 15)
    @ApiModelProperty(value = "店铺地址")
	private String address;
	/**开业时间*/
	@Excel(name = "开业时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开业时间")
	private Date openingDate;
	/**所有者*/
	@Excel(name = "所有者", width = 15)
    @ApiModelProperty(value = "所有者")
	private String possessor;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String phone;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15)
    @ApiModelProperty(value = "删除标识0-正常,1-已删除")
	private Integer delFlag;
	/**微信号*/
	@Excel(name = "微信号", width = 15)
	@ApiModelProperty(value = "微信号")
	private String wechatId;
	/**邮编*/
	@Excel(name = "邮编", width = 15)
	@ApiModelProperty(value = "邮编")
	private String postcode;
	/**营业额*/
	@Excel(name = "营业额", width = 10)
	@ApiModelProperty(value = "营业额")
	private BigDecimal turnover;
	/**已结营业额*/
	@Excel(name = "已结营业额", width = 10)
	@ApiModelProperty(value = "已结营业额")
	private BigDecimal turnover_closed;
	/**未结营业额*/
	@Excel(name = "未结营业额", width = 10)
	@ApiModelProperty(value = "未结营业额")
	private BigDecimal turnover_outstanding;
}

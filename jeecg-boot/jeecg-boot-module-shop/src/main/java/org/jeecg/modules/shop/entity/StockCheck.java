package org.jeecg.modules.shop.entity;

import java.io.Serializable;
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
 * @Description: 库存盘点
 * @Author: jeecg-boot
 * @Date:   2020-01-01
 * @Version: V1.0
 */
@Data
@TableName("tb_stock_check")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_stock_check对象", description="库存盘点")
public class StockCheck {
    
	/**账存数量*/
	@Excel(name = "账存数量", width = 15)
    @ApiModelProperty(value = "账存数量")
	private java.math.BigDecimal accountDepositAmount;
	/**营业员id*/
	@Excel(name = "营业员id", width = 15)
    @ApiModelProperty(value = "营业员id")
	private String assistantId;
	/**单据日期*/
	@Excel(name = "单据日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "单据日期")
	private Date certificateDate;
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
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String delFlag;
	/**说明*/
	@Excel(name = "说明", width = 15)
    @ApiModelProperty(value = "说明")
	private String description;
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
	private String id;
	/**实际数量*/
	@Excel(name = "实际数量", width = 15)
    @ApiModelProperty(value = "实际数量")
	private java.math.BigDecimal practicalAmount;
	/**盈亏数量*/
	@Excel(name = "盈亏数量", width = 15)
    @ApiModelProperty(value = "盈亏数量")
	private java.math.BigDecimal profitLossAmount;
	/**盈亏金额*/
	@Excel(name = "盈亏金额", width = 15)
    @ApiModelProperty(value = "盈亏金额")
	private java.math.BigDecimal profitLossMoney;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**库存盘点单编号*/
	@Excel(name = "库存盘点单编号", width = 15)
    @ApiModelProperty(value = "库存盘点单编号")
	private String stockCheckNo;
	/**货品库存表id*/
	@Excel(name = "货品库存表id", width = 15)
    @ApiModelProperty(value = "货品库存表id")
	private String stockId;
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
}

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
 * @Description: 资金流水
 * @Author: jeecg-boot
 * @Date:   2020-01-01
 * @Version: V1.0
 */
@Data
@TableName("tb_journal_account")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_journal_account对象", description="资金流水")
public class JournalAccount {
    
	/**收支项目*/
	@Excel(name = "收支项目", width = 15)
    @ApiModelProperty(value = "收支项目")
	private String balanceProject;
	/**往来单位*/
	@Excel(name = "往来单位", width = 15)
    @ApiModelProperty(value = "往来单位")
	private String btype;
	/**业务日期*/
	@Excel(name = "业务日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "业务日期")
	private Date businessDate;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
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
	/**支出*/
	@Excel(name = "支出", width = 15)
    @ApiModelProperty(value = "支出")
	private java.math.BigDecimal expend;
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
	private String id;
	/**收入*/
	@Excel(name = "收入", width = 15)
    @ApiModelProperty(value = "收入")
	private java.math.BigDecimal income;
	/**支付类型id*/
	@Excel(name = "支付类型id", width = 15)
    @ApiModelProperty(value = "支付类型id")
	private String paymentTypeId;
	/**结余*/
	@Excel(name = "结余", width = 15)
    @ApiModelProperty(value = "结余")
	private java.math.BigDecimal surplus;
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

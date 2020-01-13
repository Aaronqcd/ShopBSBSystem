package org.jeecg.modules.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @Description: 销售单
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
@Data
@TableName("tb_sales")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_sales对象", description="销售单")
public class Sales {
    
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
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String customerName;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15)
    @ApiModelProperty(value = "删除标识0-正常,1-已删除")
	private Integer delFlag;
	/**折后金额*/
	@Excel(name = "折后金额", width = 15)
    @ApiModelProperty(value = "折后金额")
	private java.math.BigDecimal discountMoney;
	/**折扣率*/
	@Excel(name = "折扣率", width = 15)
    @ApiModelProperty(value = "折扣率")
	private java.math.BigDecimal discountRate;
	/**找零*/
	@Excel(name = "找零", width = 15)
    @ApiModelProperty(value = "找零")
	private java.math.BigDecimal dispenser;
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
	private String id;
	/**支付类型id*/
	@Excel(name = "支付类型id", width = 15)
    @ApiModelProperty(value = "支付类型id")
	private String paymentTypeId;
	/**实收*/
	@Excel(name = "实收", width = 15)
    @ApiModelProperty(value = "实收")
	private java.math.BigDecimal receipts;
	/**本单应收*/
	@Excel(name = "本单应收", width = 15)
    @ApiModelProperty(value = "本单应收")
	private java.math.BigDecimal receivable;
	/**销售单编号*/
	@Excel(name = "销售单编号", width = 15)
    @ApiModelProperty(value = "销售单编号")
	private String salesNo;
	/**结算账户id*/
	@Excel(name = "结算账户id", width = 15)
    @ApiModelProperty(value = "结算账户id")
	private String settlementAccountId;
	/**店铺id*/
	@Excel(name = "店铺id", width = 15)
    @ApiModelProperty(value = "店铺id")
	private String shopId;
	/**销售状态，1表示出货，2表示退货*/
	@Excel(name = "销售状态", width = 15)
    @ApiModelProperty(value = "销售状态")
	private String saleState;
	/**状态，1表示未保存，2表示已保存*/
	@Excel(name = "状态", width = 15)
	@ApiModelProperty(value = "状态")
	private String state;
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

	@TableField(exist = false)
	private List<SalesCommodity> salesCommodityList;

	@TableField(exist = false)
	private PaymentType paymentType;

	@TableField(exist = false)
	private Shop shop;

	@TableField(exist = false)
	private Assistant assistant;

	@TableField(exist = false)
	private SettlementAccount settlementAccount;
}

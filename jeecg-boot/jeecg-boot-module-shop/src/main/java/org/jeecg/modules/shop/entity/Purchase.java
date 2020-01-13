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
 * @Description: 进货单
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Data
@TableName("tb_purchase")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_purchase对象", description="进货单")
public class Purchase {
	/**进货员*/
	@Excel(name = "进货员", width = 15)
    @ApiModelProperty(value = "进货员")
	private String buyer;
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
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String delFlag;
	/**折后金额*/
	@Excel(name = "折后金额", width = 15)
    @ApiModelProperty(value = "折后金额")
	private java.math.BigDecimal discountMoney;
	/**折扣率*/
	@Excel(name = "折扣率", width = 15)
    @ApiModelProperty(value = "折扣率")
	private java.math.BigDecimal discountRate;
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
	private String id;
	/**本次付款*/
	@Excel(name = "本次付款", width = 15)
    @ApiModelProperty(value = "本次付款")
	private java.math.BigDecimal payment;
	/**支付类型id*/
	@Excel(name = "支付类型id", width = 15)
    @ApiModelProperty(value = "支付类型id")
	private String paymentTypeId;
	/**进货单编号*/
	@Excel(name = "进货单编号", width = 15)
    @ApiModelProperty(value = "进货单编号")
	private String purchaseNo;
	/**采购状态*/
	@Excel(name = "采购状态", width = 15)
    @ApiModelProperty(value = "采购状态")
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
	private List<PurchaseCommodity> purchaseCommodityList;

	@TableField(exist = false)
	private PaymentType paymentType;
}

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

import javax.persistence.Transient;

/**
 * @Description: 货品库存
 * @Author: jeecg-boot
 * @Date:   2020-01-01
 * @Version: V1.0
 */
@Data
@TableName("tb_stock")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_stock对象", description="货品库存")
public class Stock {
	/**单据日期*/
	@Excel(name = "单据日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "单据日期")
	private Date certificateDate;
	/**商品表id*/
	@Excel(name = "商品表id", width = 15)
    @ApiModelProperty(value = "商品表id")
	private String commodityId;
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
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
	private String id;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String state;
	/**库存数*/
	@Excel(name = "库存数", width = 15)
    @ApiModelProperty(value = "库存数")
	private java.math.BigDecimal stockAmount;
	/**货品库存单编号*/
	@Excel(name = "货品库存单编号", width = 15)
    @ApiModelProperty(value = "货品库存单编号")
	private String stockNo;
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
	private Commodity commodity;
}

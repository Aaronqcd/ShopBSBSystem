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
 * @Description: 系统参数
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Data
@TableName("tb_setting")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_setting对象", description="系统参数")
public class Setting {
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
	private String id;
	/**店铺经营报告*/
	@Excel(name = "店铺经营报告", width = 15)
    @ApiModelProperty(value = "店铺经营报告")
	private String operationReport;
	/**单据应用*/
	@Excel(name = "单据应用", width = 15)
    @ApiModelProperty(value = "单据应用")
	private String applicationDocuments;
	/**小数位数设置*/
	@Excel(name = "小数位数设置", width = 15)
    @ApiModelProperty(value = "小数位数设置")
	private String decimalSetting;
	/**销售单价格控制*/
	@Excel(name = "销售单价格控制", width = 15)
    @ApiModelProperty(value = "销售单价格控制")
	private String priceControl;
	/**推送微信号*/
	@Excel(name = "推送微信号", width = 15)
    @ApiModelProperty(value = "推送微信号")
	private String pushWechatId;
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
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String delFlag;
}

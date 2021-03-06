package com.example.employee_management.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 企业用户账号管理
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("em_corporate_user_account")
public class EmCorporateUserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id，唯一识别符，")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;

    /**
     * 所属企业（关联企业信息管理表）
     */
    private Integer corporateId;

    /**
     * 常用手机号
     */
    @ApiModelProperty("常用手机号")
    private String phone;

    /**
     * 企业用户编号
     */
    @ApiModelProperty("企业用户编号")
    private String corporateUserNumber;

    /**
     * 状态(enable:启用,paus:暂停,sign_out:注销)
     */
    @ApiModelProperty("状态(enable:启用,paus:暂停,sign_out:注销")
    private String status;

    /**
     * 类型(admin:企业管理员,user:企业用户)
     */
    @ApiModelProperty("企业用户")
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 所属企业
     */
    EmCorporateInformation emCorporateInformation;


}

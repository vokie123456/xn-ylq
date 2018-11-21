package com.cdkj.ylq.dto.req;

/**
 * 列表查询角色
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:51:10 
 * @history:
 */
public class XN805021Req {

    // 角色名称(选填)
    private String name;

    // 更新人(选填)
    private String updater;

    // 系统编号（必填）
    private String companyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}

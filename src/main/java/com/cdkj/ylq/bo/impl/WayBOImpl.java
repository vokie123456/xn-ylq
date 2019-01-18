package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.IWayBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IWayDAO;
import com.cdkj.ylq.domain.Way;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EUserRefereeType;
import com.cdkj.ylq.enums.EWayStatus;
import com.cdkj.ylq.exception.BizException;

@Component
public class WayBOImpl extends PaginableBOImpl<Way> implements IWayBO {

    @Autowired
    private IWayDAO wayDAO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public boolean isWayExist(String code) {
        Way condition = new Way();
        condition.setCode(code);
        if (wayDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveWay(String name, String companyCode, String userId,
            String remark) {
        Way data = new Way();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.WAY.getCode());
        StringBuilder productUrl = new StringBuilder(
            sysConfigBO.getStringValue(SysConstants.WAY_URL, companyCode))
            .append("/product/product-list.html").append("?code=").append(code)
            .append("&userRefereeKind=").append(EUserRefereeType.W.getCode())
            .append("&companyCode=").append(companyCode);
        StringBuilder regUrl = new StringBuilder(sysConfigBO.getStringValue(
            SysConstants.WAY_URL, companyCode)).append("/user/register.html")
            .append("?code=").append(code).append("&userRefereeKind=")
            .append(EUserRefereeType.W.getCode()).append("&companyCode=")
            .append(companyCode);
        data.setCode(code);
        data.setName(name);
        data.setProductUrl(productUrl.toString());
        data.setProductPointCount(0L);
        data.setRegUrl(regUrl.toString());
        data.setRegPointCount(0L);
        data.setUserCount(0L);
        data.setStatus(EWayStatus.NORMAL.getCode());
        data.setCreateDatetime(new Date());
        data.setCompanyCode(companyCode);
        data.setUserId(userId);
        data.setRemark(remark);
        wayDAO.insert(data);
        return code;
    }

    @Override
    public int removeWay(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Way data = new Way();
            data.setCode(code);
            count = wayDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshWay(Way data, String name, String updater, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            data.setName(name);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            count = wayDAO.updateWay(data);
        }
        return count;
    }

    @Override
    public List<Way> queryWayList(Way condition) {
        return wayDAO.selectList(condition);
    }

    @Override
    public Way getWay(String code) {
        Way data = null;
        if (StringUtils.isNotBlank(code)) {
            Way condition = new Way();
            condition.setCode(code);
            data = wayDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "渠道不存在");
            }
        }
        return data;
    }

    @Override
    public int refreshProductPointCount(Way data, Long count) {
        int I = 0;
        if (data != null) {
            data.setProductPointCount(data.getProductPointCount() + count);
            I = wayDAO.updateProductPointCount(data);
        }
        return I;
    }

    @Override
    public int refreshRegPointCount(Way data, Long count) {

        int I = 0;
        if (data != null) {
            data.setRegPointCount(data.getRegPointCount() + count);
            I = wayDAO.updateRegPointCount(data);
        }
        return I;
    }

    @Override
    public int refreshUserCount(Way data, Long count) {
        int I = 0;
        if (data != null) {
            data.setUserCount(data.getUserCount() + count);
            I = wayDAO.updateUserCount(data);
        }
        return I;
    }

    @Override
    public void refreshStatus(Way data) {
        if (EWayStatus.Locked.getCode().equals(data.getStatus())) {
            data.setStatus(EWayStatus.NORMAL.getCode());
        } else {
            data.setStatus(EWayStatus.Locked.getCode());
        }
        wayDAO.updateWay(data);
    }

}

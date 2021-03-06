package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.UserCoupon;

public interface IUserCouponDAO extends IBaseDAO<UserCoupon> {
    String NAMESPACE = IUserCouponDAO.class.getName().concat(".");

    public int updateRecycle(UserCoupon data);

    public int updateUse(UserCoupon data);

    public int updateInvalid(UserCoupon data);
}

package com.cdkj.ylq.domain;

import com.cdkj.ylq.domain.ZqznInfoBack;
import com.cdkj.ylq.domain.ZqznInfoFront;
import com.cdkj.ylq.domain.ZqznInfoRealAuth;

public class InfoZqzn {

    ZqznInfoFront zqznInfoFront;

    ZqznInfoBack zqznInfoBack;

    ZqznInfoRealAuth zqznInfoRealAuth;

    public ZqznInfoFront getZqznInfoFront() {
        return zqznInfoFront;
    }

    public void setZqznInfoFront(ZqznInfoFront zqznInfoFront) {
        this.zqznInfoFront = zqznInfoFront;
    }

    public ZqznInfoBack getZqznInfoBack() {
        return zqznInfoBack;
    }

    public void setZqznInfoBack(ZqznInfoBack zqznInfoBack) {
        this.zqznInfoBack = zqznInfoBack;
    }

    public ZqznInfoRealAuth getZqznInfoRealAuth() {
        return zqznInfoRealAuth;
    }

    public void setZqznInfoRealAuth(ZqznInfoRealAuth zqznInfoRealAuth) {
        this.zqznInfoRealAuth = zqznInfoRealAuth;
    }
}

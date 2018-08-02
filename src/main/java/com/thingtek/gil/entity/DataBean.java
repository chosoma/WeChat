package com.thingtek.gil.entity;

import com.thingtek.base.entity.BaseBean;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public @Data
class DataBean extends BaseBean implements Serializable {

    private String place, xw;
    private byte gatewayType, gatewayNumber, unitType, unitNumber;
    private float pres, temp, den, vari, batlv, hitchvol;
    private Date date;
    private int point;
    private boolean lowPres, lowLock;
    private byte warn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataBean dataBean = (DataBean) o;

        if (unitType != dataBean.unitType) return false;
        if (unitNumber != dataBean.unitNumber) return false;
        return date != null ? date.equals(dataBean.date) : dataBean.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) unitType;
        result = 31 * result + (int) unitNumber;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DataBean{date=" + date +
                '}';
    }


}

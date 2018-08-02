package com.thingtek.gil.entity;

import lombok.Data;

public@Data
class PointBean implements Comparable<PointBean> {
    private int id;
    private int point;
    private String place;
    private byte unitType;
    private byte gatewayType, gatewayNumber;
    private float x, y;

    public byte getUnitType() {
        return unitType;
    }

    @Override
    public String toString() {
        return "PointBean{" +
                "id=" + id +
                ", point=" + point +
                ", place='" + place + '\'' +
                ", unitType=" + unitType +
                ", gatewayType=" + gatewayType +
                ", gatewayNumber=" + gatewayNumber +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public PointBean() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointBean pointBean = (PointBean) o;

        return point == pointBean.point;
    }

    @Override
    public int hashCode() {
        return point;
    }

    @Override
    public int compareTo(PointBean o) {
        return point - o.point;
    }
}

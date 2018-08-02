package com.thingtek.gil.entity;

import lombok.Data;

/**
 * 网关实体类
 */
public@Data
class NetBean implements Comparable<NetBean>{

    private String sim;//类型,sim卡号
    private byte type;
    private byte number;//网关id
    private byte channel;

    @Override
    public String toString() {
        return "NetBean{" +
                "sim='" + sim + '\'' +
                ", type=" + type +
                ", number=" + number +
                ", channel=" + channel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetBean netBean = (NetBean) o;

        return type == netBean.type && number == netBean.number;
    }

    @Override
    public int hashCode() {
        int result = (int) type;
        result = 31 * result + (int) number;
        return result;
    }

    @Override
    public int compareTo(NetBean o) {
        if(type!=o.type){
            return type-o.type;
        }
        int n1 = number&0xff;
        int n2 = o.number&0xff;
        return n1-n2;
    }
}

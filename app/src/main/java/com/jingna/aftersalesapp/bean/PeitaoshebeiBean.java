package com.jingna.aftersalesapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/8/7.
 */

public class PeitaoshebeiBean implements Serializable {

    /**
     * status : 200
     * data : [{"id":107,"name":"电钻","dayMoney":20,"company":"天"},{"id":108,"name":"螺丝","dayMoney":1,"company":"包"},{"id":109,"name":"斧子","dayMoney":10,"company":"天"},{"id":110,"name":"机床","dayMoney":200,"company":"天"},{"id":111,"name":"桌子","dayMoney":30,"company":"天"},{"id":112,"name":"台式钻","dayMoney":10,"company":"天"},{"id":113,"name":"手电钻","dayMoney":10,"company":"天"},{"id":114,"name":"橡胶锤","dayMoney":10,"company":"天"},{"id":115,"name":"刮刀","dayMoney":10,"company":"天"},{"id":116,"name":"梅花搬手","dayMoney":15,"company":"天"},{"id":117,"name":"内六角搬手","dayMoney":10,"company":"天"},{"id":118,"name":"铁砧","dayMoney":20,"company":"天"},{"id":119,"name":"平口钳","dayMoney":15,"company":"天"},{"id":120,"name":"台虎钳","dayMoney":15,"company":"天"},{"id":121,"name":"断线钳","dayMoney":15,"company":"天"}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 107
         * name : 电钻
         * dayMoney : 20
         * company : 天
         */

        private int id;
        private String name;
        private int dayMoney;
        private String company;
        private int isSelect = 0;
        private int num = 1;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getIsSelect() {
            return isSelect;
        }

        public void setIsSelect(int isSelect) {
            this.isSelect = isSelect;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDayMoney() {
            return dayMoney;
        }

        public void setDayMoney(int dayMoney) {
            this.dayMoney = dayMoney;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }
}

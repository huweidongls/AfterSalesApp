package com.jingna.aftersalesapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/4/15.
 */

public class AppMemberCommissionAuditqueryListAppBean {

    /**
     * status : 200
     * data : [{"id":20,"memberId":47,"auditMoney":3,"createTime":"2020-04-14 13:52:49","isPass":0},{"id":19,"memberId":47,"auditMoney":5,"createTime":"2020-04-14 13:52:32","isPass":0},{"id":18,"memberId":47,"auditMoney":5,"createTime":"2020-04-14 11:07:42","isPass":1},{"id":17,"memberId":48,"auditMoney":0,"createTime":"2020-04-13 14:54:25","isPass":0},{"id":16,"memberId":48,"auditMoney":0,"createTime":"2020-04-12 18:38:06","isPass":0},{"id":15,"memberId":47,"auditMoney":1,"createTime":"2020-04-09 10:43:26","isPass":2},{"id":14,"memberId":47,"auditMoney":1,"createTime":"2020-04-08 16:23:03","isPass":2},{"id":13,"memberId":47,"auditMoney":22,"createTime":"2020-04-08 16:18:19","isPass":2},{"id":12,"memberId":47,"auditMoney":2,"createTime":"2020-04-07 16:33:52","isPass":1},{"id":11,"memberId":47,"auditMoney":0,"createTime":"2020-04-07 14:28:35","isPass":2},{"id":10,"memberId":47,"auditMoney":0,"createTime":"2020-04-03 13:43:23","isPass":1},{"id":9,"memberId":47,"auditMoney":0,"createTime":"2020-04-03 13:42:06","isPass":2},{"id":8,"memberId":47,"auditMoney":0,"createTime":"2020-04-03 13:40:40","isPass":2},{"id":7,"memberId":47,"auditMoney":0,"createTime":"2020-04-03 13:39:30","isPass":1},{"id":6,"memberId":47,"auditMoney":0,"createTime":"2020-04-03 13:37:48","isPass":2},{"id":5,"memberId":47,"auditMoney":0,"createTime":"2020-04-03 09:38:57","isPass":2},{"id":4,"memberId":47,"auditMoney":0,"createTime":"2020-04-02 15:25:44","isPass":1},{"id":3,"memberId":47,"auditMoney":0,"createTime":"2020-04-02 11:31:43","isPass":1},{"id":2,"memberId":47,"auditMoney":0,"createTime":"2020-04-02 11:31:24","isPass":1},{"id":1,"memberId":47,"auditMoney":0,"createTime":"2020-04-02 09:37:26","isPass":1}]
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

    public static class DataBean {
        /**
         * id : 20
         * memberId : 47
         * auditMoney : 3
         * createTime : 2020-04-14 13:52:49
         * isPass : 0
         */

        private int id;
        private int engineerId;
        private double auditMoney;
        private String createTime;
        private int isPass;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMemberId() {
            return engineerId;
        }

        public void setMemberId(int engineerId) {
            this.engineerId = engineerId;
        }

        public double getAuditMoney() {
            return auditMoney;
        }

        public void setAuditMoney(double auditMoney) {
            this.auditMoney = auditMoney;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getIsPass() {
            return isPass;
        }

        public void setIsPass(int isPass) {
            this.isPass = isPass;
        }
    }
}

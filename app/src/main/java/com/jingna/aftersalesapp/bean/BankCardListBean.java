package com.jingna.aftersalesapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/7/23.
 */

public class BankCardListBean implements Serializable {

    /**
     * status : 200
     * errorMsg :
     * data : [{"id":1,"userId":46,"bankCardNum":"12580","phone":"123456789","cardType":"中国银行","isDelete":0,"createTime":"2019-07-23T03:04:54.000+0000","updateTime":""}]
     * totalPage :
     * totalCount :
     * userNameFromToken :
     */

    private String status;
    private String errorMsg;
    private String totalPage;
    private String totalCount;
    private String userNameFromToken;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getUserNameFromToken() {
        return userNameFromToken;
    }

    public void setUserNameFromToken(String userNameFromToken) {
        this.userNameFromToken = userNameFromToken;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * userId : 46
         * bankCardNum : 12580
         * phone : 123456789
         * cardType : 中国银行
         * isDelete : 0
         * createTime : 2019-07-23T03:04:54.000+0000
         * updateTime :
         */

        private int id;
        private int userId;
        private String bankCardNum;
        private String phone;
        private String cardType;
        private int isDelete;
        private String createTime;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getBankCardNum() {
            return bankCardNum;
        }

        public void setBankCardNum(String bankCardNum) {
            this.bankCardNum = bankCardNum;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}

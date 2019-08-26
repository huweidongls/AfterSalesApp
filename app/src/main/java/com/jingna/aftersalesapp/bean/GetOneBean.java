package com.jingna.aftersalesapp.bean;

/**
 * Created by Administrator on 2019/7/23.
 */

public class GetOneBean {

    /**
     * status : 200
     * data : {"id":50,"memBirthday":"1998-3-3","memName":"啊实打实的","headPhoto":"asd","username":"18686817319","password":"e10adc3949ba59abbe56e057f20f883e","phoneNum":"18686817319","newTime":"2019-08-26T02:18:51.000+0000","gender":"0","totalAmount":0,"withdrawableCash":0,"historicalCashWithdrawal":0}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 50
         * memBirthday : 1998-3-3
         * memName : 啊实打实的
         * headPhoto : asd
         * username : 18686817319
         * password : e10adc3949ba59abbe56e057f20f883e
         * phoneNum : 18686817319
         * newTime : 2019-08-26T02:18:51.000+0000
         * gender : 0
         * totalAmount : 0
         * withdrawableCash : 0
         * historicalCashWithdrawal : 0
         */

        private int id;
        private String memBirthday;
        private String memName;
        private String headPhoto;
        private String username;
        private String password;
        private String phoneNum;
        private String newTime;
        private String gender;
        private int totalAmount;
        private int withdrawableCash;
        private int historicalCashWithdrawal;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemBirthday() {
            return memBirthday;
        }

        public void setMemBirthday(String memBirthday) {
            this.memBirthday = memBirthday;
        }

        public String getMemName() {
            return memName;
        }

        public void setMemName(String memName) {
            this.memName = memName;
        }

        public String getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(String headPhoto) {
            this.headPhoto = headPhoto;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getNewTime() {
            return newTime;
        }

        public void setNewTime(String newTime) {
            this.newTime = newTime;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getWithdrawableCash() {
            return withdrawableCash;
        }

        public void setWithdrawableCash(int withdrawableCash) {
            this.withdrawableCash = withdrawableCash;
        }

        public int getHistoricalCashWithdrawal() {
            return historicalCashWithdrawal;
        }

        public void setHistoricalCashWithdrawal(int historicalCashWithdrawal) {
            this.historicalCashWithdrawal = historicalCashWithdrawal;
        }
    }
}

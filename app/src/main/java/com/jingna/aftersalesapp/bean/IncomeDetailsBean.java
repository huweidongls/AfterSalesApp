package com.jingna.aftersalesapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/11/25.
 */

public class IncomeDetailsBean {

    /**
     * status : 200
     * data : [{"year":"2019","list":[{"title":"20190826104446656","time":"11-22 15:22","type":"0","price":"0.00","headPhoto":""},{"title":"中国银行","time":"09-04 14:58","type":"1","price":"10.00","headPhoto":""}],"chu":"10.0","ru":"0.0"},{"year":"2018","list":[{"title":"20190826105907227","time":"11-22 15:22","type":"0","price":"5000.00","headPhoto":""}],"chu":"0.0","ru":"5000.0"},{"year":"2017","list":[{"title":"20190826163453334","time":"11-22 15:22","type":"0","price":"0.00","headPhoto":""}],"chu":"0.0","ru":"0.0"}]
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
         * year : 2019
         * list : [{"title":"20190826104446656","time":"11-22 15:22","type":"0","price":"0.00","headPhoto":""},{"title":"中国银行","time":"09-04 14:58","type":"1","price":"10.00","headPhoto":""}]
         * chu : 10.0
         * ru : 0.0
         */

        private String year;
        private String chu;
        private String ru;
        private List<ListBean> list;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getChu() {
            return chu;
        }

        public void setChu(String chu) {
            this.chu = chu;
        }

        public String getRu() {
            return ru;
        }

        public void setRu(String ru) {
            this.ru = ru;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * title : 20190826104446656
             * time : 11-22 15:22
             * type : 0
             * price : 0.00
             * headPhoto :
             */

            private String title;
            private String time;
            private String type;
            private String price;
            private String headPhoto;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }
        }
    }
}

package com.jingna.aftersalesapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/26.
 */

public class IndexBean {

    /**
     * status : 200
     * data : [{"id":"20190826104446656","addresCoordinate":"126.63238520689535,45.7427950567895","addresPhone":"18686817319","addresName":"汉广街41号","appAfterSaleEquipments":[]},{"id":"20190826105907227","addresCoordinate":"126.63238520689535,45.7427950567895","addresPhone":"18686817319","addresName":"汉广街41号","appAfterSaleEquipments":[]}]
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
         * id : 20190826104446656
         * addresCoordinate : 126.63238520689535,45.7427950567895
         * addresPhone : 18686817319
         * addresName : 汉广街41号
         * appAfterSaleEquipments : []
         */

        private String id;
        private String addresCoordinate;
        private String addresPhone;
        private String addresName;
        private List<?> appAfterSaleEquipments;
        private String deviceName;

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddresCoordinate() {
            return addresCoordinate;
        }

        public void setAddresCoordinate(String addresCoordinate) {
            this.addresCoordinate = addresCoordinate;
        }

        public String getAddresPhone() {
            return addresPhone;
        }

        public void setAddresPhone(String addresPhone) {
            this.addresPhone = addresPhone;
        }

        public String getAddresName() {
            return addresName;
        }

        public void setAddresName(String addresName) {
            this.addresName = addresName;
        }

        public List<?> getAppAfterSaleEquipments() {
            return appAfterSaleEquipments;
        }

        public void setAppAfterSaleEquipments(List<?> appAfterSaleEquipments) {
            this.appAfterSaleEquipments = appAfterSaleEquipments;
        }
    }
}

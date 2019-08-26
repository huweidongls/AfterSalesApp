package com.jingna.aftersalesapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/26.
 */

public class IndexDetailsBean {

    /**
     * status : 200
     * data : {"id":"20190826105907227","deviceId":"1,2","addresPhone":"18686817319","addresUname":"哈哈哈","addresName":"汉广街41号","orderStatus":"1","createTime":"2019-08-26 16:34:53","appAfterSaleEquipments":[{"equipmentName":"污水处理设备","equipmentModel":"型号001"},{"equipmentName":"污水处理设备","equipmentModel":"型号002"}]}
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
         * id : 20190826105907227
         * deviceId : 1,2
         * addresPhone : 18686817319
         * addresUname : 哈哈哈
         * addresName : 汉广街41号
         * orderStatus : 1
         * createTime : 2019-08-26 16:34:53
         * appAfterSaleEquipments : [{"equipmentName":"污水处理设备","equipmentModel":"型号001"},{"equipmentName":"污水处理设备","equipmentModel":"型号002"}]
         */

        private String id;
        private String deviceId;
        private String addresPhone;
        private String addresUname;
        private String addresName;
        private String orderStatus;
        private String createTime;
        private List<AppAfterSaleEquipmentsBean> appAfterSaleEquipments;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getAddresPhone() {
            return addresPhone;
        }

        public void setAddresPhone(String addresPhone) {
            this.addresPhone = addresPhone;
        }

        public String getAddresUname() {
            return addresUname;
        }

        public void setAddresUname(String addresUname) {
            this.addresUname = addresUname;
        }

        public String getAddresName() {
            return addresName;
        }

        public void setAddresName(String addresName) {
            this.addresName = addresName;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<AppAfterSaleEquipmentsBean> getAppAfterSaleEquipments() {
            return appAfterSaleEquipments;
        }

        public void setAppAfterSaleEquipments(List<AppAfterSaleEquipmentsBean> appAfterSaleEquipments) {
            this.appAfterSaleEquipments = appAfterSaleEquipments;
        }

        public static class AppAfterSaleEquipmentsBean {
            /**
             * equipmentName : 污水处理设备
             * equipmentModel : 型号001
             */

            private String equipmentName;
            private String equipmentModel;

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public String getEquipmentModel() {
                return equipmentModel;
            }

            public void setEquipmentModel(String equipmentModel) {
                this.equipmentModel = equipmentModel;
            }
        }
    }
}

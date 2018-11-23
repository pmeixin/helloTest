package com.unisinsight.vdp.core.dto.response.bean;

public class AlgorithmRepositoryDataInfo {

    private static final long serialVersionUID = -2442633768111985319L;

    /**
     * 镜像ID
     */
    private String imageId;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 算法厂商，，支持列表为:KuangShi、ShenMo
     */
    private String vendor;

    /**
     * 算法sdk版本
     */
    private String sdkVersion;


    /**
     * 镜像名称
     */
    private String name;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 镜像大小
     */
    private String size;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

package com.unisinsight.vdp.core.common.enums;


/**
 * 数据存留期类型eunm
 *
 * @author liujiaji [HZ.liujiaji@h3c.com]
 * @date 2018/10/25 10:43
 * @since 1.0
 */
public enum DataRetentionPeriodTypeEnum {

    STRUCTURE(0,"结构化数据"),COOL(1,"特征冷数据"),HOT(2,"特征热数据");

    /**
     * 类型值
     */
    private Integer num;
    /**
     * 类描述
     */
    private String name;

    DataRetentionPeriodTypeEnum(Integer num,String name){
        this.num = num;
        this.name = name;
    }

    /**
     * 根据编号获取枚举
     *
     * @param num
     * @return DataRetentionPeriodTypeEnum
     */
    public static DataRetentionPeriodTypeEnum getByNum(Integer num) {
        for (DataRetentionPeriodTypeEnum typeEnum : values()) {
            if (typeEnum.getNum().equals(num)) {
                return typeEnum;
            }
        }
        return null;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


}

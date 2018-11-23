package com.unisinsight.vdp.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "data_retention_period")
public class DataRetentionPeriod {

    @Id
    private Short id;

    private Integer type;

    private Integer vehicle;

    @Column(name = "face_snap")
    private Integer faceSnap;

    private Integer person;

    @Column(name = "vehicle_notification")
    private Integer vehicleNotification;

    @Column(name = "face_notification")
    private Integer faceNotification;

    /**
     * @return id
     */
    public Short getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return vehicle
     */
    public Integer getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle
     */
    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return face_snap
     */
    public Integer getFaceSnap() {
        return faceSnap;
    }

    /**
     * @param faceSnap
     */
    public void setFaceSnap(Integer faceSnap) {
        this.faceSnap = faceSnap;
    }

    /**
     * @return person
     */
    public Integer getPerson() {
        return person;
    }

    /**
     * @param person
     */
    public void setPerson(Integer person) {
        this.person = person;
    }

    /**
     * @return vehicle_notification
     */
    public Integer getVehicleNotification() {
        return vehicleNotification;
    }

    /**
     * @param vehicleNotification
     */
    public void setVehicleNotification(Integer vehicleNotification) {
        this.vehicleNotification = vehicleNotification;
    }

    /**
     * @return face_notification
     */
    public Integer getFaceNotification() {
        return faceNotification;
    }

    /**
     * @param faceNotification
     */
    public void setFaceNotification(Integer faceNotification) {
        this.faceNotification = faceNotification;
    }
}
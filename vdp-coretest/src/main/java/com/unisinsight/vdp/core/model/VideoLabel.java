package com.unisinsight.vdp.core.model;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* 视频标签实体
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/11/8 16:25
* @since  1.0
*/
@Table(name = "video_label")
public class VideoLabel {
    /**
     * 视频标记id
     */
    @Id
    @Column(name = "label_id")
    private Long labelId;

    /**
     * 视频设备Id
     */
    private String id;

    /**
     * 标记开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 标记结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 标记内容
     */
    private String mark;

    /**
     * 标记类型：1标签、2锁
     */
    private Integer type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 添加人用户名
     */
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 添加人用户code
     */
    @Column(name = "create_user_code")
    private String createUserCode;

    /**
     * 更新人用户名
     */
    @Column(name = "update_user_name")
    private String updateUserName;

    /**
     * 更新人用户code
     */
    @Column(name = "update_user_code")
    private String updateUserCode;

    /**
     * 获取视频标记id
     *
     * @return label_id - 视频标记id
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 设置视频标记id
     *
     * @param labelId 视频标记id
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * 获取视频设备Id
     *
     * @return id - 视频设备Id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置视频设备Id
     *
     * @param id 视频设备Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取标记开始时间
     *
     * @return start_time - 标记开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置标记开始时间
     *
     * @param startTime 标记开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取标记结束时间
     *
     * @return end_time - 标记结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置标记结束时间
     *
     * @param endTime 标记结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取标记内容
     *
     * @return mark - 标记内容
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置标记内容
     *
     * @param mark 标记内容
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * 获取标记类型：1标签、2锁
     *
     * @return type - 标记类型：1标签、2锁
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置标记类型：1标签、2锁
     *
     * @param type 标记类型：1标签、2锁
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取添加人用户名
     *
     * @return create_user_name - 添加人用户名
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置添加人用户名
     *
     * @param createUserName 添加人用户名
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取添加人用户code
     *
     * @return create_user_code - 添加人用户code
     */
    public String getCreateUserCode() {
        return createUserCode;
    }

    /**
     * 设置添加人用户code
     *
     * @param createUserCode 添加人用户code
     */
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    /**
     * 获取更新人用户名
     *
     * @return update_user_name - 更新人用户名
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 设置更新人用户名
     *
     * @param updateUserName 更新人用户名
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    /**
     * 获取更新人用户code
     *
     * @return update_user_code - 更新人用户code
     */
    public String getUpdateUserCode() {
        return updateUserCode;
    }

    /**
     * 设置更新人用户code
     *
     * @param updateUserCode 更新人用户code
     */
    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
    }
}
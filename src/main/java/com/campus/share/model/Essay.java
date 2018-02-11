package com.campus.share.model;

import java.util.Date;

public class Essay {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.essay_id
     *
     * @mbggenerated
     */
    private Long essayId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.author_id
     *
     * @mbggenerated
     */
    private Long authorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.summary
     *
     * @mbggenerated
     */
    private String summary;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.read_num
     *
     * @mbggenerated
     */
    private Integer readNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.essay_type
     *
     * @mbggenerated
     */
    private Byte essayType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.resource_type_id
     *
     * @mbggenerated
     */
    private Long resourceTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column essay.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.essay_id
     *
     * @return the value of essay.essay_id
     *
     * @mbggenerated
     */
    public Long getEssayId() {
        return essayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.essay_id
     *
     * @param essayId the value for essay.essay_id
     *
     * @mbggenerated
     */
    public void setEssayId(Long essayId) {
        this.essayId = essayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.author_id
     *
     * @return the value of essay.author_id
     *
     * @mbggenerated
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.author_id
     *
     * @param authorId the value for essay.author_id
     *
     * @mbggenerated
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.title
     *
     * @return the value of essay.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.title
     *
     * @param title the value for essay.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.summary
     *
     * @return the value of essay.summary
     *
     * @mbggenerated
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.summary
     *
     * @param summary the value for essay.summary
     *
     * @mbggenerated
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.read_num
     *
     * @return the value of essay.read_num
     *
     * @mbggenerated
     */
    public Integer getReadNum() {
        return readNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.read_num
     *
     * @param readNum the value for essay.read_num
     *
     * @mbggenerated
     */
    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.essay_type
     *
     * @return the value of essay.essay_type
     *
     * @mbggenerated
     */
    public Byte getEssayType() {
        return essayType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.essay_type
     *
     * @param essayType the value for essay.essay_type
     *
     * @mbggenerated
     */
    public void setEssayType(Byte essayType) {
        this.essayType = essayType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.resource_type_id
     *
     * @return the value of essay.resource_type_id
     *
     * @mbggenerated
     */
    public Long getResourceTypeId() {
        return resourceTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.resource_type_id
     *
     * @param resourceTypeId the value for essay.resource_type_id
     *
     * @mbggenerated
     */
    public void setResourceTypeId(Long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.create_time
     *
     * @return the value of essay.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.create_time
     *
     * @param createTime the value for essay.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.update_time
     *
     * @return the value of essay.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.update_time
     *
     * @param updateTime the value for essay.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column essay.content
     *
     * @return the value of essay.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column essay.content
     *
     * @param content the value for essay.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
package com.campus.share.model;

import com.campus.share.bean.vo.EssayVO;

import java.util.Date;

public class FlowNode {

    public static final String PUBLISHED = "published";

    public static final String IN_PROGRESS = "inProgress";

    public static final String WAIT_CONFIRM = "waitConfirm";

    public static final String DONE = "done";

    public static final String ALL = "all"; //自定义，接口定义


    public static final String ACT_RECEIVE = "receive";

    public static final String ACT_COMPLETE = "complete";

    public static final String ACT_COMFIRM = "comfirm";

    //自定义属性

    private int actorNum; //该节点参与者人数

    private UserInfo actor;

    private String nodeValue;

    private EssayVO essay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column flow_node.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column flow_node.node_key
     *
     * @mbggenerated
     */
    private String nodeKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column flow_node.actor_id
     *
     * @mbggenerated
     */
    private Long actorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column flow_node.essay_id
     *
     * @mbggenerated
     */
    private Long essayId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column flow_node.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column flow_node.act_time
     *
     * @mbggenerated
     */
    private Date actTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column flow_node.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column flow_node.id
     *
     * @return the value of flow_node.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column flow_node.id
     *
     * @param id the value for flow_node.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column flow_node.node_key
     *
     * @return the value of flow_node.node_key
     *
     * @mbggenerated
     */
    public String getNodeKey() {
        return nodeKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column flow_node.node_key
     *
     * @param nodeKey the value for flow_node.node_key
     *
     * @mbggenerated
     */
    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column flow_node.actor_id
     *
     * @return the value of flow_node.actor_id
     *
     * @mbggenerated
     */
    public Long getActorId() {
        return actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column flow_node.actor_id
     *
     * @param actorId the value for flow_node.actor_id
     *
     * @mbggenerated
     */
    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column flow_node.essay_id
     *
     * @return the value of flow_node.essay_id
     *
     * @mbggenerated
     */
    public Long getEssayId() {
        return essayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column flow_node.essay_id
     *
     * @param essayId the value for flow_node.essay_id
     *
     * @mbggenerated
     */
    public void setEssayId(Long essayId) {
        this.essayId = essayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column flow_node.remark
     *
     * @return the value of flow_node.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column flow_node.remark
     *
     * @param remark the value for flow_node.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column flow_node.act_time
     *
     * @return the value of flow_node.act_time
     *
     * @mbggenerated
     */
    public Date getActTime() {
        return actTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column flow_node.act_time
     *
     * @param actTime the value for flow_node.act_time
     *
     * @mbggenerated
     */
    public void setActTime(Date actTime) {
        this.actTime = actTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column flow_node.status
     *
     * @return the value of flow_node.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column flow_node.status
     *
     * @param status the value for flow_node.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getActorNum() {
        return actorNum;
    }

    public void setActorNum(int actorNum) {
        this.actorNum = actorNum;
    }

    public UserInfo getActor() {
        return actor;
    }

    public void setActor(UserInfo actor) {
        this.actor = actor;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public EssayVO getEssay() {
        return essay;
    }

    public void setEssay(EssayVO essay) {
        this.essay = essay;
    }
}
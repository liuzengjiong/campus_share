package com.campus.share.dao;

import com.campus.share.bean.vo.EssayVO;
import com.campus.share.bean.vo.FlowNodeEssayVO;
import com.campus.share.model.FlowNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

public interface FlowNodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_node
     *
     * @mbggenerated
     */
    @Delete({
            "delete from flow_node",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_node
     *
     * @mbggenerated
     */
    @Insert({
            "insert into flow_node (id, node_key, ",
            "actor_id, essay_id, ",
            "remark, act_time, ",
            "status)",
            "values (#{id,jdbcType=BIGINT}, #{nodeKey,jdbcType=VARCHAR}, ",
            "#{actorId,jdbcType=BIGINT}, #{essayId,jdbcType=BIGINT}, ",
            "#{remark,jdbcType=VARCHAR}, #{actTime,jdbcType=TIMESTAMP}, ",
            "#{status,jdbcType=INTEGER})"
    })
    int insert(FlowNode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_node
     *
     * @mbggenerated
     */
    @Select({
            "select",
            "id, node_key, actor_id, essay_id, remark, act_time, status",
            "from flow_node",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="node_key", property="nodeKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="actor_id", property="actorId", jdbcType=JdbcType.BIGINT),
            @Result(column="essay_id", property="essayId", jdbcType=JdbcType.BIGINT),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="act_time", property="actTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    FlowNode selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_node
     *
     * @mbggenerated
     */
    @Select({
            "select",
            "id, node_key, actor_id, essay_id, remark, act_time, status",
            "from flow_node"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="node_key", property="nodeKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="actor_id", property="actorId", jdbcType=JdbcType.BIGINT),
            @Result(column="essay_id", property="essayId", jdbcType=JdbcType.BIGINT),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="act_time", property="actTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<FlowNode> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_node
     *
     * @mbggenerated
     */
    @Update({
            "update flow_node",
            "set node_key = #{nodeKey,jdbcType=VARCHAR},",
            "actor_id = #{actorId,jdbcType=BIGINT},",
            "essay_id = #{essayId,jdbcType=BIGINT},",
            "remark = #{remark,jdbcType=VARCHAR},",
            "act_time = #{actTime,jdbcType=TIMESTAMP},",
            "status = #{status,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FlowNode record);



    // =========================================

    @Select({
            "select",
            "id, node_key, count(actor_id) as actorNum, essay_id, remark, act_time, actor_id",
            "from flow_node",
            "where essay_id = #{essayId,jdbcType=BIGINT} && status != 1",
            "group by node_key"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="node_key", property="nodeKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="actorNum", property="actorNum", jdbcType=JdbcType.INTEGER),
            @Result(column="essay_id", property="essayId", jdbcType=JdbcType.BIGINT),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="act_time", property="actTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="actor_id",property="actor",one=@One(
                    select="com.campus.share.dao.UserInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER))
    })
    List<FlowNode> selectGroupByessayId(Long essayId);


    // =========================================

    @Select({
            "select",
            "id, node_key, essay_id, remark, act_time, actor_id",
            "from flow_node",
            "where essay_id = #{param1,jdbcType=BIGINT} && status != 1 && actor_id=#{param2,jdbcType=BIGINT}",
            "order by act_time desc",
            "limit 1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="node_key", property="nodeKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="actor_id", property="actorId", jdbcType=JdbcType.BIGINT),
            @Result(column="essay_id", property="essayId", jdbcType=JdbcType.BIGINT),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="act_time", property="actTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    FlowNode selectLastByessayIdAndUserId(Long essayId,Long userId);


    @Select({
            "select",
            "count(1)",
            "from flow_node",
            "where essay_id = #{essayId,jdbcType=BIGINT} && status != 1 && node_key = 'inProgress'"
    })
    int countInProgressNodeNum(Long essayId);


    @Select({
            "select",
            "fn.id, fn.node_key, fn.essay_id, fn.remark, fn.act_time, fn.actor_id",
            "from flow_node fn, essay e",
            "where fn.essay_id = e.essay_id ",
            "and e.author_id = #{userId,jdbcType=BIGINT} and status != 1"
    })
    List<FlowNode> selectFlowNodesOfPublishedEssay(Long userId);

    @Select({
            "select",
            "count(DISTINCT fn2.essay_id) as essayNum,fn2.node_key ",
            "from flow_node fn2",
            "left join essay e on fn2.essay_id = e.essay_id",
            "where fn2.status!=1  and e.author_id=#{authorId,jdbcType=BIGINT}",
            "AND fn2.node_key = (select node_key from flow_node where status!=1 and essay_id= fn2.essay_id and actor_id = fn2.actor_id order by act_time desc limit 1 )",
            "group by fn2.node_key"
    })
    List<Map> countAuthorEssayWithFlowNode(Long authorId);


    @Select({
            "select fn2.essay_id,actor_id,fn2.node_key from flow_node fn2",
            "left join essay e on fn2.essay_id = e.essay_id",
            "where fn2.status!=1  and e.author_id=#{param1,jdbcType=BIGINT}",
            "AND node_key=#{param2,jdbcType=VARCHAR}",
            "and fn2.node_key = (select node_key from flow_node where status!=1 and essay_id= fn2.essay_id and actor_id = fn2.actor_id order by act_time desc limit 1 )",
            "group by fn2.essay_id",
            "order by e.create_time desc"
    })
    @Results({
            @Result(column="node_key", property="nodeKey", jdbcType=JdbcType.VARCHAR),
            @Result(column = "essay_id", property = "essay", one = @One(
                    select = "com.campus.share.dao.EssayMapper.selectSimpleInfoByPrimaryKey", fetchType = FetchType.EAGER)),
            @Result(column = "{essayId = essay_id,nodeKey = node_key}",
                    property = "essayFlowNodes", many = @Many(
                    select = "com.campus.share.dao.FlowNodeMapper.selectByEssayIdAndNodeKey", fetchType = FetchType.EAGER))
    })
    List<FlowNodeEssayVO> getNodeEssay(Long authorId, String nodeKey);



    @Select({
            "select",
            "fn2.id, fn2.node_key, fn2.actor_id, fn2.essay_id, fn2.remark, fn2.act_time, fn2.status",
            "from flow_node fn2",
            "where fn2.essay_id = #{essayId,jdbcType=BIGINT}",
            "and fn2.node_key = #{nodeKey,jdbcType=VARCHAR}",
            "and fn2.node_key = (select node_key from flow_node where status!=1 and essay_id= fn2.essay_id and actor_id = fn2.actor_id order by act_time desc limit 1 )",
            "order by fn2.act_time desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="node_key", property="nodeKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="actor_id", property="actorId", jdbcType=JdbcType.BIGINT),
            @Result(column="essay_id", property="essayId", jdbcType=JdbcType.BIGINT),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="act_time", property="actTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
            @Result(column="actor_id",property="actor",one=@One(
                    select="com.campus.share.dao.UserInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER))
    })
    List<FlowNode> selectByEssayIdAndNodeKey(Long essayId,String nodeKey);



    @Select({
            "select",
            "count(DISTINCT fn2.essay_id) as essayNum,fn2.node_key ",
            "from flow_node fn2",
            "where fn2.status!=1 and fn2.actor_id=#{actorId,jdbcType=BIGINT}",
            "AND fn2.node_key = (select node_key from flow_node where status!=1 and essay_id= fn2.essay_id and actor_id = fn2.actor_id order by act_time desc limit 1 )",
            "group by fn2.node_key"
    })
    List<Map> countActorEssayWithFlowNode(Long actorId);

    @Select({
            "select fn2.essay_id,fn2.node_key,fn2.act_time",
            "from flow_node fn2,essay e",
            "where fn2.essay_id=e.essay_id and fn2.actor_id != e.author_id ",
            "and fn2.status!=1  and fn2.actor_id=#{param1,jdbcType=BIGINT}",
            "AND node_key=#{param2,jdbcType=VARCHAR}",
            "and fn2.node_key = (select node_key from flow_node where status!=1 and essay_id= fn2.essay_id and actor_id = fn2.actor_id order by act_time desc limit 1 )",
            "group by fn2.essay_id",
            "order by fn2.act_time desc"
    })
    @Results({
            @Result(column="act_time", property="actTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="node_key", property="nodeKey", jdbcType=JdbcType.VARCHAR),
            @Result(column = "essay_id", property = "essay", one = @One(
                    select = "com.campus.share.dao.EssayMapper.selectSimpleInfoByPrimaryKey", fetchType = FetchType.EAGER))
    })
    List<FlowNodeEssayVO> getActorNodeEssay(Long actorId, String nodeKey);

    @Select({
            "select fn2.essay_id,fn2.node_key,fn2.act_time",
            "from flow_node fn2, essay e",
            "where fn2.essay_id=e.essay_id and fn2.actor_id != e.author_id",
            "and fn2.status!=1  and fn2.actor_id=#{actorId,jdbcType=BIGINT}",
            "and fn2.node_key = (select node_key from flow_node where status!=1 and essay_id= fn2.essay_id and actor_id = fn2.actor_id order by act_time desc limit 1 )",
            "order by fn2.act_time desc"
    })
    @Results({
            @Result(column="act_time", property="actTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="node_key", property="nodeKey", jdbcType=JdbcType.VARCHAR),
            @Result(column = "essay_id", property = "essay", one = @One(
                    select = "com.campus.share.dao.EssayMapper.selectSimpleInfoByPrimaryKey", fetchType = FetchType.EAGER))
    })
    List<FlowNodeEssayVO> getActorAllNodeEssay(Long actorId);

}
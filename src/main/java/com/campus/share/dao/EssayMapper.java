package com.campus.share.dao;

import com.campus.share.model.Essay;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
public interface EssayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table essay
     *
     * @mbggenerated
     */
    @Delete({
        "delete from essay",
        "where essay_id = #{essayId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long essayId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table essay
     *
     * @mbggenerated
     */
    @Insert({
        "insert into essay (essay_id, author_id, ",
        "title, summary, ",
        "read_num, essay_type, ",
        "resource_type_id, create_time, ",
        "update_time, content)",
        "values (#{essayId,jdbcType=BIGINT}, #{authorId,jdbcType=BIGINT}, ",
        "#{title,jdbcType=VARCHAR}, #{summary,jdbcType=VARCHAR}, ",
        "#{readNum,jdbcType=INTEGER}, #{essayType,jdbcType=TINYINT}, ",
        "#{resourceTypeId,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Essay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table essay
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "essay_id, author_id, title, summary, read_num, essay_type, resource_type_id, ",
        "create_time, update_time, content",
        "from essay",
        "where essay_id = #{essayId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="essay_id", property="essayId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR),
        @Result(column="read_num", property="readNum", jdbcType=JdbcType.INTEGER),
        @Result(column="essay_type", property="essayType", jdbcType=JdbcType.TINYINT),
        @Result(column="resource_type_id", property="resourceTypeId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Essay selectByPrimaryKey(Long essayId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table essay
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "essay_id, author_id, title, summary, read_num, essay_type, resource_type_id, ",
        "create_time, update_time, content",
        "from essay"
    })
    @Results({
        @Result(column="essay_id", property="essayId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="author_id", property="authorId", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR),
        @Result(column="read_num", property="readNum", jdbcType=JdbcType.INTEGER),
        @Result(column="essay_type", property="essayType", jdbcType=JdbcType.TINYINT),
        @Result(column="resource_type_id", property="resourceTypeId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Essay> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table essay
     *
     * @mbggenerated
     */
    @Update({
        "update essay",
        "set author_id = #{authorId,jdbcType=BIGINT},",
          "title = #{title,jdbcType=VARCHAR},",
          "summary = #{summary,jdbcType=VARCHAR},",
          "read_num = #{readNum,jdbcType=INTEGER},",
          "essay_type = #{essayType,jdbcType=TINYINT},",
          "resource_type_id = #{resourceTypeId,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where essay_id = #{essayId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Essay record);
}
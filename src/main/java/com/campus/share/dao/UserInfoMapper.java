package com.campus.share.dao;

import com.campus.share.model.UserInfo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated
     */
    @Delete({
            "delete from user_info",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated
     */
    @Insert({
            "insert into user_info (user_id, nickname, ",
            "phone, major, point, ",
            "avatar_path)",
            "values (#{userId,jdbcType=BIGINT}, #{nickname,jdbcType=VARCHAR}, ",
            "#{phone,jdbcType=VARCHAR}, #{major,jdbcType=VARCHAR}, #{point,jdbcType=INTEGER}, ",
            "#{avatarPath,jdbcType=VARCHAR})"
    })
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated
     */
    @Select({
            "select",
            "user_id, nickname, phone, major, point, avatar_path",
            "from user_info",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="major", property="major", jdbcType=JdbcType.VARCHAR),
            @Result(column="point", property="point", jdbcType=JdbcType.INTEGER),
            @Result(column="avatar_path", property="avatarPath", jdbcType=JdbcType.VARCHAR)
    })
    UserInfo selectByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated
     */
    @Select({
            "select",
            "user_id, nickname, phone, major, point, avatar_path",
            "from user_info"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="major", property="major", jdbcType=JdbcType.VARCHAR),
            @Result(column="point", property="point", jdbcType=JdbcType.INTEGER),
            @Result(column="avatar_path", property="avatarPath", jdbcType=JdbcType.VARCHAR)
    })
    List<UserInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated
     */
    @Update({
            "update user_info",
            "set nickname = #{nickname,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR},",
            "major = #{major,jdbcType=VARCHAR},",
            "point = #{point,jdbcType=INTEGER},",
            "avatar_path = #{avatarPath,jdbcType=VARCHAR}",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserInfo record);
}
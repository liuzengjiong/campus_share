package com.campus.share.sql;

import com.campus.share.model.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public class UserInfoProvider {

    public String updateUserInfo(@Param("userInfo") UserInfo userInfo) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" update user_info");

        if(!StringUtils.isEmpty(userInfo.getAvatarPath())){
            sqlBuilder.append(" set avatar_path = #{userInfo.avatarPath,jdbcType=VARCHAR}");
        }
        if(!StringUtils.isEmpty(userInfo.getNickname())){
            sqlBuilder.append(" set nickname = #{userInfo.nickname,jdbcType=VARCHAR}");
        }
        if(!StringUtils.isEmpty(userInfo.getMajor())){
            sqlBuilder.append(" set major = #{userInfo.major,jdbcType=VARCHAR}");
        }
        if(!StringUtils.isEmpty(userInfo.getPhone())){
            sqlBuilder.append(" set phone = #{userInfo.phone,jdbcType=VARCHAR}");
        }
        sqlBuilder.append(" where user_id = #{userInfo.userId,jdbcType=BIGINT}");
        return sqlBuilder.toString();
    }

}

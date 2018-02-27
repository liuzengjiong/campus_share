package com.campus.share.sql;

import java.util.Map;

public class EssaySqlProvider {

    public String searchEssay(final Map param){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT essay_id, author_id, title, summary, read_num, essay_type_key, resource_type_key, create_time, update_time, content, essay_status");
        sqlBuilder.append(" FROM essay");
        if(param.get("keyword") == null){
            sqlBuilder.append(" WHERE 1=1");
        }else{
            sqlBuilder.append(" WHERE (title like concat(concat('%',#{keyword}),'%')");
            sqlBuilder.append(" OR ");
            sqlBuilder.append(" content like concat(concat('%',#{keyword}),'%') )");
        }
        if(param.get("essayTypeKey") != null){
            sqlBuilder.append(" AND essay_type_key=#{essayTypeKey}");
        }
        if(param.get("sourceTypeKey") != null){
            sqlBuilder.append(" AND resource_type_key=#{sourceTypeKey}");
        }
        sqlBuilder.append(" ORDER BY create_time DESC");
        System.out.println(sqlBuilder.toString());
       return sqlBuilder.toString();
    }

}

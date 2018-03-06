package com.campus.share.sql;

import java.util.List;
import java.util.Map;

public class EssaySqlProvider {

    public String searchEssay(final Map param){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT e.essay_id, e.author_id, e.title, e.summary, e.read_num, e.essay_type_key, e.resource_type_key, e.create_time, e.update_time, e.content, e.essay_status");
        sqlBuilder.append(" FROM essay e");
        sqlBuilder.append(" LEFT JOIN reward r ON e.essay_id = r.essay_id");
        if(param.get("keyword") == null){
            sqlBuilder.append(" WHERE 1=1");
        }else{
            sqlBuilder.append(" WHERE (e.title like concat(concat('%',#{keyword}),'%')");
            sqlBuilder.append(" OR ");
            sqlBuilder.append(" e.content like concat(concat('%',#{keyword}),'%') )");
        }
        if(param.get("essayTypeKey") != null){
            sqlBuilder.append(" AND e.essay_type_key=#{essayTypeKey}");
        }
        if(param.get("sourceTypeKeys") != null){
            List<String> sourceTypeKeys = (List<String>) param.get("sourceTypeKeys");
            sqlBuilder.append(" AND e.resource_type_key in (");
            for(int i = 0 ; i < sourceTypeKeys.size() ; i++){
                String paramName = "sourceTypeKey" + i;
                if(i!=0){
                    sqlBuilder.append(",");
                }
                sqlBuilder.append("#{"+paramName+"}");
                param.put(paramName,sourceTypeKeys.get(i));
            }
            sqlBuilder.append(" )");
        }
        if(param.get("rewardTypeKeys") != null){
            List<String> rewardTypeKeys = (List<String>) param.get("rewardTypeKeys");
            sqlBuilder.append(" AND r.reward_type_key in (");
            for(int i = 0 ; i < rewardTypeKeys.size() ; i++){
                String paramName = "rewardTypeKey" + i;
                if(i!=0){
                    sqlBuilder.append(",");
                }
                sqlBuilder.append("#{"+paramName+"}");
                param.put(paramName,rewardTypeKeys.get(i));
            }
            sqlBuilder.append(" )");
        }
        sqlBuilder.append(" ORDER BY create_time DESC");
        System.out.println(sqlBuilder.toString());
       return sqlBuilder.toString();
    }

}

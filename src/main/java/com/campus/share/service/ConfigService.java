package com.campus.share.service;

import com.campus.share.model.Config;

import java.util.List;

/**
 * 系统配置
 */
public interface ConfigService {

    List<String> getValuesByType(String type);


    /**
     * 获取配置列表
     * @param type 配置类型
     * @return List<Config> 如果type未找到则返回null
     */
    List<Config> getConfigsByType(String type);

    /**
     * 获取配置的值
     * @param type 配置类型
     * @param key 配置键
     * @return 配置值 如果type或key未找到，则返回null
     */
    String getConfigValue(String type,String key);

    /**
     * 获取配置键
     * @param type 配置类型
     * @param value 配置值
     * @return 配置键 如果type或value未找到，则返回null
     */
    String getConfigKey(String type,String value);

}

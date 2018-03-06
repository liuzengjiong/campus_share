package com.campus.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.constant.AppConfig;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FieldConstant;
import com.campus.share.dao.ConfigMapper;
import com.campus.share.exception.BusinessException;
import com.campus.share.model.Config;
import com.campus.share.service.ConfigService;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ConfigServiceImpl implements ConfigService {

    private final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private AppConfig appConfig;

    private Map<String,List<Config>> configMap;

    private Date invalidTime;


    private void checkConfig(){
        if(configMap == null || invalidTime == null || invalidTime.before(new Date())){
            fetchConfig();
        }
    }

    private void fetchConfig(){
        logger.info("开始获取配置。。。");
        configMap = new HashMap<>();
        List<Config> allConfig = configMapper.selectAll();
        for(Config config : allConfig){
            if(configMap.get(config.getConfigType()) == null){
                List<Config> configList = new ArrayList<>();
                configList.add(config);
                configMap.put(config.getConfigType(),configList);
            }else{
                configMap.get(config.getConfigType()).add(config);
            }
        }
        invalidTime = DateUtils.addSeconds(new Date(),appConfig.getConfigCacheSeconds());
        logger.info("获取配置结束，过期时间"+ invalidTime.toString());
    }

    @Override
    public List<String> getValuesByType(String type) {
        ArrayList<String> result = new ArrayList<>();
        List<Config> configs = this.getConfigsByType(type);

        if(configs != null){
            for(Config config : configs){
                result.add(config.getConfigValue());
            }
        }

        return result;
    }

    @Override
    public List<Config> getConfigsByType(String type) {
        checkConfig();
        return configMap.get(type);
    }

    @Override
    public String getConfigValue(String type, String key) {
        List<Config> configList = getConfigsByType(type);
        if(configList == null){
            return key;
        }
        for(Config config : configList){
            if(config.getConfigKey().equals(key)){
                return config.getConfigValue();
            }
        }
        return key;
    }

    @Override
    public String getConfigKey(String type, String value) {
        List<Config> configList = getConfigsByType(type);
        if(configList == null){
            logger.error("类型{}未找到",type);
            throw new BusinessException(CodeEnum.FAIL_CONFIG_NOT_MATCH);
        }
        for(Config config : configList){
            if(config.getConfigValue().equals(value)){
                return config.getConfigKey();
            }
        }
        logger.error("类型{}中无{}对应键值",type,value);
        throw new BusinessException(CodeEnum.FAIL_CONFIG_NOT_MATCH);
    }

    @Override
    public String getConfigKeyNoStrict(String type, String value) {
        List<Config> configList = getConfigsByType(type);
        if(configList == null){
            logger.error("类型{}未找到",type);
            return value;
        }
        for(Config config : configList){
            if(config.getConfigValue().equals(value)){
                return config.getConfigKey();
            }
        }
        logger.error("类型{}中无{}对应键值",type,value);
        return value;
    }
}

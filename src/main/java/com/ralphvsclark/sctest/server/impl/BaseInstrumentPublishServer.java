package com.ralphvsclark.sctest.server.impl;

import com.ralphvsclark.sctest.server.InstrumentPublishServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public abstract class BaseInstrumentPublishServer implements InstrumentPublishServer {

    protected Map<String, String> configMap = new HashMap<>();

    @Override
    public synchronized void initConfig(Map<String, String> config) {
        configMap.putAll(config);
    }

    public String getConfig(String key) {
        return configMap.get(key);
    }

}

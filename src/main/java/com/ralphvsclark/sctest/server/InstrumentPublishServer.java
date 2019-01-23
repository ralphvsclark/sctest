package com.ralphvsclark.sctest.server;

import java.util.Map;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public interface InstrumentPublishServer {

    void start();

    void initConfig(Map<String, String> config);

}

package com.ralphvsclark.sctest;

import com.ralphvsclark.sctest.server.impl.ConsoleInstrumentPublishServer;
import com.ralphvsclark.sctest.server.impl.SocketInstrumentPublishServer;

import java.util.HashMap;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class MainApp {

    public static void main(String[] args) {
        startSocketServer();
    }

    public static void startSocketServer() {

        SocketInstrumentPublishServer server = new SocketInstrumentPublishServer();
        server.initConfig(new HashMap<>());
        server.start();
    }

    public static void startConsoleServer() {

        ConsoleInstrumentPublishServer server = new ConsoleInstrumentPublishServer();
        server.initConfig(new HashMap<>());
        server.start();
    }

}

package com.ralphvsclark.sctest.server.impl;

import com.ralphvsclark.sctest.command.impl.CommandOperatorFactory;
import com.ralphvsclark.sctest.socket.SocketProcessRunnable;
import com.ralphvsclark.sctest.service.ExecutorEngine;
import com.ralphvsclark.sctest.service.impl.ExecutorEngineImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is just a demo for how to implement the publish server
 * in different ways and the socket publish server is not
 * implemented yet.
 *
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class SocketInstrumentPublishServer extends BaseInstrumentPublishServer {

    private final static String KEY_PORT_NUMBER = "PortNumber";

    private final static int DEFAULT_PORT = 8899;

    private final static Logger logger = Logger.getLogger(SocketInstrumentPublishServer.class.getName());

    private CommandOperatorFactory commandOperatorFactory = new CommandOperatorFactory();

    private ExecutorEngine executorEngine = new ExecutorEngineImpl(commandOperatorFactory);

    private ExecutorService es = Executors.newFixedThreadPool(100);

    @Override
    public synchronized void initConfig(Map<String, String> config) {
        super.initConfig(config);

        if (!configMap.containsKey(KEY_PORT_NUMBER)) {
            configMap.put(KEY_PORT_NUMBER, new Integer(DEFAULT_PORT).toString());
        }
    }

    @Override
    public void start() {

        String portStr = this.getConfig(KEY_PORT_NUMBER);
        int port = 0;
        try {
            port = Integer.parseInt(portStr);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Start server failed: port is not properly configured", ex);
            return;
        }

        ServerSocket server = null;
        try {
            logger.info("Server is starting and listens to " + port);
            server = new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "Start server failed", ex);
            return;
        }

        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                logger.log(Level.INFO, "Server is successfully started");

                // Use a thread to process the request
                Runnable runnable = new SocketProcessRunnable(socket, executorEngine);
                es.submit(runnable);
            } catch (IOException ex) {
                ex.printStackTrace();
                logger.log(Level.SEVERE, "Creating accept socket failed", ex);
                break;
            }
        }

        if (server != null) {
            try {
                server.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Failed to close server", ex);
            }
        }
    }

}

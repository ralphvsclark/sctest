package com.ralphvsclark.sctest.server.impl;

import com.ralphvsclark.sctest.command.impl.CommandOperatorFactory;
import com.ralphvsclark.sctest.service.ExecutorEngine;
import com.ralphvsclark.sctest.service.impl.ExecutorEngineImpl;
import com.ralphvsclark.sctest.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class ConsoleInstrumentPublishServer extends BaseInstrumentPublishServer {

    private final static Logger logger = Logger.getLogger(ConsoleInstrumentPublishServer.class.getName());

    private CommandOperatorFactory commandOperatorFactory = new CommandOperatorFactory();

    private ExecutorEngine executorEngine = new ExecutorEngineImpl(commandOperatorFactory);

    @Override
    public void start() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ( true ) {

            String input = null;
            try {
                input = reader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (StringUtil.isEmpty(input)) {
                continue;
            }

            String msg = executorEngine.exec(input);
            output(msg);
        }
    }

    private void output(String msg) {
        System.out.printf(msg);
        System.out.println();
        System.out.println();
    }

    private void output(String msg, String...args) {
        System.out.printf(msg, args);
        System.out.println();
        System.out.println();
    }
}

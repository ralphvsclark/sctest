package com.ralphvsclark.sctest.service.impl;

import com.ralphvsclark.sctest.command.CommandOperator;
import com.ralphvsclark.sctest.command.impl.CommandOperatorFactory;
import com.ralphvsclark.sctest.exception.CommException;
import com.ralphvsclark.sctest.service.ExecutorEngine;
import com.ralphvsclark.sctest.util.StringUtil;
import javafx.util.Pair;

public class ExecutorEngineImpl implements ExecutorEngine {

    private CommandOperatorFactory commandOperatorFactory;

    public ExecutorEngineImpl(CommandOperatorFactory commandOperatorFactory) {
        this.commandOperatorFactory = commandOperatorFactory;
    }

    @Override
    public String exec(String input) {
        Pair<String, String> cmdNParams = StringUtil.parseInput(input);

        CommandOperator operator = commandOperatorFactory.createCommandOperator(cmdNParams.getKey());
        if (operator == null) {
            return "Err: Unsupported command - " + input;
        }

        String ret = null;
        try {
            ret = operator.exec(cmdNParams.getValue());
        } catch (CommException ex) {
            ret = "Err: " + ex.toString();
        }

        return ret;
    }
}

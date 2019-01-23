package com.ralphvsclark.sctest.command.impl;

import com.ralphvsclark.sctest.command.CommandOperator;
import com.ralphvsclark.sctest.dao.InstrumentDao;
import com.ralphvsclark.sctest.enums.CommandEnum;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class CommandOperatorFactory {

    private final static Logger logger = Logger.getLogger(CommandOperatorFactory.class.getName());

    private final static Map<CommandEnum, CommandOperator> operatorMap = new HashMap<>();

    private InstrumentDao instrumentDao = new InstrumentDao();

    public CommandOperator createCommandOperator(String cmd) {

        cmd = cmd.toUpperCase();

        CommandEnum command = null;
        try {
            command = CommandEnum.valueOf(cmd);
        } catch (Exception ex) {
            logger.warning("Unsupported command: " + cmd);
        }

        return createCommandOperator(command);
    }

    public CommandOperator createCommandOperator(CommandEnum command) {

        if (command == null) {
            return null;
        }

        // Check if there is command operator which is created already
        if (operatorMap.containsKey(command)) {
            return operatorMap.get(command);
        }

        CommandOperator co = null;
        if (command == CommandEnum.QUIT) {

            co = new QuitOperator();

        } else if (command == CommandEnum.HELP) {

            co = new HelpOperator();

        } else if (command == CommandEnum.DISPLAY) {

            DisplayOperator dop = new DisplayOperator();
            dop.setInstrumentDao(instrumentDao);
            co = dop;

        } else if (command == CommandEnum.PUBLISH) {

            PublishOperator pop = new PublishOperator();
            pop.setInstrumentDao(instrumentDao);
            co = pop;
        }

        operatorMap.put(command, co);

        return co;
    }
}

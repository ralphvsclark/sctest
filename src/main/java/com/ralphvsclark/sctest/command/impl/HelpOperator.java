package com.ralphvsclark.sctest.command.impl;

import com.ralphvsclark.sctest.command.CommandOperator;
import com.ralphvsclark.sctest.enums.CommandEnum;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class HelpOperator implements CommandOperator {

    private String helpString = "";

    public HelpOperator() {
        StringBuilder sb = new StringBuilder("Available commands: ");
        for(CommandEnum command: CommandEnum.values()) {
            sb.append(command);
            sb.append(" ");
        }

        helpString = sb.toString();
    }

    @Override
    public String exec(String input) {

        return helpString;
    }
}

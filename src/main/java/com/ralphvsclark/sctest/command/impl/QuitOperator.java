package com.ralphvsclark.sctest.command.impl;

import com.ralphvsclark.sctest.command.CommandOperator;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class QuitOperator implements CommandOperator {

    @Override
    public String exec(String input) {
        System.exit(0);
        return "We are quitting!!!";
    }
}

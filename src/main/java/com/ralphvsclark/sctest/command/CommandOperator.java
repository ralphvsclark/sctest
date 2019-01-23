package com.ralphvsclark.sctest.command;

import com.ralphvsclark.sctest.exception.CommException;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public interface CommandOperator {

    String exec(String input) throws CommException;
}

package com.ralphvsclark.sctest.test.command;

import com.ralphvsclark.sctest.command.CommandOperator;
import com.ralphvsclark.sctest.command.impl.*;
import com.ralphvsclark.sctest.enums.CommandEnum;
import com.ralphvsclark.sctest.util.StringUtil;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestCommandOperatorFactory {

    private CommandOperatorFactory factory = new CommandOperatorFactory();

    @Test
    public void testCreateCommandOperator() {

        {
            CommandOperator co = factory.createCommandOperator(CommandEnum.HELP);
            Assert.assertEquals(co.getClass(), HelpOperator.class);
        }

        {
            CommandOperator co = factory.createCommandOperator(CommandEnum.QUIT);
            Assert.assertEquals(co.getClass(), QuitOperator.class);
        }

        {
            CommandOperator co = factory.createCommandOperator(CommandEnum.DISPLAY);
            Assert.assertEquals(co.getClass(), DisplayOperator.class);
        }

        {
            CommandOperator co = factory.createCommandOperator(CommandEnum.PUBLISH);
            Assert.assertEquals(co.getClass(), PublishOperator.class);
        }


    }
}

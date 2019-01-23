package com.ralphvsclark.sctest.test.command;

import com.ralphvsclark.sctest.command.CommandOperator;
import com.ralphvsclark.sctest.command.impl.CommandOperatorFactory;
import com.ralphvsclark.sctest.entity.Instrument;
import com.ralphvsclark.sctest.enums.CommandEnum;
import com.ralphvsclark.sctest.enums.InstrumentType;
import com.ralphvsclark.sctest.exception.CommException;
import com.ralphvsclark.sctest.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class TestPublishCommand {

    private CommandOperatorFactory factory = new CommandOperatorFactory();

    @Test
    public void testParseLMEInstrument() throws CommException {

        String data = "PB_03_2018|15-03-2018|17-03-2018|LME_PB|Lead 13 March 2018";

        Instrument ins = Instrument.parse(InstrumentType.LME, data);
        Assert.assertEquals(ins.getCode(), "PB_03_2018");

        Calendar c = Calendar.getInstance();
        c.set(2018, 3 - 1, 15, 0, 0, 0);
        Assert.assertEquals(DateUtil.toStandardDate( ins.getLastTradingDate() ), DateUtil.toStandardDate( c.getTime() ) );

        c.set(2018, 3 - 1, 17, 0, 0, 0);
        Assert.assertEquals(DateUtil.toStandardDate( ins.getDeliveryDate() ), DateUtil.toStandardDate( c.getTime() ) );

        Assert.assertEquals(ins.getMarket(), "PB");

        Assert.assertEquals(ins.getLabel(), "Lead 13 March 2018");
    }

    @Test
    public void testParsePrimeInstrument() throws CommException {

        String data = "PRIME_03_2018|15-03-2018|17-03-2018|LME_PB|PB_03_2018|Lead 13 March 2018";

        Instrument ins = Instrument.parse(InstrumentType.PRIME, data);
        Assert.assertEquals(ins.getCode(), "PRIME_03_2018");

        Calendar c = Calendar.getInstance();
        c.set(2018, 3 - 1, 15, 0, 0, 0);
        Assert.assertEquals(DateUtil.toStandardDate( ins.getLastTradingDate() ), DateUtil.toStandardDate( c.getTime() ) );

        c.set(2018, 3 - 1, 17, 0, 0, 0);
        Assert.assertEquals(DateUtil.toStandardDate( ins.getDeliveryDate() ), DateUtil.toStandardDate( c.getTime() ) );

        Assert.assertEquals(ins.getMarket(), "PB");

        Assert.assertEquals(ins.getExchangeCode(), "PB_03_2018");

        Assert.assertEquals(ins.getLabel(), "Lead 13 March 2018");
    }

    @Test
    public void testPublishLMEInstrument() throws CommException {

        String input = "lme PB_03_2018|15-03-2018|17-03-2018|LME_PB|Lead 13 March 2018";

        CommandOperator co = factory.createCommandOperator(CommandEnum.PUBLISH);
        String ret = co.exec(input);
        Assert.assertTrue(ret.length() > 0);

        co = factory.createCommandOperator(CommandEnum.DISPLAY);
        String msg = co.exec("");

        System.out.println(msg);
    }

    @Test
    public void testPublishPrimeInstrument() throws CommException {

        this.testPublishLMEInstrument();

        String input = "prime PRIME_03_2018|15-03-2018|17-03-2018|LME_PB|PB_03_2018|Lead 13 March 2018";

        CommandOperator co = factory.createCommandOperator(CommandEnum.PUBLISH);
        String ret = co.exec(input);
        Assert.assertTrue(ret.length() > 0);

        co = factory.createCommandOperator(CommandEnum.DISPLAY);
        co.exec("");
        String msg = co.exec("");

        System.out.println(msg);
    }
}

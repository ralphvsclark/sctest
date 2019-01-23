package com.ralphvsclark.sctest.command.impl;

import com.ralphvsclark.sctest.command.CommandOperator;
import com.ralphvsclark.sctest.dao.InstrumentDao;
import com.ralphvsclark.sctest.entity.Instrument;
import com.ralphvsclark.sctest.entity.InstrumentUtil;

import java.util.List;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class DisplayOperator implements CommandOperator {

    private InstrumentDao instrumentDao;

    public DisplayOperator() {

    }

    public DisplayOperator(InstrumentDao instrumentDao) {
        this.instrumentDao = instrumentDao;
    }

    public void setInstrumentDao(InstrumentDao instrumentDao) {
        this.instrumentDao = instrumentDao;
    }

    @Override
    public String exec(String input) {

        return InstrumentUtil.export( instrumentDao.getTopInstruments() );
    }
}

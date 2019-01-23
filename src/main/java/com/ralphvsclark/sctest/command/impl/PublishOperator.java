package com.ralphvsclark.sctest.command.impl;

import com.ralphvsclark.sctest.command.CommandOperator;
import com.ralphvsclark.sctest.dao.InstrumentDao;
import com.ralphvsclark.sctest.entity.Instrument;
import com.ralphvsclark.sctest.entity.InstrumentUtil;
import com.ralphvsclark.sctest.enums.InstrumentType;
import com.ralphvsclark.sctest.exception.CommException;
import com.ralphvsclark.sctest.exception.ExConst;
import com.ralphvsclark.sctest.util.StringUtil;
import javafx.util.Pair;

import java.util.List;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class PublishOperator implements CommandOperator {

    private InstrumentDao instrumentDao;

    public PublishOperator() {

    }

    public PublishOperator(InstrumentDao instrumentDao) {
        this.instrumentDao = instrumentDao;
    }

    public void setInstrumentDao(InstrumentDao instrumentDao) {
        this.instrumentDao = instrumentDao;
    }

    @Override
    public String exec(String input) throws CommException {

        // Parse input
        Pair<String, String> typeNData = StringUtil.parseInput(input);

        // Check if this is a supported instrument type
        if (typeNData == null) {
            throw new CommException(ExConst.CD_PUBLISH_INS_TYPE_NOT_SPECIFIED, "Instrument type is not specified");
        }

        String type = typeNData.getKey();
        type = type.toUpperCase();
        InstrumentType it = null;
        try {
            it = InstrumentType.valueOf(type);
        } catch (Exception ex) {
            String msg = String.format("Instrument type %s is not supported", type);
            throw new CommException(ExConst.CD_PUBLISH_INS_TYPE_NOT_SUPPORTED, "Instrument type is not supported");
        }

        // Parse instrument
        String data = typeNData.getValue();
        Instrument ins = Instrument.parse(it, data);

        // If this is a prime ins, we need to merge it with exiting ins
        if (it == InstrumentType.PRIME) {
            this.mergeInstrument(ins);
        }

        instrumentDao.addInstrument(ins);

        return InstrumentUtil.export(ins);
    }

    private void mergeInstrument(Instrument ins) {
        List<Instrument> instrumentList = instrumentDao.getTopInstruments();
        Instrument targetIns = null;
        for (Instrument instrument: instrumentList) {
            if (instrument.getCode().equals(ins.getExchangeCode())) {
                targetIns = instrument;
            }
        }

        if (targetIns != null) {
            ins.setTradable(false);
            ins.setLastTradingDate(targetIns.getLastTradingDate());
            ins.setDeliveryDate(targetIns.getDeliveryDate());
        }
    }
}

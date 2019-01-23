package com.ralphvsclark.sctest.dao;

import com.ralphvsclark.sctest.entity.Instrument;
import com.ralphvsclark.sctest.util.DateUtil;
import com.ralphvsclark.sctest.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class InstrumentDao {

    private static Logger logger = Logger.getLogger(InstrumentDao.class.getName());

    private static ConcurrentHashMap<String, Instrument> insMap = new ConcurrentHashMap<String, Instrument>();

    private static Deque<Instrument> insList = new LinkedBlockingDeque<>();

    public InstrumentDao() {

    }

    public Instrument getInstrument(String code) {

        Instrument instrument = new Instrument();
        instrument.setCode(code);
        return instrument;
    }

    public List<Instrument> getTopInstruments() {

        List<Instrument> lst = new ArrayList<>();
        lst.addAll(insList);

        return lst;

    }

    public boolean addInstrument(Instrument instrument) {
        if (instrument == null) {
            return false;
        }

        String code = instrument.getCode();
        if (StringUtil.isEmpty(code)) {
            logger.warning("Can't add instrument, code is empty");
            return false;
        }

        // Some other check.
        insList.add(instrument);

        return true;
    }
}

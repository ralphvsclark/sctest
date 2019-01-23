package com.ralphvsclark.sctest.entity;

import java.util.ArrayList;
import java.util.List;

public class InstrumentUtil {

    public static String export(List<Instrument> insList) {

        StringBuilder sb = new StringBuilder();

        // Add header
        sb.append( String.format(Instrument.OUTPUT_FORMAT, "LAST_TRADING_DATE", "DELIVERY_DATE", "MARKET", "LABEL", "TRADABLE") );

        // Add rows
        for (Instrument instrument: insList) {
            sb.append(instrument.display());
        }

        return sb.toString();

    }

    public static String export(Instrument instrument) {

        List<Instrument> insList = new ArrayList<>();
        insList.add(instrument);

        return export(insList);
    }
}

package com.ralphvsclark.sctest.entity;

import com.ralphvsclark.sctest.enums.InstrumentType;
import com.ralphvsclark.sctest.exception.CommException;
import com.ralphvsclark.sctest.exception.ExConst;
import com.ralphvsclark.sctest.util.DateUtil;
import com.ralphvsclark.sctest.util.StringUtil;

import java.util.Date;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class Instrument {

    protected String code;

    protected Date lastTradingDate;

    protected Date deliveryDate;

    protected String market;

    protected String exchangeCode;

    protected String label;

    protected boolean tradable = true;

    public final static String OUTPUT_FORMAT = "| %1$-18s | %2$-15s | %3$-15s | %4$-20s | %5$-10s |\n";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getLastTradingDate() {
        return lastTradingDate;
    }

    public void setLastTradingDate(Date lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public boolean isTradable() {
        return tradable;
    }

    public void setTradable(boolean tradable) {
        this.tradable = tradable;
    }

    public String display() {
        String res = String.format(OUTPUT_FORMAT
                , DateUtil.toStandardDate(lastTradingDate)
                , DateUtil.toStandardDate(deliveryDate)
                , market
                , label
                , tradable ? "TRUE" : "FALSE");

        return res;
    }

    public static Instrument parse(InstrumentType type, String data) throws CommException {

        Instrument ins = null;
        if (type == InstrumentType.LME) {
            ins = parseLme(data);
        } else if (type == InstrumentType.PRIME) {
            ins = parsePrime(data);
        }

        return ins;
    }


    public static Instrument parseLme(String data) throws CommException {

        int fieldLength = 5;

        if (data == null) {
            throw new CommException(ExConst.CD_PUBLISH_DATA_NOT_PARSABLE, "Instrument data can't be parsed");
        }

        String parts[] = data.split("\\|");
        if (parts.length != fieldLength) {
            throw new CommException(ExConst.CD_PUBLISH_DATA_FORMAT_INVALID, "Instrument data has invalid format");
        }

        String code = parts[0];
        String ERR_TEMPLATE = "Instrument data has invalid field value for %s";
        if (StringUtil.isEmpty(code)) {
            String msg = String.format(ERR_TEMPLATE, "code");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }

        Date lastTradingDate = DateUtil.fromStandardDate(parts[1]);
        if (lastTradingDate == null) {
            String msg = String.format(ERR_TEMPLATE, "last_trading_date");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }


        Date deliveryDate = DateUtil.fromStandardDate(parts[2]);
        if (deliveryDate == null) {
            String msg = String.format(ERR_TEMPLATE, "delivery_date");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }

        String market = parts[3];
        if (StringUtil.isEmpty(market)) {
            String msg = String.format(ERR_TEMPLATE, "market");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }

        String marketParts[] = market.split("_");
        if (marketParts.length != 2) {
            String msg = String.format(ERR_TEMPLATE, "market");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }
        market = marketParts[1];

        String label = parts[4];

        Instrument ins = new Instrument();
        ins.setCode(code);
        ins.setLastTradingDate(lastTradingDate);
        ins.setDeliveryDate(deliveryDate);
        ins.setMarket(market);
        ins.setLabel(label);
        ins.setTradable(true);

        return ins;
    }

    public static Instrument parsePrime(String data) throws CommException {

        int fieldLength = 6;

        if (data == null) {
            throw new CommException(ExConst.CD_PUBLISH_DATA_NOT_PARSABLE, "Instrument data can't be parsed");
        }

        String parts[] = data.split("\\|");
        if (parts.length != fieldLength) {
            throw new CommException(ExConst.CD_PUBLISH_DATA_FORMAT_INVALID, "Instrument data has invalid format");
        }

        String code = parts[0];
        String ERR_TEMPLATE = "Instrument data has invalid field value for %s";
        if (StringUtil.isEmpty(code)) {
            String msg = String.format(ERR_TEMPLATE, "code");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }

        Date lastTradingDate = DateUtil.fromStandardDate(parts[1]);
        if (lastTradingDate == null) {
            String msg = String.format(ERR_TEMPLATE, "last_trading_date");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }


        Date deliveryDate = DateUtil.fromStandardDate(parts[2]);
        if (deliveryDate == null) {
            String msg = String.format(ERR_TEMPLATE, "delivery_date");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }

        String market = parts[3];
        if (StringUtil.isEmpty(market)) {
            String msg = String.format(ERR_TEMPLATE, "market");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }

        String marketParts[] = market.split("_");
        if (marketParts.length != 2) {
            String msg = String.format(ERR_TEMPLATE, "market");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }
        market = marketParts[1];

        String exchangeCode = parts[4];
        if (StringUtil.isEmpty(market)) {
            String msg = String.format(ERR_TEMPLATE, "exchange_code");
            throw new CommException(ExConst.CD_PUBLISH_FIELD_VALUE_NOT_VALID, msg);
        }

        String label = parts[5];

        Instrument ins = new Instrument();
        ins.setCode(code);
        ins.setLastTradingDate(lastTradingDate);
        ins.setDeliveryDate(deliveryDate);
        ins.setMarket(market);
        ins.setLabel(label);
        ins.setTradable(true);
        ins.setExchangeCode(exchangeCode);

        return ins;
    }
}

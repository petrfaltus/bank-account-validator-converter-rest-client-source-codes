package cz.petrfaltus.bank_account_val_conv_restclient;

import java.io.IOException;
import java.io.StringWriter;

import java.util.Map;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
    private static final String METHOD_NUMBER = "method_number";

    private static final String IBAN = "iban";
    private static final String COUNTRY = "country";

    private static final String ERROR_CODE = "error_code";
    private static final String ERROR_STRING = "error_string";
    private static final String DATA = "data";

    private static final String IBAN_HUMAN = "iban_human";
    private static final String ACCOUNT_NUMBER_IDENTIFICATOR = "account_number_identificator";
    private static final String BANK_CODE = "bank_code";
    private static final String ACCOUNT_NUMBER = "account_number";

    private static final long METHOD_COUNTRIES_NUMBER = 1;
    private static final long METHOD_IBAN_TO_LOCAL_NUMBERING_NUMBER = 2;

    private static String lastErrorString;

    private static String objToString(JSONObject obj) {
        String retString = Const.EMPTY_STRING;

        try {
            StringWriter out = new StringWriter();
            obj.writeJSONString(out);

            retString = out.toString();
        } catch (IOException ioex) {
            retString = null;
        }

        return retString;
    }

    public static String codeQueryCountries() {
        JSONObject obj = new JSONObject();
        obj.put(METHOD_NUMBER, METHOD_COUNTRIES_NUMBER);

        String retString = objToString(obj);

        return retString;
    }

    public static Map<String, String> decodeResultCountries(String resultJson) {
        Map<String, String> retMap = null;
        lastErrorString = null;

        try {
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(resultJson);
            long errorCode = (long) jsonObject.get(ERROR_CODE);

            if (errorCode == 0) {
                retMap = (Map<String, String>) jsonObject.get(DATA);
            } else {
                lastErrorString = (String) jsonObject.get(ERROR_STRING);
            }
        } catch (ParseException pe) {
            retMap = null;
        } catch (NullPointerException npe) {
            retMap = null;
        }

        return retMap;
    }

    public static String codeQueryIbanToLocalNumbering(String iban, String country) {
        JSONObject obj = new JSONObject();
        obj.put(METHOD_NUMBER, METHOD_IBAN_TO_LOCAL_NUMBERING_NUMBER);
        obj.put(IBAN, iban);
        obj.put(COUNTRY, country);

        String retString = objToString(obj);

        return retString;
    }

    public static DataLocalNumbering decodeResultIbanToLocalNumbering(String resultJson) {
        DataLocalNumbering retData = null;
        lastErrorString = null;

        try {
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(resultJson);
            long errorCode = (long) jsonObject.get(ERROR_CODE);

            if (errorCode == 0) {
                JSONObject data = (JSONObject) jsonObject.get(DATA);

                retData = new DataLocalNumbering();
                retData.iban_human = (String) data.get(IBAN_HUMAN);
                retData.account_number_identificator = (String) data.get(ACCOUNT_NUMBER_IDENTIFICATOR);
                retData.bank_code = (String) data.get(BANK_CODE);
                retData.account_number = (String) data.get(ACCOUNT_NUMBER);
            } else {
                lastErrorString = (String) jsonObject.get(ERROR_STRING);
            }
        } catch (ParseException pe) {
            retData = null;
        } catch (NullPointerException npe) {
            retData = null;
        }

        return retData;
    }

    public static String getLastErrorString() {
        return lastErrorString;
    }
}

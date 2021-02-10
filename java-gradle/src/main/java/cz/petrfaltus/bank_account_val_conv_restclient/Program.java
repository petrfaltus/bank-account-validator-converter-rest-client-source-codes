package cz.petrfaltus.bank_account_val_conv_restclient;

import java.util.Map;
import java.util.Set;

import static java.lang.System.out;

public class Program {
    public static final String MESSAGE_ERROR_CODING_JSON = "error coding the request JSON";
    public static final String MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service";
    public static final String MESSAGE_ERROR_DECODING_JSON = "error decoding the reply JSON";
    public static final String MESSAGE_RECEIVED_ERROR = "received error";

    public static void main(String[] args) {
        // all countries query
        out.println("All supported countries:");

        String requestJsonCountries = Json.codeQueryCountries();
        if (requestJsonCountries == null) {
            out.println(" - " + MESSAGE_ERROR_CODING_JSON);
            return;
        }
        String replyJsonCountries = Web.request(requestJsonCountries);
        if (replyJsonCountries == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        Map<String, String> countries = Json.decodeResultCountries(replyJsonCountries);
        if (countries == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString);
            } else {
                out.println(" - " + MESSAGE_ERROR_DECODING_JSON);
            }

            return;
        }

        Set<Map.Entry<String, String>> countriesEntrySet = countries.entrySet();

        for (Map.Entry<String, String> oneCountryEntry: countriesEntrySet) {
            out.println(" - " + oneCountryEntry.getKey() + " ... " + oneCountryEntry.getValue());
        }

        out.println();

        // IBAN validation and to local numbering query conversion
        out.println("IBAN validation and to local numbering conversion:");

        String iban = "CZ6508000000192000145399";
        String country = "cz"; // returned in the all supported countries request

        String requestJsonIbanToLocalNumbering = Json.codeQueryIbanToLocalNumbering(iban, country);
        if (requestJsonIbanToLocalNumbering == null) {
            out.println(" - " + MESSAGE_ERROR_CODING_JSON);
            return;
        }
        String replyJsonIbanToLocalNumbering = Web.request(requestJsonIbanToLocalNumbering);
        if (replyJsonIbanToLocalNumbering == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        DataLocalNumbering dataLocalNumbering = Json.decodeResultIbanToLocalNumbering(replyJsonIbanToLocalNumbering);
        if (dataLocalNumbering == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString);
            } else {
                out.println(" - " + MESSAGE_ERROR_DECODING_JSON);
            }

            return;
        }

        out.println(" - IBAN human: " + dataLocalNumbering.iban_human);
        out.println(" - account number identificator: " + dataLocalNumbering.account_number_identificator);
        out.println(" - bank code: " + dataLocalNumbering.bank_code);
        out.println(" - account number: " + dataLocalNumbering.account_number);

        out.println();

        // Local account number identificator validation
        out.println("Local account number identificator validation:");

        String account_number_identificator = "19-2000145399";
        country = "cz"; // returned in the all supported countries request

        String requestJsonLocalNumberIdentificatorValid = Json.codeQueryLocalNumberIdentificatorValid(account_number_identificator, country);
        if (requestJsonLocalNumberIdentificatorValid == null) {
            out.println(" - " + MESSAGE_ERROR_CODING_JSON);
            return;
        }
        String replyJsonLocalNumberIdentificatorValid = Web.request(requestJsonLocalNumberIdentificatorValid);
        if (replyJsonLocalNumberIdentificatorValid == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        boolean localNumberIdentificatorValid = Json.decodeResultLocalNumberIdentificatorValid(replyJsonLocalNumberIdentificatorValid);
        if (localNumberIdentificatorValid == false) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString);
            } else {
                out.println(" - " + MESSAGE_ERROR_DECODING_JSON);
            }

            return;
        }

        out.println(" - VALID!");
    }

}

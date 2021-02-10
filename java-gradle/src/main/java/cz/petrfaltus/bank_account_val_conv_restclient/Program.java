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
    }

}

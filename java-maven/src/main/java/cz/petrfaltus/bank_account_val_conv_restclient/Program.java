package cz.petrfaltus.bank_account_val_conv_restclient;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.System.out;

public class Program {
    public static final String MESSAGE_ERROR_CODING_JSON = "error coding the request JSON";
    public static final String MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service";
    public static final String MESSAGE_ERROR_DECODING_JSON = "error decoding the reply JSON";
    public static final String MESSAGE_RECEIVED_ERROR = "received error";

    public static void main(String[] args) {
        // all supported countries query
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
            out.println(" - " + oneCountryEntry.getKey() + " (" + oneCountryEntry.getValue() + ")");
        }

        out.println();

        // IBAN validation and to local numbering conversion query
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

        // local account number identificator validation query
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

        out.println();

        // bank code validation and query
        out.println("Bank code validation and query:");

        String bank_code = "0800";
        country = "cz"; // returned in the all supported countries request

        String requestJsonBankCodeValid = Json.codeQueryBankCodeValid(bank_code, country);
        if (requestJsonBankCodeValid == null) {
            out.println(" - " + MESSAGE_ERROR_CODING_JSON);
            return;
        }
        String replyJsonBankCodeValid = Web.request(requestJsonBankCodeValid);
        if (replyJsonBankCodeValid == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        DataBank dataBank = Json.decodeResultBankCodeValid(replyJsonBankCodeValid);
        if (dataBank == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString);
            } else {
                out.println(" - " + MESSAGE_ERROR_DECODING_JSON);
            }

            return;
        }

        out.println(" - bank name: " + dataBank.bank_name);
        out.println(" - bank swift: " + dataBank.bank_swift);

        out.println();

        // local numbering to IBAN conversion query
        out.println("Local numbering to IBAN conversion:");

        String account_number = "19-2000145399/0800"; // must be already valid (validated before)
        country = "cz"; // returned in the all supported countries request

        String requestJsonLocalNumberingToIban = Json.codeQueryLocalNumberingToIban(account_number, country);
        if (requestJsonLocalNumberingToIban == null) {
            out.println(" - " + MESSAGE_ERROR_CODING_JSON);
            return;
        }
        String replyJsonLocalNumberingToIban = Web.request(requestJsonLocalNumberingToIban);
        if (replyJsonLocalNumberingToIban == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        DataIban dataIban = Json.decodeResultLocalNumberingToIban(replyJsonLocalNumberingToIban);
        if (dataIban == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString);
            } else {
                out.println(" - " + MESSAGE_ERROR_DECODING_JSON);
            }

            return;
        }

        out.println(" - IBAN: " + dataIban.iban);
        out.println(" - IBAN human: " + dataIban.iban_human);

        out.println();

        // one bank query
        out.println("One bank query:");

        String query = "česká";
        country = "cz"; // returned in the all supported countries request

        String requestJsonBankQuery = Json.codeQueryBankQuery(query, country);
        if (requestJsonBankQuery == null) {
            out.println(" - " + MESSAGE_ERROR_CODING_JSON);
            return;
        }
        String replyJsonBankQuery = Web.request(requestJsonBankQuery);
        if (replyJsonBankQuery == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        List<OneBank> replyBanks = Json.decodeResultBankQuery(replyJsonBankQuery);
        if (replyBanks == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString);
            } else {
                out.println(" - " + MESSAGE_ERROR_DECODING_JSON);
            }

            return;
        }

        for (OneBank replyBank: replyBanks) {
            out.println(" - bank code: " + replyBank.bank_code);
            out.println(" - bank name: " + replyBank.bank_name);
            out.println(" - bank SWIFT: " + replyBank.bank_swift);

            out.println();
        }
    }

}

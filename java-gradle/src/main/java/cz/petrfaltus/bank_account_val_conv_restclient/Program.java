package cz.petrfaltus.bank_account_val_conv_restclient;

import static java.lang.System.out;

public class Program {
    public static final String MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service";

    public static void main(String[] args) {
        String requestJsonCountries = "{ \"method_number\":1 }";
        String replyJsonCountries = Web.request(requestJsonCountries);
        if (replyJsonCountries == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        out.println(replyJsonCountries);
    }

}

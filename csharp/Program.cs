using System;
using System.Collections.Generic;

using Newtonsoft.Json;

namespace BankAccountValConvRestClient
{
    public class Program
    {
        private static readonly string MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service";
        private static readonly string MESSAGE_RECEIVED_ERROR = "received error";

        public static void Main(string[] args)
        {
            // all supported countries query
            Console.WriteLine("All supported countries:");

            RestRequest1 restRequest1 = new RestRequest1();

            string restRequestJsonCountries = JsonConvert.SerializeObject(restRequest1);

            string restReplyJsonCountries;
            try
            {
                restReplyJsonCountries = Web.Request(restRequestJsonCountries);
            }
            catch (Exception)
            {
                Console.WriteLine(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
                return;
            }

            RestReply1 restReply1 = JsonConvert.DeserializeObject<RestReply1>(restReplyJsonCountries);

            if (restReply1.error_code != 0)
            {
                Console.WriteLine(" - " + MESSAGE_RECEIVED_ERROR + ": " + restReply1.error_string);
                return;
            }

            foreach(KeyValuePair<string, string> country in restReply1.data)
            {
                Console.WriteLine(" - " + country.Key + " ... " + country.Value);
            }

            Console.WriteLine();

            // IBAN validation and to local numbering conversion query
            Console.WriteLine("IBAN validation and to local numbering conversion:");

            RestRequest2 restRequest2 = new RestRequest2();
            restRequest2.iban = "CZ6508000000192000145399";
            restRequest2.country = "cz"; // returned in the all supported countries request

            string restRequestJsonIbanToLocalNumbering = JsonConvert.SerializeObject(restRequest2);

            string restReplyJsonIbanToLocalNumbering;
            try
            {
                restReplyJsonIbanToLocalNumbering = Web.Request(restRequestJsonIbanToLocalNumbering);
            }
            catch (Exception)
            {
                Console.WriteLine(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
                return;
            }

            RestReply2 restReply2 = JsonConvert.DeserializeObject<RestReply2>(restReplyJsonIbanToLocalNumbering);

            if (restReply2.error_code != 0)
            {
                Console.WriteLine(" - " + MESSAGE_RECEIVED_ERROR + ": " + restReply2.error_string);
                return;
            }

            DataLocalNumbering dataLocalNumbering = restReply2.data;
            Console.WriteLine(" - IBAN human: " + dataLocalNumbering.iban_human);
            Console.WriteLine(" - account number identificator: " + dataLocalNumbering.account_number_identificator);
            Console.WriteLine(" - bank code: " + dataLocalNumbering.bank_code);
            Console.WriteLine(" - account number: " + dataLocalNumbering.account_number);

            Console.WriteLine();

            // local account number identificator validation query
            Console.WriteLine("Local account number identificator validation:");

            RestRequest3 restRequest3 = new RestRequest3();
            restRequest3.account_number_identificator = "19-2000145399";
            restRequest3.country = "cz"; // returned in the all supported countries request

            string restRequestJsonLocalNumberIdentificatorValid = JsonConvert.SerializeObject(restRequest3);

            string restReplyJsonLocalNumberIdentificatorValid;
            try
            {
                restReplyJsonLocalNumberIdentificatorValid = Web.Request(restRequestJsonLocalNumberIdentificatorValid);
            }
            catch (Exception)
            {
                Console.WriteLine(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
                return;
            }

            RestReply3 restReply3 = JsonConvert.DeserializeObject<RestReply3>(restReplyJsonLocalNumberIdentificatorValid);

            if (restReply3.error_code != 0)
            {
                Console.WriteLine(" - " + MESSAGE_RECEIVED_ERROR + ": " + restReply3.error_string);
                return;
            }

            Console.WriteLine(" - VALID!");

            Console.WriteLine();

            // bank code validation and query
            Console.WriteLine("Bank code validation and query:");

            RestRequest4 restRequest4 = new RestRequest4();
            restRequest4.bank_code = "0800";
            restRequest4.country = "cz"; // returned in the all supported countries request

            string restRequestJsonBankCodeValid = JsonConvert.SerializeObject(restRequest4);

            string restReplyJsonBankCodeValid;
            try
            {
                restReplyJsonBankCodeValid = Web.Request(restRequestJsonBankCodeValid);
            }
            catch (Exception)
            {
                Console.WriteLine(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
                return;
            }

            RestReply4 restReply4 = JsonConvert.DeserializeObject<RestReply4>(restReplyJsonBankCodeValid);

            if (restReply4.error_code != 0)
            {
                Console.WriteLine(" - " + MESSAGE_RECEIVED_ERROR + ": " + restReply4.error_string);
                return;
            }

            DataBank dataBank = restReply4.data;
            Console.WriteLine(" - bank name: " + dataBank.bank_name);
            Console.WriteLine(" - bank swift: " + dataBank.bank_swift);
        }
    }
}

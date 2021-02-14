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
                Console.WriteLine(" - " + country.Key + " (" + country.Value + ")");
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

            Console.WriteLine();

            // local numbering to IBAN conversion query
            Console.WriteLine("Local numbering to IBAN conversion:");

            RestRequest5 restRequest5 = new RestRequest5();
            restRequest5.account_number = "19-2000145399/0800"; // must be already valid (validated before)
            restRequest5.country = "cz"; // returned in the all supported countries request

            string restRequestJsonLocalNumberingToIban = JsonConvert.SerializeObject(restRequest5);

            string restReplyJsonLocalNumberingToIban;
            try
            {
                restReplyJsonLocalNumberingToIban = Web.Request(restRequestJsonLocalNumberingToIban);
            }
            catch (Exception)
            {
                Console.WriteLine(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
                return;
            }

            RestReply5 restReply5 = JsonConvert.DeserializeObject<RestReply5>(restReplyJsonLocalNumberingToIban);

            if (restReply5.error_code != 0)
            {
                Console.WriteLine(" - " + MESSAGE_RECEIVED_ERROR + ": " + restReply5.error_string);
                return;
            }

            DataIban dataIban = restReply5.data;
            Console.WriteLine(" - IBAN: " + dataIban.iban);
            Console.WriteLine(" - IBAN human: " + dataIban.iban_human);

            Console.WriteLine();

            // one bank query
            Console.WriteLine("One bank query:");

            RestRequest6 restRequest6 = new RestRequest6();
            restRequest6.query = "česká";
            restRequest6.country = "cz"; // returned in the all supported countries request

            string restRequestJsonBankquery = JsonConvert.SerializeObject(restRequest6);

            string restReplyJsonBankquery;
            try
            {
                restReplyJsonBankquery = Web.Request(restRequestJsonBankquery);
            }
            catch (Exception)
            {
                Console.WriteLine(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
                return;
            }

            RestReply6 restReply6 = JsonConvert.DeserializeObject<RestReply6>(restReplyJsonBankquery);

            if (restReply6.error_code != 0)
            {
                Console.WriteLine(" - " + MESSAGE_RECEIVED_ERROR + ": " + restReply6.error_string);
                return;
            }

            foreach(DataOneBank replyBank in restReply6.data)
            {
                Console.WriteLine(" - bank code: " + replyBank.bank_code);
                Console.WriteLine(" - bank name: " + replyBank.bank_name);
                Console.WriteLine(" - bank SWIFT: " + replyBank.bank_swift);

                Console.WriteLine();
            }
        }
    }
}

using System;

namespace BankAccountValConvRestClient
{
    public class Program
    {
        private static readonly string MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service";

        public static void Main(string[] args)
        {
            string restRequestJsonCountries = "{ \"method_number\":1 }";

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

            Console.WriteLine(restReplyJsonCountries);
        }
    }
}

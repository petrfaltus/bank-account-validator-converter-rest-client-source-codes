using System.Collections.Generic;

namespace BankAccountValConvRestClient
{
    public class RestReply5
    {
        public int error_code { get; set; }
        public string error_string { get; set; }

        public DataIban data { get; set; }

        public RestReply5()
        {
            error_code = -999;
        }
    }
}

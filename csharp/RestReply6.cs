using System.Collections.Generic;

namespace BankAccountValConvRestClient
{
    public class RestReply6
    {
        public int error_code { get; set; }
        public string error_string { get; set; }

        public DataOneBank[] data { get; set; }

        public RestReply6()
        {
            error_code = -999;
        }
    }
}

using System.Collections.Generic;

namespace BankAccountValConvRestClient
{
    public class RestReply1
    {
        public int error_code { get; set; }
        public string error_string { get; set; }

        public Dictionary<string, string> data { get; set; }

        public RestReply1()
        {
            error_code = -999;
        }
    }
}

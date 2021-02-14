using System.Collections.Generic;

namespace BankAccountValConvRestClient
{
    public class RestReply3
    {
        public int error_code { get; set; }
        public string error_string { get; set; }

        public RestReply3()
        {
            error_code = -999;
        }
    }
}

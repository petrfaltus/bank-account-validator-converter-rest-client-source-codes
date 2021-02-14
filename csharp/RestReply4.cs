using System.Collections.Generic;

namespace BankAccountValConvRestClient
{
    public class RestReply4
    {
        public int error_code { get; set; }
        public string error_string { get; set; }

        public DataBank data { get; set; }

        public RestReply4()
        {
            error_code = -999;
        }
    }
}

using System.Collections.Generic;

namespace BankAccountValConvRestClient
{
    public class RestReply2
    {
        public int error_code { get; set; }
        public string error_string { get; set; }

        public DataLocalNumbering data { get; set; }

        public RestReply2()
        {
            error_code = -999;
        }
    }
}

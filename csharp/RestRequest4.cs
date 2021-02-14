
namespace BankAccountValConvRestClient
{
    public class RestRequest4
    {
        public int method_number { get; set; }

        public string bank_code { get; set; }
        public string country { get; set; }

        public RestRequest4()
        {
            method_number = 4;
        }
    }
}

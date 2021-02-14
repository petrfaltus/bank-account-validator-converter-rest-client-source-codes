
namespace BankAccountValConvRestClient
{
    public class RestRequest5
    {
        public int method_number { get; set; }

        public string account_number { get; set; }
        public string country { get; set; }

        public RestRequest5()
        {
            method_number = 5;
        }
    }
}

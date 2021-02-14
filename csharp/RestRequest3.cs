
namespace BankAccountValConvRestClient
{
    public class RestRequest3
    {
        public int method_number { get; set; }

        public string account_number_identificator { get; set; }
        public string country { get; set; }

        public RestRequest3()
        {
            method_number = 3;
        }
    }
}

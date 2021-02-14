
namespace BankAccountValConvRestClient
{
    public class RestRequest2
    {
        public int method_number { get; set; }

        public string iban { get; set; }
        public string country { get; set; }

        public RestRequest2()
        {
            method_number = 2;
        }
    }
}

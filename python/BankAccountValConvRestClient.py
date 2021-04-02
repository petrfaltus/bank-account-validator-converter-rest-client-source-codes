from classes.Web import Web
from classes.Json import Json

MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service"
MESSAGE_ERROR_DECODING_JSON = "error decoding the reply JSON"
MESSAGE_RECEIVED_ERROR = "received error"

# all supported countries query
print("All supported countries:")

requestJsonCountries = Json.codeQueryCountries()

try:
    replyJsonCountries = Web.request(requestJsonCountries)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

countries = Json.decodeResult(replyJsonCountries)
if countries == None:
    errorString = Json.getLastErrorString()

    if errorString != None:
        print(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString)
    else:
        print(" - " + MESSAGE_ERROR_DECODING_JSON)

    exit()

for countryCode in countries:
    print(" - " + countryCode + " (" + countries[countryCode] + "}")

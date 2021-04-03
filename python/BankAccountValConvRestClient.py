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

print()

# IBAN validation and to local numbering conversion query
print("IBAN validation and to local numbering conversion:")

iban = "CZ6508000000192000145399"
country = "cz" # returned in the all supported countries request

requestJsonIbanToLocalNumbering = Json.codeQueryIbanToLocalNumbering(iban, country)

try:
    replyJsonIbanToLocalNumbering = Web.request(requestJsonIbanToLocalNumbering)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

replyLocalNumbering = Json.decodeResult(replyJsonIbanToLocalNumbering)
if replyLocalNumbering == None:
    errorString = Json.getLastErrorString()

    if errorString != None:
        print(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString)
    else:
        print(" - " + MESSAGE_ERROR_DECODING_JSON)

    exit()

print(" - IBAN human: " + replyLocalNumbering[Json.IBAN_HUMAN])
print(" - account number identificator: " + replyLocalNumbering[Json.ACCOUNT_NUMBER_IDENTIFICATOR])
print(" - bank code: " + replyLocalNumbering[Json.BANK_CODE])
print(" - account number: " + replyLocalNumbering[Json.ACCOUNT_NUMBER])

print()

# local account number identificator validation query
print("Local account number identificator validation:")

account_number_identificator = "19-2000145399"
country = "cz" # returned in the all supported countries request

requestJsonLocalNumberIdentificatorValid = Json.codeQueryLocalNumberIdentificatorValid(account_number_identificator, country)

try:
    replyJsonLocalNumberIdentificatorValid = Web.request(requestJsonLocalNumberIdentificatorValid)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

replyValid = Json.decodeResultLocalNumberIdentificatorValid(replyJsonLocalNumberIdentificatorValid)
if replyValid == False:
    errorString = Json.getLastErrorString()

    if errorString != None:
        print(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString)
    else:
        print(" - " + MESSAGE_ERROR_DECODING_JSON)

    exit()

print(" - VALID!")

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

print()

# bank code validation and query
print("Bank code validation and query:")

bank_code = "0800"
country = "cz" # returned in the all supported countries request

requestJsonBankCodeValid = Json.codeQueryBankCodeValid(bank_code, country)

try:
    replyJsonBankCodeValid = Web.request(requestJsonBankCodeValid)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

replyBank = Json.decodeResult(replyJsonBankCodeValid)
if replyBank == None:
    errorString = Json.getLastErrorString()

    if errorString != None:
        print(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString)
    else:
        print(" - " + MESSAGE_ERROR_DECODING_JSON)

    exit()

print(" - bank name: " + replyBank[Json.BANK_NAME])
print(" - bank swift: " + replyBank[Json.BANK_SWIFT])

print()

# local numbering to IBAN conversion query
print("Local numbering to IBAN conversion:")

account_number = "19-2000145399/0800" # must be already valid (validated before)
country = "cz" # returned in the all supported countries request

requestJsonLocalNumberingToIban = Json.codeQueryLocalNumberingToIban(account_number, country)

try:
    replyJsonLocalNumberingToIban = Web.request(requestJsonLocalNumberingToIban)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

replyIban = Json.decodeResult(replyJsonLocalNumberingToIban)
if replyIban == None:
    errorString = Json.getLastErrorString()

    if errorString != None:
        print(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString)
    else:
        print(" - " + MESSAGE_ERROR_DECODING_JSON)

    exit()

print(" - IBAN: " + replyIban[Json.IBAN])
print(" - IBAN human: " + replyIban[Json.IBAN_HUMAN])

print()

# one bank query
print("One bank query:")

query = "česká"
country = "cz" # returned in the all supported countries request

requestJsonBankQuery = Json.codeQueryBankQuery(query, country)

try:
    replyJsonBankQuery = Web.request(requestJsonBankQuery)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

replyBanks = Json.decodeResult(replyJsonBankQuery)
if replyBanks == None:
    errorString = Json.getLastErrorString()

    if errorString != None:
        print(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString)
    else:
        print(" - " + MESSAGE_ERROR_DECODING_JSON)

    exit()

totalBanks = 0
for replyOneBank in replyBanks:
    if totalBanks > 0:
        print()

    print(" - bank code: " + replyOneBank[Json.BANK_CODE])
    print(" - bank name: " + replyOneBank[Json.BANK_NAME])
    print(" - bank SWIFT: " + replyOneBank[Json.BANK_SWIFT])

    totalBanks = totalBanks + 1

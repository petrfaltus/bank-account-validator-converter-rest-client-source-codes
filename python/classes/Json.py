import json

class Json:
    METHOD_NUMBER = "method_number"

    IBAN = "iban"
    COUNTRY = "country"

    ERROR_CODE = "error_code"
    ERROR_STRING = "error_string"
    DATA = "data"

    IBAN_HUMAN = "iban_human"
    ACCOUNT_NUMBER_IDENTIFICATOR = "account_number_identificator"
    BANK_CODE = "bank_code"
    ACCOUNT_NUMBER = "account_number"

    METHOD_COUNTRIES_NUMBER = 1
    METHOD_IBAN_TO_LOCAL_NUMBERING_NUMBER = 2

    lastErrorString = None

    @staticmethod
    def codeQueryCountries():
        output = {Json.METHOD_NUMBER: Json.METHOD_COUNTRIES_NUMBER}
        outputJson = json.dumps(output)

        return outputJson

    @staticmethod
    def codeQueryIbanToLocalNumbering(iban, country):
        output = {Json.METHOD_NUMBER: Json.METHOD_IBAN_TO_LOCAL_NUMBERING_NUMBER,
                  Json.IBAN: iban,
                  Json.COUNTRY: country}
        outputJson = json.dumps(output)

        return outputJson

    @staticmethod
    def decodeResult(inputJson):
        retData = None
        Json.lastErrorString = None

        try:
            input = json.loads(inputJson)

            if input[Json.ERROR_CODE] == 0:
                retData = input[Json.DATA]
            else:
                # error reported by the service
                Json.lastErrorString = input[Json.ERROR_STRING]
        except:
            # invalid or corrupted JSON
            retData = None

        return retData

    @staticmethod
    def getLastErrorString():
        return Json.lastErrorString

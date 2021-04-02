import json

class Json:
    METHOD_NUMBER = "method_number"

    ERROR_CODE = "error_code"
    ERROR_STRING = "error_string"
    DATA = "data"

    METHOD_COUNTRIES_NUMBER = 1

    lastErrorString = None

    @staticmethod
    def codeQueryCountries():
        output = {Json.METHOD_NUMBER: Json.METHOD_COUNTRIES_NUMBER}
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

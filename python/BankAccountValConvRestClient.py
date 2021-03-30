from classes.Web import Web

MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service"

# all supported countries query
print("All supported countries:")

requestJsonCountries = "{ \"method_number\": 1 }"

try:
    replyJsonCountries = Web.request(requestJsonCountries)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

print(replyJsonCountries)

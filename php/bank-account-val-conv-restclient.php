<?php

// constants
const PATH_TO_CLASSES = ".class";

// user classes autoloading
spl_autoload_register(function($class_name)
{
  $class_source_file_path = PATH_TO_CLASSES."/".$class_name.".php";
  require_once $class_source_file_path;
});

const MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service";
const MESSAGE_ERROR_DECODING_JSON = "error decoding the reply JSON";
const MESSAGE_RECEIVED_ERROR = "received error";

// all supported countries query
echo "All supported countries:".PHP_EOL;

$requestJsonCountries = tJson::codeQueryCountries();

$replyJsonCountries = tWeb::request($requestJsonCountries);
if ($replyJsonCountries === null)
{
  echo " - ".MESSAGE_ERROR_CONTACTING_SERVICE.PHP_EOL;
  return;
}

$countries = tJson::decodeResult($replyJsonCountries);
if ($countries === null)
{
  $errorString = tJson::getLastErrorString();

  if ($errorString != null)
  {
    echo " - ".MESSAGE_RECEIVED_ERROR.": ".$errorString.PHP_EOL;
  }
  else
  {
    echo " - ".MESSAGE_ERROR_DECODING_JSON.PHP_EOL;
  }

  return;
}

foreach ($countries as $countryCode => $countryName)
{
  echo " - ".$countryCode." (".$countryName."}".PHP_EOL;
}

echo PHP_EOL;

// IBAN validation and to local numbering conversion query
echo "IBAN validation and to local numbering conversion:".PHP_EOL;

$iban = "CZ6508000000192000145399";
$country = "cz"; // returned in the all supported countries request

$requestJsonIbanToLocalNumbering = tJson::codeQueryIbanToLocalNumbering($iban, $country);

$replyJsonIbanToLocalNumbering = tWeb::request($requestJsonIbanToLocalNumbering);
if ($replyJsonIbanToLocalNumbering === null)
{
  echo " - ".MESSAGE_ERROR_CONTACTING_SERVICE.PHP_EOL;
  return;
}

$replyLocalNumbering = tJson::decodeResult($replyJsonIbanToLocalNumbering);
if ($replyLocalNumbering === null)
{
  $errorString = tJson::getLastErrorString();

  if ($errorString != null)
  {
    echo " - ".MESSAGE_RECEIVED_ERROR.": ".$errorString.PHP_EOL;
  }
  else
  {
    echo " - ".MESSAGE_ERROR_DECODING_JSON.PHP_EOL;
  }

  return;
}

echo " - IBAN human: ".$replyLocalNumbering[tJson::IBAN_HUMAN].PHP_EOL;
echo " - account number identificator: ".$replyLocalNumbering[tJson::ACCOUNT_NUMBER_IDENTIFICATOR].PHP_EOL;
echo " - bank code: ".$replyLocalNumbering[tJson::BANK_CODE].PHP_EOL;
echo " - account number: ".$replyLocalNumbering[tJson::ACCOUNT_NUMBER].PHP_EOL;

echo PHP_EOL;

// local account number identificator validation query
echo "Local account number identificator validation:".PHP_EOL;

$account_number_identificator = "19-2000145399";
$country = "cz"; // returned in the all supported countries request

$requestJsonLocalNumberIdentificatorValid = tJson::codeQueryLocalNumberIdentificatorValid($account_number_identificator, $country);

$replyJsonLocalNumberIdentificatorValid = tWeb::request($requestJsonLocalNumberIdentificatorValid);
if ($replyJsonLocalNumberIdentificatorValid === null)
{
  echo " - ".MESSAGE_ERROR_CONTACTING_SERVICE.PHP_EOL;
  return;
}

$replyValid = tJson::decodeResultLocalNumberIdentificatorValid($replyJsonLocalNumberIdentificatorValid);
if ($replyValid === false)
{
  $errorString = tJson::getLastErrorString();

  if ($errorString != null)
  {
    echo " - ".MESSAGE_RECEIVED_ERROR.": ".$errorString.PHP_EOL;
  }
  else
  {
    echo " - ".MESSAGE_ERROR_DECODING_JSON.PHP_EOL;
  }

  return;
}

echo " - VALID!".PHP_EOL;

echo PHP_EOL;

// bank code validation and query
echo "Bank code validation and query:".PHP_EOL;

$bank_code = "0800";
$country = "cz"; // returned in the all supported countries request

$requestJsonBankCodeValid = tJson::codeQueryBankCodeValid($bank_code, $country);

$replyJsonBankCodeValid = tWeb::request($requestJsonBankCodeValid);
if ($replyJsonBankCodeValid === null)
{
  echo " - ".MESSAGE_ERROR_CONTACTING_SERVICE.PHP_EOL;
  return;
}

$replyBank = tJson::decodeResult($replyJsonBankCodeValid);
if ($replyBank === null)
{
  $errorString = tJson::getLastErrorString();

  if ($errorString != null)
  {
    echo " - ".MESSAGE_RECEIVED_ERROR.": ".$errorString.PHP_EOL;
  }
  else
  {
    echo " - ".MESSAGE_ERROR_DECODING_JSON.PHP_EOL;
  }

  return;
}

echo " - bank name: ".$replyBank[tJson::BANK_NAME].PHP_EOL;
echo " - bank swift: ".$replyBank[tJson::BANK_SWIFT].PHP_EOL;

?>

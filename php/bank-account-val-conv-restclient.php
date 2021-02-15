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

?>

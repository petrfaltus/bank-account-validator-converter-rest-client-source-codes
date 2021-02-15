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

$countries = tJson::decodeResultCountries($replyJsonCountries);
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

?>

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

// all supported countries query
echo "All supported countries:".PHP_EOL;

$requestJsonCountries = "{ \"method_number\":1 }";

$replyJsonCountries = tWeb::request($requestJsonCountries);
if ($replyJsonCountries === null)
{
  echo " - ".MESSAGE_ERROR_CONTACTING_SERVICE.PHP_EOL;
  return;
}

echo $replyJsonCountries.PHP_EOL;

?>

<?php

class tJson
{
  const METHOD_NUMBER = "method_number";

  const ERROR_CODE = "error_code";
  const ERROR_STRING = "error_string";
  const DATA = "data";

  const METHOD_COUNTRIES_NUMBER = 1;

  protected static $lastErrorString;

  //----------------------------------------------------------------------------
  public static function codeQueryCountries()
  {
    $output[self::METHOD_NUMBER] = self::METHOD_COUNTRIES_NUMBER;

    $outputJson = json_encode($output);

    return $outputJson;
  }
  //----------------------------------------------------------------------------
  public static function decodeResultCountries(&$inputJson)
  {
    $retData = null;
    self::$lastErrorString = null;

    $input = json_decode($inputJson, true);

    if ((!isset($input[self::ERROR_CODE])) or (!isset($input[self::ERROR_STRING])))
    {
      // invalid JSON
      $retData = null;
    }
    elseif ($input[self::ERROR_CODE] !== 0)
    {
      // error reported by the service
      $retData = null;
      self::$lastErrorString = $input[self::ERROR_STRING];
    }
    elseif ((!isset($input[self::DATA])) or (!is_array($input[self::DATA])))
    {
      // corrupted JSON
      $retData = null;
    }
    else
    {
      $retData = $input[self::DATA];
    }

    return $retData;
  }
  //----------------------------------------------------------------------------

  //----------------------------------------------------------------------------
  public static function getLastErrorString()
  {
    return self::$lastErrorString;
  }
  //----------------------------------------------------------------------------
}

?>

<?php

class tJson
{
  const METHOD_NUMBER = "method_number";

  const IBAN = "iban";
  const COUNTRY = "country";

  const ERROR_CODE = "error_code";
  const ERROR_STRING = "error_string";
  const DATA = "data";

  const IBAN_HUMAN = "iban_human";
  const ACCOUNT_NUMBER_IDENTIFICATOR = "account_number_identificator";
  const BANK_CODE = "bank_code";
  const ACCOUNT_NUMBER = "account_number";

  const METHOD_COUNTRIES_NUMBER = 1;
  const METHOD_IBAN_TO_LOCAL_NUMBERING_NUMBER = 2;
  const METHOD_LOCAL_NUMBER_IDENTIFICATOR_VALID_NUMBER = 3;

  protected static $lastErrorString;

  //----------------------------------------------------------------------------
  public static function codeQueryCountries()
  {
    $output[self::METHOD_NUMBER] = self::METHOD_COUNTRIES_NUMBER;

    $outputJson = json_encode($output);

    return $outputJson;
  }
  //----------------------------------------------------------------------------
  public static function codeQueryIbanToLocalNumbering(&$iban, &$country)
  {
    $output[self::METHOD_NUMBER] = self::METHOD_IBAN_TO_LOCAL_NUMBERING_NUMBER;
    $output[self::IBAN] = $iban;
    $output[self::COUNTRY] = $country;

    $outputJson = json_encode($output);

    return $outputJson;
  }
  //----------------------------------------------------------------------------
  public static function codeQueryLocalNumberIdentificatorValid(&$account_number_identificator, &$country)
  {
    $output[self::METHOD_NUMBER] = self::METHOD_LOCAL_NUMBER_IDENTIFICATOR_VALID_NUMBER;
    $output[self::ACCOUNT_NUMBER_IDENTIFICATOR] = $account_number_identificator;
    $output[self::COUNTRY] = $country;

    $outputJson = json_encode($output);

    return $outputJson;
  }
  //----------------------------------------------------------------------------
  public static function decodeResultLocalNumberIdentificatorValid(&$inputJson)
  {
    $retValue = false;
    self::$lastErrorString = null;

    $input = json_decode($inputJson, true);

    if ((!isset($input[self::ERROR_CODE])) or (!isset($input[self::ERROR_STRING])))
    {
      // invalid JSON
      $retValue = false;
    }
    elseif ($input[self::ERROR_CODE] !== 0)
    {
      // error reported by the service
      $retValue = false;
      self::$lastErrorString = $input[self::ERROR_STRING];
    }
    else
    {
      $retValue = true;
    }

    return $retValue;
  }
  //----------------------------------------------------------------------------
  public static function decodeResult(&$inputJson)
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

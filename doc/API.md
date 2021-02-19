# Bank account validator and converter REST API
Application interface description
(c) Petr Faltus 2021

---
## All supported countries request
Returns all country codes and names supported by the API.

-   **URL**
    <http://api.petrfaltus.net/bank_account/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value | Type    |
        | --------        | ----- | ----    |
        | `method_number` | 1     | integer |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 1
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value          | Type                |
        | --------       | -----          | ----                |
        | `error_code`   | 0              | integer             |
        | `error_string` | "ok"           | string              |
        | `data`         | *substructure* | list of *Data Item* |

    -   **Data Item Content**
        | Variable     | Value        | Type   |
        | --------     | -----        | ----   |
        | country code | country name | string |

    -   **Example JSON Reply**
        ```javascript
        {
          "error_code" : 0,
          "error_string" : "ok",
          "data" : {
                     "cz" : "Česká republika",
                     "sk" : "Slovensko"
                   }
        }
        ```

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## IBAN validation and to local numbering conversion request for the country
Validates the IBAN for the specified country in the API and returns the local account number if valid.

-   **URL**
    <http://api.petrfaltus.net/bank_account/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value        | Type    |
        | --------        | -----        | ----    |
        | `method_number` | 2            | integer |
        | `iban`          | IBAN         | string  |
        | `country`       | country code | string  |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 2,
          "iban" : "CZ6508000000192000145399",
          "country" : "cz"
        }
        ```
        or
        ```javascript
        {
          "method_number" : 2,
          "iban" : "SK3975000000000255018843",
          "country" : "sk"
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value          | Type    |
        | --------       | -----          | ----    |
        | `error_code`   | 0              | integer |
        | `error_string` | "ok"           | string  |
        | `data`         | *substructure* | *Data*  |

    -   **Data Content**
        | Variable                       | Value                              | Type   |
        | --------                       | -----                              | ----   |
        | `iban_human`                   | IBAN in the writable form          | string |
        | `account_number_identificator` | local account number identificator | string |
        | `bank_code`                    | bank code                          | string |
        | `account_number`               | local account number               | string |

    -   **Example JSON Reply**
        ```javascript
        {
          "error_code" : 0,
          "error_string" : "ok",
          "data" : {
                     "iban_human" : "CZ65 0800 0000 1920 0014 5399",
                     "account_number_identificator" : "19-2000145399",
                     "bank_code" : "0800",
                     "account_number" : "19-2000145399\/0800"
                   }
        }
        ```

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## Local account number identificator validation for the country
Validates the local account number identificator for the specified country in the API.

-   **URL**
    <http://api.petrfaltus.net/bank_account/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable                       | Value                              | Type    |
        | --------                       | -----                              | ----    |
        | `method_number`                | 3                                  | integer |
        | `account_number_identificator` | local account number identificator | string  |
        | `country`                      | country code                       | string  |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 3,
          "account_number_identificator" : "19-2000145399",
          "country" : "cz"
        }
        ```
        or
        ```javascript
        {
          "method_number" : 3,
          "account_number_identificator" : "631968405",
          "country" : "sk"
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value | Type    |
        | --------       | ----- | ----    |
        | `error_code`   | 0     | integer |
        | `error_string` | "ok"  | string  |

    -   **Example JSON Reply**
        ```javascript
        {
          "error_code" : 0,
          "error_string" : "ok"
        }
        ```

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## Bank code validation and query for the country
Validates the local bank code for the specified country in the API and returns bank details if valid.

-   **URL**
    <http://api.petrfaltus.net/bank_account/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value        | Type    |
        | --------        | -----        | ----    |
        | `method_number` | 4            | integer |
        | `bank_code`     | bank code    | string  |
        | `country`       | country code | string  |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 4,
          "bank_code" : "0800",
          "country" : "cz"
        }
        ```
        or
        ```javascript
        {
          "method_number" : 4,
          "bank_code" : "0900",
          "country" : "sk"
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value          | Type    |
        | --------       | -----          | ----    |
        | `error_code`   | 0              | integer |
        | `error_string` | "ok"           | string  |
        | `data`         | *substructure* | *Data*  |

    -   **Data Content**
        | Variable     | Value      | Type   |
        | --------     | -----      | ----   |
        | `bank_name`  | bank name  | string |
        | `bank_swift` | bank SWIFT | string |

    -   **Example JSON Reply**
        ```javascript
        {
          "error_code" : 0,
          "error_string" : "ok",
          "data" : {
                     "bank_name" : "Česká spořitelna, a.s.",
                     "bank_swift" : "GIBACZPX"
                   }
        }
        ```

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## Local numbering to IBAN conversion for the country
Converts the local account number to the IBAN for the specified country in the API. For local account number expects from the caller the already valid value.

-   **URL**
    <http://api.petrfaltus.net/bank_account/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable         | Value                              | Type    |
        | --------         | -----                              | ----    |
        | `method_number`  | 5                                  | integer |
        | `account_number` | local already valid account number | string  |
        | `country`        | country code                       | string  |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 5,
          "account_number" : "19-2000145399\/0800",
          "country" : "cz"
        }
        ```
        or
        ```javascript
        {
          "method_number" : 5,
          "account_number" : "631968405\/0900",
          "country" : "sk"
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value          | Type    |
        | --------       | -----          | ----    |
        | `error_code`   | 0              | integer |
        | `error_string` | "ok"           | string  |
        | `data`         | *substructure* | *Data*  |

    -   **Data Content**
        | Variable     | Value                     | Type   |
        | --------     | -----                     | ----   |
        | `iban`       | IBAN                      | string |
        | `iban_human` | IBAN in the writable form | string |

    -   **Example JSON Reply**
        ```javascript
        {
          "error_code" : 0,
          "error_string" : "ok",
          "data" : {
                     "iban" : "CZ6508000000192000145399",
                     "iban_human" : "CZ65 0800 0000 1920 0014 5399"
                   }
        }
        ```

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## One bank query for the country
Returns bank details for the specified country and requested name from the API.

-   **URL**
    <http://api.petrfaltus.net/bank_account/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value        | Type    |
        | --------        | -----        | ----    |
        | `method_number` | 6            | integer |
        | `query`         | bank name    | string  |
        | `country`       | country code | string  |

    -   **Optional**
        | Variable          | Value  | Type    |
        | --------          | -----  | ----    |
        | `czech_sensitive` | 0 or 1 | integer |
        | `case_sensitive`  | 0 or 1 | integer |

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 6,
          "query" : "\u010desk\u00e1",
          "country" : "cz"
        }
        ```
        or
        ```javascript
        {
          "method_number" : 6,
          "query" : "slovenska",
          "country" : "sk"
        }
        ```
        or
        ```javascript
        {
          "method_number" : 6,
          "query" : "Slovensk\u00e1",
          "country" : "sk"
          "czech_sensitive" : 1,
          "case_sensitive" : 1
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable          | Value                          | Type                 |
        | --------          | -----                          | ----                 |
        | `error_code`      | 0                              | integer              |
        | `error_string`    | "ok"                           | string               |
        | `czech_sensitive` | 0 or 1 (used for this request) | integer              |
        | `case_sensitive`  | 0 or 1 (used for this request) | integer              |
        | `data`            | *substructure*                 | array of *Data Item* |

    -   **Data Item Content**
        | Variable     | Value      | Type   |
        | --------     | -----      | ----   |
        | `bank_code`  | bank code  | string |
        | `bank_name`  | bank name  | string |
        | `bank_swift` | bank SWIFT | string |

    -   **Example JSON Reply**
        ```javascript
        {
          "error_code" : 0,
          "error_string" : "ok",
          "czech_sensitive" : 0,
          "case_sensitive" : 0,
          "data" : [
                     {
                       "bank_code" : "0710",
                       "bank_name" : "ČESKÁ NÁRODNÍ BANKA",
                       "bank_swift" : "CNBACZPP"
                     },
                     {
                       "bank_code" : "0800",
                       "bank_name" : "Česká spořitelna, a.s.",
                       "bank_swift" : "GIBACZPX"
                     },
                     {
                       "bank_code" : "2240",
                       "bank_name" : "Poštová banka, a.s., pobočka Česká republika",
                       "bank_swift" : "POBNCZPP"
                     },
                     {
                       "bank_code" : "6300",
                       "bank_name" : "BNP Paribas S.A., pobočka Česká republika",
                       "bank_swift" : "GEBACZPP"
                     },
                     {
                       "bank_code" : "8040",
                       "bank_name" : "Oberbank AG pobočka Česká republika",
                       "bank_swift" : "OBKLCZ2X"
                     },
                     {
                       "bank_code" : "8090",
                       "bank_name" : "Česká exportní banka, a.s.",
                       "bank_swift" : "CZEECZPP"
                     },
                     {
                       "bank_code" : "8200",
                       "bank_name" : "PRIVAT BANK der Raiffeisenlandesbank Oberösterreich Aktiengesellschaft, pobočka Česká republika",
                       "bank_swift" : ""
                     }
                   ]
        }
        ```

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## Communication diagnostics query
Returns detected communication properties for the REST request.

-   **URL**
    <http://api.petrfaltus.net/bank_account/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value | Type    |
        | --------        | ----- | ----    |
        | `method_number` | 0     | integer |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 0
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable         | Value                     | Type    |
        | --------         | -----                     | ----    |
        | `error_code`     | 0                         | integer |
        | `error_string`   | "ok"                      | string  |
        | `request_method` | request method            | string  |
        | `proxy_ip`       | IP of the proxy           | string  |
        | `proxy_software` | proxy software            | string  |
        | `client_ip`      | IP of the client          | string  |
        | `user_agent`     | user agent header line    | string  |
        | `user_language`  | user language header line | string  |

    -   **Example JSON Reply**
        ```javascript
        {
          "error_code" : 0,
          "error_string" : "ok",
          "request_method" : "POST",
          "proxy_ip" : "",
          "proxy_software" : "",
          "client_ip" : "85.70.117.166",
          "user_agent" : "Petr Faltus Java Bank account validator and converter REST client",
          "user_language" : ""
        }
        ```

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

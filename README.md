# Bank account validator and converter REST client source codes
Small example console source codes for a validation and conversion of the bank account. It is the client that communicates using JSONs with my own bank account validation and conversion REST service.

## Running under Windows
1. clone this repository to your computer
2. build and run the example **Java** code
3. compile and run the example **.NET C#** code
4. run the example **PHP** code
5. run the example **Python** code

### 1. Cloning to your computer
- install [GIT] on your computer
- clone this repository to your computer by the GIT command
  `git clone https://github.com/petrfaltus/bank-account-validator-converter-rest-client-source-codes.git`

### 2. The Java client source code
- install [Java JDK] on your computer
- set the OS environment `%JAVA_HOME%` variable (must exist `"%JAVA_HOME%\bin\java.exe"`)

#### 2.1. Apache Maven
- install [Apache Maven] on your computer
- add the Maven directory (where the batch `mvn.cmd` locates) to the OS environment `%PATH%` variable

The subdirectory `java-maven` contains prepared Windows batches:
- `01-build.cmd` - cleans, compiles and builds the Maven project
- `02-run.cmd` - runs the built Java archive (JAR)
- `03-clean.cmd` - cleans the Maven project

#### 2.2. Gradle Build Tool
- install [Gradle Build Tool] on your computer
- add the Gradle directory (where the batch `gradle.bat` locates) to the OS environment `%PATH%` variable

The subdirectory `java-gradle` contains prepared Windows batches:
- `01-build.cmd` - cleans, compiles and builds the Gradle project
- `02-run.cmd` - runs the built Java archive (JAR)
- `03-clean.cmd` - cleans the Gradle project

### 3. The .NET C# client source code
- use the `csc.exe` .NET C# compiler that is the part of Microsoft .NET Framework (part of OS)

The subdirectory `csharp` contains prepared Windows batches:
- `01-compile.cmd` - compiles the source code (contains the path definition to the `csc.exe` compiler)
- `02-run.cmd` - runs the Windows executable
- `03-clean.cmd` - deletes the Windows executable

For the JSON serialization and JSON deserialization there is the [Newtonsoft.Json.dll] needed.

### 4. The PHP client source code
- install [PHP] on your computer
- set the OS environment `%PHP_HOME%` variable (must exist `"%PHP_HOME%\php.exe"`)

The subdirectory `php` contains prepared Windows batch:
- `01-run.cmd` - runs the code through the PHP interpreter

### 5. The Python client source code
- install [Python] on your computer
- set the OS environment `%PYTHON_HOME%` variable (must exist `"%PYTHON_HOME%\python.exe"`)

The subdirectory `python` contains prepared Windows batch:
- `01-run.cmd` - runs the code through the Python interpreter

## Versions
Now in February 2021 I have the computer with **Windows 10 Pro 64bit**, **12GB RAM** and available **50GB free HDD space**

| Tool | Version | Setting |
| ------ | ------ | ------ |
| [GIT] | 2.30.0.windows.2 | |
| [Java JDK] | 15.0.2 | Java(TM) SE Runtime Environment (build 15.0.2+7-27) |
| [Apache Maven] | 3.6.3 | |
| [Gradle Build Tool] | 6.8.2 | |
| .NET C# compiler | 4.8.3752.0 | |
| [Newtonsoft.Json.dll] | 12.0.3.23909 | part of the repository |
| [PHP] | 7.4.15 | 7.4.15-nts-Win32-vc15-x64 |
| [Python] | 3.4.3 | |

## To do (my plans to the future)


[GIT]: <https://git-scm.com/>
[Java JDK]: <https://www.oracle.com/java/technologies/javase-downloads.html>
[Apache Maven]: <https://maven.apache.org/>
[Gradle Build Tool]: <https://gradle.org/>
[Newtonsoft.Json.dll]: <https://www.newtonsoft.com/>
[PHP]: <https://www.php.net/>
[Python]: <https://www.python.org/>

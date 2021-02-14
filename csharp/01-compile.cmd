@echo off

set OUT=bin

set PROJECT=BankAccountValConvRestClient
set SOURCE=Program
set EXECUTABLE=%OUT%\%PROJECT%.exe
set ICON=ico\BankAccount.ico
set DOTNET_HOME=C:\WINDOWS\Microsoft.NET\Framework64\v4.0.30319

set DLL=%OUT%\Newtonsoft.Json.dll

mkdir %OUT%
"%DOTNET_HOME%\csc.exe" /win32icon:%ICON% /out:%EXECUTABLE% /reference:%DLL% %SOURCE%.cs Web.cs RestRequest1.cs RestReply1.cs RestRequest2.cs RestReply2.cs DataLocalNumbering.cs RestRequest3.cs RestReply3.cs RestRequest4.cs RestReply4.cs DataBank.cs RestRequest5.cs RestReply5.cs DataIban.cs RestRequest6.cs RestReply6.cs DataOneBank.cs

@echo off
call keytool -genkey -alias ftps -keyalg RSA -keysize 2048 -keystore keystore.jks -dname "CN=Nguyen Xuan Hoang, OU=PTIT, O=DEMO, L=Nguyen trai, S=Hanoi, C=VN" -storepass password -keypass password
pause
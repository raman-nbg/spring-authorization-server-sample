# Spring Authorization Server Sample
This project contains a basic sample implementation of a
OAuth2 authorization server based on Spring Boot and
Spring Security OAuth2.

## Usage
### Storage
All OAuth2 clients are stored in a MongoDB. Provide a MongoDB
running on localhost:27017. Everything else is picked up automatically.

### SSL
The server only accepts connections via HTTPS. Therefore you have to
provide a SSL certificate. You can create a self-signed certificate
using the following commands:

```shell
cd src/main/resources/keystore
keytool -genkeypair -alias localhost -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore localhost.p12 -validity 3650
keytool -export -keystore keystore.jks -alias localhost -file myCertificate.crt
```

While creating the certificate you can choose any password. Set this password
in `src/main/resources/application.yml` within the property `server.ssl.key-store-password`.
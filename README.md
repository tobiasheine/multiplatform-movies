# Data
A Kotlin Multiplatform Library exposing data transfer objects being shared between `backend` and `frontend`.

# Backend
A JVM project exposing a Ktor HTTP API, including the `/movies` endpoint.

# Frontend
A Kotlin Multiplatform Library consuming the `/movies` endpoint, shared bei `android` & `ios`.

Libraries:
- [Ktor HTTP Client](https://ktor.io/clients/http-client/multiplatform.html)
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [SQLDelight](https://github.com/cashapp/sqldelight)

# Android & iOS
Native mobile clients using the frontend library to render a movie gallery.

![Native apps screenshot](https://user-images.githubusercontent.com/1046688/69727862-4e987b00-10f1-11ea-8b5a-e5126d165daf.png)

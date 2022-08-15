## Hi!

This is a simple HTTP service which exposes an HTTP POST endpoint.

I created a series app, so a central data structure is a series, containing their name, release year, country and genre. I added such series to an internal database and return events that have a unique identifier and a timestamp.

To start the server and issue HTTP requests to the server I tried this:
curl -XPOST -H "Content-Type: application/json" -d '{"name": "Dark", "launchYear":2017, "country":"Germany", "genre": "Sciene Fiction"}' localhost:8080/api/series


### Tools

- Scala
- Akka
- Spray
- IntelliJ
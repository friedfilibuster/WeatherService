To build:

`gradle bootJar`

To run:

`gradle bootRun`

To test:

`gradle test`

To request wind information for a zip code:

`curl http://localhost:8080/api/v1/wind/89101`

To clear the weather data cache:

`curl -X POST http://localhost:8080/cache/clear`
# GoCardless Pro Java Example

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

This is a simple Java application that uses the [GoCardless Pro API](https://developer.gocardless.com/pro/) to collect recurring payments for subscriptions.  It uses the GoCardless [redirect flow](https://developer.gocardless.com/pro/#api-endpoints-redirect-flows), and is built using the [Dropwizard](http://www.dropwizard.io) framework.

## Running the app locally

First, register a sandbox account [here](https://manage-sandbox.gocardless.com/), and grab an API key and secret from the dashboard.  Then:

```
export GC_API_KEY=...
export GC_API_SECRET=...
gradle jar
java -jar build/libs/enterprise-solutions.jar server config.yml
```

The app will be running at [http://localhost:8080](http://localhost:8080).

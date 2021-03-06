# GoCardless Pro Java Example

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

This is a simple Java application that uses the [GoCardless Pro API](https://developer.gocardless.com/pro/) to collect recurring payments for subscriptions.  It uses the GoCardless [redirect flow](https://developer.gocardless.com/pro/#api-endpoints-redirect-flows), and is built using the [Dropwizard](http://www.dropwizard.io) framework.

The app can be seen running at [https://gocardless-pro-java-example.herokuapp.com](https://gocardless-pro-java-example.herokuapp.com).

## Running the app locally

First, register a sandbox account [here](https://manage-sandbox.gocardless.com/), and grab an access token from the dashboard.  Then:

```
export GC_ACCESS_TOKEN=...
gradle shadowJar
java -jar build/libs/enterprise-solutions-all.jar server config.yml
```

The app will be running at [http://localhost:8080](http://localhost:8080).

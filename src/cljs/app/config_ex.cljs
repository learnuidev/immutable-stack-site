(ns app.config-ex)

(defonce env {:region "us-east-1"
              :userPoolId "example-user-pool-id"
              :userPoolWebClientId "example-userpool-web-client-id"
              :mandatorySignIn true
              :authenticationType "AMAZON_COGNITO_USER_POOLS"})

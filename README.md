# spring-security-oAuth2-jwt-practise

## Spring Security

* server.port=8080
* Open explorer with http://localhost:8080, use user/123 to login.

## OAuth2

### Authorization Server

* server.port=8081
* Open explorer with GET
  request: http://localhost:8081/oauth/authorize?client_id=client&response_type=code, then use
  user/123 approve the request. Then it will redirect to https://www.baidu/callback?code=HzhTb7.
  Copy the authorization code "HzhTb7" for next step.
* Open PostMan with POST
  request: http://localhost:8081/oauth/token?grant_type=authorization_code&client_id=client&client_secret=secret&code=HzhTb7&redirect_url=https://www.baidu.com/callback&scope=all&username=user&password=123.Copy
  the access_token value for next step.

### Resource Server

* server.port=8082
* Open PostMan with GET request: http://localhost:8082/hello?access_token=???
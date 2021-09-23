# spring-security-oAuth2-jwt-practise

## Spring Security

* server.port=7080
* Open explorer with http://localhost:7080, use user/123 to login.

## OAuth2

### Authorization Server

* server.port=7081
* Open explorer with GET
  request: http://localhost:7081/oauth/authorize?client_id=client&response_type=code, then use
  user/123 approve the request. Then it will redirect to http://localhost:9099/login?code=VDV3pz.
  Copy the authorization code "VDV3pz" for next step.
* Open PostMan with POST
  request: http://localhost:7081/oauth/token?grant_type=authorization_code&client_id=client&client_secret=secret&code=VDV3pz&redirect_url=https://www.baidu.com/callback&scope=read_profile_info&username=user&password=123
  the access_token value for next step.

### Resource Server

* server.port=7082
* Open PostMan with GET request: http://localhost:7082/hello?access_token=???
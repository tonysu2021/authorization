## 測試Oauth

以下API部分需使用Postman Authorization功能，請選擇驗證的Type例如 Basic Token或是 Basic Auth。

```http
## 建立一組token (clientapp:123)
POST /oauth/token?grant_type=password&username=tony01&password=123456&scope=read HTTP/1.1
Host: 127.0.0.1:10083
Authorization: Basic Y2xpZW50YXBwOjEyMw==

## 確認Token內容
POST /oauth/check_token?token=<Access_Token> HTTP/1.1
Host: 127.0.0.1:10083

## 確認Token加密密要 (clientapp:123)
GET /.well-known/jwks.json HTTP/1.1
Host: 127.0.0.1:10083
Authorization: Basic Y2xpZW50YXBwOjEyMw==

## Refresh Token (clientapp:123)
POST /oauth/token?grant_type=refresh_token&refresh_token=<Refresh_Token> HTTP/1.1
Host: 127.0.0.1:10083
Authorization: Basic Y2xpZW50YXBwOjEyMw==

## 查詢使用者
GET /user/tony01 HTTP/1.1
Host: 127.0.0.1:10083
Authorization: Basic YWRtaW46MTIzNDU2
Cookie: JSESSIONID=8AA55590D644D52FB414413D7435546F

## 註冊使用者

POST /user HTTP/1.1
Host: 127.0.0.1:10083
Content-Type: application/json
Content-Length: 89

{
    "userName":"tony02",
    "password":"123456",
    "phone":"",
    "email":""
}


```

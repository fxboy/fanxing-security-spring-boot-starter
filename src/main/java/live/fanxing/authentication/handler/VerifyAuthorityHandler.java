package live.fanxing.authentication.handler;


import live.fanxing.authentication.entity.Authentications;
import live.fanxing.authentication.exception.TokenVerificationFailedException;

import javax.servlet.http.HttpServletRequest;

public interface VerifyAuthorityHandler {

     Authentications tokenAuthentication(HttpServletRequest request) throws TokenVerificationFailedException;  //token的校验方法,返回权限列表
}

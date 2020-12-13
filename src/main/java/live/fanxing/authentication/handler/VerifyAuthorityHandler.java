package live.fanxing.authentication.handler;


import live.fanxing.authentication.entity.Authentications;
import live.fanxing.authentication.exception.TokenVerificationFailedException;
import live.fanxing.authentication.exception.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

public interface VerifyAuthorityHandler {
     Authentications verifyAuthority(HttpServletRequest request) throws AuthenticationException;  //token的校验方法,返回权限列表
}

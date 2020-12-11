package live.fanxing.security.handler;


import live.fanxing.security.entity.Authentication;
import live.fanxing.security.exception.TokenAuthenticationFailedException;

public interface VerifyAuthorityHandler {

     Authentication tokenAuthentication(Object Token) throws TokenAuthenticationFailedException;  //token的校验方法,返回权限列表
}

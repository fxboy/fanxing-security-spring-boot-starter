package live.fanxing.authentication.exception;

@Deprecated
public class TokenVerificationFailedException extends Exception {
        public TokenVerificationFailedException(String message){
            super(message);
        }
}

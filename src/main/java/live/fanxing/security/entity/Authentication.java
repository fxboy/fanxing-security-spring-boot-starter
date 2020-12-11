package live.fanxing.security.entity;

import java.util.List;

public class Authentication {
    List<String> authentication;
    public Authentication(List<String> authentication){
        this.authentication = authentication;
    }

    public List<String> getAuthentication(){
        return this.authentication;
    }




    @Override
    public String toString() {
        return "Authentication{" +
                "authentication=" + authentication +
                '}';
    }
}

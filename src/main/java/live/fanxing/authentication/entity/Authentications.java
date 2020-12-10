package live.fanxing.authentication.entity;

import javax.swing.text.html.parser.Entity;
import java.util.*;

/**
 *  用来保存roles或者permission
 *  roles： 角色
 *  permission: 权限
 * */
public class Authentications{
    int size = 0;
    HashMap<String,String> value;

    public Authentications(){
        this.value = new HashMap<>();
    }
    public int size(){
        return this.size;
    }

    /**
     *  添加权限角色
     *
     * @param role 角色
     * @param permission 操作权限
     * */
    public void add(String role,String permission) {
        value.put(role,permission);
        this.size++;
    }

    /**
     * 判断传过来的类key是否包含此类的某一个key
     * */
    public boolean hasAuthority(String[] role,String[] permission) {
        System.out.println("role length：" + role.length);
        System.out.println("per length:"+permission.length);
        boolean contain = false;
        // 校验是否拥有角色权限
        for (String e : role) {
            System.out.println("角色："+e);
            if(this.value.containsKey(e)){
                contain = true;
                System.out.println("rolexuan：" + e);
                break;
            }
        }

        //校验操作权限是否操作,如果角色为0的话，要权限操作也没用，如果需要的话，可以把role.length给删了
        if(permission.length < 1 || role.length < 1){
            System.out.println("长度为0");
            contain = true;  //如果角色为null
        }else{
            contain = false;
            for (String e : permission) {
                System.out.println("权限："+e);
                if(this.value.containsValue(e)){
                    System.out.println("per：" + e);
                    contain = true;
                    break;
                }
            }
        }
        System.out.println("验证的权限："+ contain);

        return contain;
    }

    /**
     *  返回一个set，存放当前所有的角色
     * */
    public Set roleSet(){
        return this.value.keySet();
    }

    /**
     *  返回一个集合，存放当前所有的权限
     * */
    public Collection permissionSet(){
        return this.value.values();
    }

    /**
     * 返回一个角色与操作的EntrySet
     * */
    public Set rolePermissionKey(){
        return this.value.entrySet();
    }

    /**
     * 用来获取一个角色是否存在
     **/
    public boolean roleExistence(String value){
        return this.value.containsKey(value);
    }

    /**
     * 用来获取一个操作是否存在
     **/
    public boolean permissionExistence(String value){
        return this.value.containsValue(value);
    }

    @Override
    public String toString() {
        return "Authentications{" +
                "size=" + size +
                ", value=" + value +
                '}';
    }
}

package live.fanxing.authentication.entity;

import live.fanxing.authentication.exception.PermissionTypeExcetion;
import live.fanxing.authentication.exception.RolesLengthExcetion;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  用来保存roles或者permission
 *  roles： 角色
 *  permission: 权限
 * */
public class Authentications<T>{
    int size = 0;
    HashMap<String,T> value;

    public Authentications(){
        this.value = new HashMap<String,T>();
    }
    public int size(){
        return this.size;
    }

    /**
     *  添加权限角色
     *
     * @param role 角色,仅有一个角色，如果有多个可以在执行add方法添加
     * @param permissions 操作权限 多个 只可以是数组或者字符串
     * */
    public void add(String role,T permissions) {
        value.put(role,permissions);
        this.size++;
    }

    public boolean iAdopt(boolean adopt){
        if(adopt){
            return adopt;
        }else{
            return !adopt;
        }
    }

    public List<String> hasRole(List<String> roles) throws RolesLengthExcetion {
        List<String> hasRoles = new ArrayList<String>(); //用来存当前用户所通过的权限
        if(roles.size() == 0) {
            throw new RolesLengthExcetion("Roles length should not be 0");
        }

        for (String role : roles) {
            if(this.value.containsKey(role)){
                hasRoles.add(role);
            }
        }
        return hasRoles;
    }

    public boolean hasAllPermission(List<String> permissions,String role) throws PermissionTypeExcetion {
        // 先判断长度是否为0，如果为0，则 不需要验证权限
        if(permissions.size() == 0){
            return true;
        }
        T userPermission = this.value.get(role);

        //因为注解上要操作权限了，如果这个获取的角色并没有设置权限
        if(userPermission == null){
            return false;
        }
        //开始判断权限是否存在一个
        //判断权限是否是数组类型的
        if(userPermission.getClass() == String[].class){
            List<String> userPermissionList = Arrays.asList((String[]) userPermission);
            // 如果是数组类型，则将它转换为List后，用 用户权限去判断，是否包含所有的注解上的权限
            return userPermissionList.containsAll(permissions);
        }else if(userPermission.getClass() == String.class){

            //如果用户权限只有一个，注解上的权限长度为1 也就是一个,只需要判断这俩是否相等即可，如果有多个，那肯定是不通过
            if(permissions.size() == 1){
                return permissions.get(0).equals(userPermission);
            }
            return false;

        }else if(userPermission.getClass() == ArrayList.class){
            //判断是否是arraylist类型的
            List<String> userPermissionList = (List<String>) userPermission;
            return userPermissionList.containsAll(permissions);

        }
        else{
            throw new PermissionTypeExcetion("The permission type added should be String, String [] or ArrayList<String>");

        }
    }

    public boolean hasAnyPermission(List<String> permissions,String role) throws PermissionTypeExcetion {
        // 先判断长度是否为0，如果为0，则 不需要验证权限
        if(permissions.size() == 0){
            return true;
        }
        T userPermission = this.value.get(role);

        //因为注解上要操作权限了，如果这个获取的角色并没有设置权限
        if(userPermission == null){
            return false;
        }

        //开始判断权限是否存在一个
        //判断权限是否是数组类型的
        if(userPermission.getClass() == String[].class){
            List<String> userPermissionList = Arrays.asList((String[]) userPermission);
            for (String s : userPermissionList) {
                if(permissions.contains(s)){
                    return true;
                }
            }

        }else if(userPermission.getClass() == String.class){
            //判断权限是否字符串类型的
            for (String permission : permissions) {
                if(permission.equals(userPermission)){
                    return true;
                }
            }

        }else if(userPermission.getClass() == ArrayList.class){
            //判断是否是arraylist类型的
            for (String s : (List<String>)userPermission) {
                if(permissions.contains(s)){
                    return true;
                }
            }

        }
        else{
            throw new PermissionTypeExcetion("The permission type added should be String, String [] or ArrayList<String>");
        }
        return false;
    }


    /**
     * 判断传过来的类key是否包含此类的某一个key  1.3.5
     * */
    @Deprecated
    public boolean hasAuthorityDepr(String[] role,String[] permission) {
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

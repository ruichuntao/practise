package rui.designPattern;

public class UserManagerImplProxy implements UserManager {

    public UserManager manager;

    public UserManagerImplProxy(UserManager manager) {
        this.manager = manager;
    }

    @Override
    public void addUser(String userId, String userName) {
        manager.addUser(userId, userName);
    }

    @Override
    public void delUser(String userId) {
        manager.delUser(userId);
    }

    @Override
    public void findUser(String userId) {
        manager.findUser(userId);
    }

    @Override
    public void modifyUser(String userId) {
        manager.modifyUser(userId);
    }

    public static void main(String[] args) {
        UserManagerImplProxy proxy = new UserManagerImplProxy(new UserManagerImpl());
        proxy.addUser("111","张三");
    }

}

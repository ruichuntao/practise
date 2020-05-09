package rui.designPattern;

public class UserManagerImpl implements UserManager {
    @Override
    public void addUser(String userId, String userName) {
        System.out.println("addUser" + userName);
    }

    @Override
    public void delUser(String userId) {
        System.out.println("delUser" + userId);
    }

    @Override
    public void findUser(String userId) {
        System.out.println("findUser" + userId);
    }

    @Override
    public void modifyUser(String userId) {
        System.out.println("modifyUser" + userId);
    }
}

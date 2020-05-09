package rui.designPattern;

public interface UserManager {
    void addUser(String userId, String userName);

    void delUser(String userId);

    void findUser(String userId);

    void modifyUser(String userId);

}

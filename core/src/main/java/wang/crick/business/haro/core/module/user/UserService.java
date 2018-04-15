package wang.crick.business.haro.core.module.user;

import wang.crick.business.haro.core.domain.User;

public interface UserService {

    User findUnique();

    boolean modifyPassword(String password, String id);

    User login(String username, String password);

    boolean validExisted(String username, String password);

}

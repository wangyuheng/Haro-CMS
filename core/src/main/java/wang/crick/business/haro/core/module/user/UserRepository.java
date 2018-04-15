package wang.crick.business.haro.core.module.user;

import wang.crick.business.haro.core.domain.User;

public interface UserRepository {

    User findUnique();

    User findUnique(String username, String password);

    boolean modifyPassword(String password, String id);

}

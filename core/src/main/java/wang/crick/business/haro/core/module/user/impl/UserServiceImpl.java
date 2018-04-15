package wang.crick.business.haro.core.module.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.crick.business.haro.core.domain.User;
import wang.crick.business.haro.core.module.user.UserRepository;
import wang.crick.business.haro.core.module.user.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public User findUnique() {
        return userRepository.findUnique();
    }

    public boolean modifyPassword(String password, String id) {
        return userRepository.modifyPassword(password, id);
    }

    public User login(String username, String password) {
        return userRepository.findUnique(username, password);
    }

    public boolean validExisted(String username, String password) {
        return userRepository.findUnique(username, password) != null;
    }
}

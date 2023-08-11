package mbuchatskyi.service;

import mbuchatskyi.model.User;

public interface UserService {
    User create(User user);
    
    User readById(long id);
    
    User readByEmail(String email);
    
    User update(User user);
}

package mbuchatskyi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mbuchatskyi.exception.NullEntityReferenceException;
import mbuchatskyi.model.User;
import mbuchatskyi.repository.UserRepository;
import mbuchatskyi.service.UserService;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        if (user != null) {
            return userRepository.save(user);
        }
        
        throw new NullEntityReferenceException("User cannot be 'null'");
    }
    
    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User readByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User update(User user) {
        if (user != null) {
            readById(user.getId());
            return userRepository.save(user);
        }
        
        throw new NullEntityReferenceException("User cannot be 'null'");
    }
}

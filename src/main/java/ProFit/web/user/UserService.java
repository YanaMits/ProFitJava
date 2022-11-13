package ProFit.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public List<User> listAll(){
        return (List<User>) repository.findAll();
    }

    public void save(User user){
        repository.save(user);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()){
            repository.deleteById(id);
        }
        else {
            throw new UserNotFoundException("No user found with id:" + id);
        }
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserNotFoundException("No user found with id:" + id);
    }
}

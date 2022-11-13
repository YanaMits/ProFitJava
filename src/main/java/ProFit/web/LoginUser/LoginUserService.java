package ProFit.web.LoginUser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginUserService implements UserDetailsService {

    private final LoginUserRepository loginUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return loginUserRepository.findByEmail(email)
                .orElseThrow(
                        ()->new UsernameNotFoundException("No such email.")
                );
    }

    public String signUpUser(LoginUser loginUser) {
        boolean userExists = loginUserRepository.findByEmail(loginUser.getEmail()).isPresent();
        if(!userExists) {
            throw new IllegalStateException("Email already exists.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(loginUser.getPassword());
        loginUser.setPassword(encodedPassword);

        loginUserRepository.save(loginUser);

        return "done!";
    }
}

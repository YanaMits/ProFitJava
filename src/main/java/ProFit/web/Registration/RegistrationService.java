package ProFit.web.Registration;

import ProFit.web.LoginUser.LoginUser;
import ProFit.web.LoginUser.LoginUserService;
import ProFit.web.LoginUser.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private LoginUserService loginUserService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return loginUserService.signUpUser(
                new LoginUser(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        UserRole.USER
                )
        );

    }
}

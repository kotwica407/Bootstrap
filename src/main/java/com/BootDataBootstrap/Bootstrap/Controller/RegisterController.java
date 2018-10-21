package com.BootDataBootstrap.Bootstrap.Controller;

import com.BootDataBootstrap.Bootstrap.model.Users;
import com.BootDataBootstrap.Bootstrap.service.CustomUserDetailsService;
import com.BootDataBootstrap.Bootstrap.service.EmailService;
import org.apache.catalina.User;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
public class RegisterController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private CustomUserDetailsService customUserDetailsService;
    private EmailService emailService;

    @Autowired
    public RegisterController(CustomUserDetailsService customUserDetailsService, EmailService emailService) {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
        this.customUserDetailsService = customUserDetailsService;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, Users users){
        modelAndView.addObject("user", users);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid Users users, BindingResult bindingResult, HttpServletRequest request){
        modelAndView.addObject("user", users);
        Optional<Users> usersExists = customUserDetailsService.findByEmail(users.getEmail());

        if(usersExists.isPresent()){
            modelAndView.addObject("errorMessage", "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
            bindingResult.reject("email");
        }

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("register");
        }
        else {
            //user is disabled until click on confirmation link
            users.setEnabled(false);

            //generate token for confirmation link
            users.setConfirmationToken(UUID.randomUUID().toString());

            customUserDetailsService.saveUser(users);
            String appUrl = request.getScheme()+"://"+request.getServerName();
            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(users.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                    + "http://localhost:8080/confirm?token=" + users.getConfirmationToken());
            registrationEmail.setFrom("noreply@domain.com");

            emailService.sendEmail(registrationEmail);
            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + users.getEmail());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }

    @GetMapping("/confirm")
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token){

        Optional<Users> users = customUserDetailsService.findByConfirmationToken(token);

        if(users.isPresent()==false){
            modelAndView.addObject("invalidToken", "This is an invalid confirmation link");
        }
        else {
            modelAndView.addObject("confirmationToken", users.get().getConfirmationToken());
        }

        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    @PostMapping("/confirm")
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam("password") String password, @RequestParam("token") String token, RedirectAttributes redir){
        modelAndView.setViewName("confirm");

        //find the user with associated token
        Optional<Users> users = customUserDetailsService.findByConfirmationToken(token);

        //set new password
        users.get().setPassword(bCryptPasswordEncoder.encode(password));

        users.get().setEnabled(true);

        customUserDetailsService.saveUser(users.get());
        modelAndView.addObject("successMessage", "Your password has been set");
        return modelAndView;

    }
}





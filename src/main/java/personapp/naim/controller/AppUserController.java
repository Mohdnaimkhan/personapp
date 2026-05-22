package personapp.naim.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import personapp.naim.model.AppUser;
import personapp.naim.repository.AppUserRepository;

@Controller
public class AppUserController {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("appUser") AppUser appUser,
                               BindingResult bindingResult,
                               Model model) {
        if (appUserRepository.findByUsername(appUser.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "error.appUser", "Username already exists");
        }
        if (appUserRepository.findByEmail(appUser.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.appUser", "Email already exists");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepository.save(appUser);
        return "redirect:/login?registered";
    }
}

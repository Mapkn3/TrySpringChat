package ru.mapkn3.TrySpringChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.mapkn3.TrySpringChat.model.Account;
import ru.mapkn3.TrySpringChat.model.Profile;
import ru.mapkn3.TrySpringChat.service.AccountService;
import ru.mapkn3.TrySpringChat.service.ProfileService;
import ru.mapkn3.TrySpringChat.service.RoleService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    ProfileService profileService;
    @Autowired
    AccountService accountService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn() {
        return "signin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("authData", new AuthData());
        return "edit";
    }

    @RequestMapping(method = RequestMethod.POST, params = "new")
    public String newUser(@Valid AuthData authData, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "edit";
            }
            Profile profile = authData.getProfile();
            Account account = authData.getAccount();
            account.setRole(roleService.getRoleByName("User"));
            account.setProfile(profile);
            profile.setAccount(account);
            profileService.addNewProfile(profile);
            accountService.addNewAccount(account);
            return "redirect:/user/signin";
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            return "redirect:/user/signup?error=Login%20already%20in%20use";
        }
    }

    @RequestMapping("/{login}")
    public String profile(@PathVariable("login") String login, Principal principal, Model model) {
        if (principal.getName().equals(login)) {
            Account account = accountService.getAccountByName(login);
            model.addAttribute("account", account);
            return "detail";
        } else {
            return "redirect:/home";
        }
    }

    private class AuthData {
        @Valid
        private Profile profile;
        @Valid
        private Account account;

        public AuthData() {
            profile = new Profile();
            account = new Account();
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }
    }
}

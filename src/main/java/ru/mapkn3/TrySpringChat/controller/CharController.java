package ru.mapkn3.TrySpringChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.mapkn3.TrySpringChat.model.Account;
import ru.mapkn3.TrySpringChat.model.Message;
import ru.mapkn3.TrySpringChat.service.AccountService;
import ru.mapkn3.TrySpringChat.service.MessageService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dialogs")
public class CharController {
    @Autowired
    AccountService accountService;
    @Autowired
    MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView dialogs() {
        List<Account> friends = accountService.getAllAccounts();
        return new ModelAndView("dialogs", "dialogs", friends);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public String history(@PathVariable("login") String login, Principal principal, Model model) {
        Account a = accountService.getAccountByName(principal.getName());
        Account b = accountService.getAccountByName(login);
        List<Message> history = messageService.getHistory(a, b);
        model.addAttribute("history", history);
        model.addAttribute("message", new Message());
        return "history";
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.POST)
    public String sendMsg(@PathVariable("login") String login, Principal principal, @Valid Message message) {
        Account a = accountService.getAccountByName(principal.getName());
        Account b = accountService.getAccountByName(login);
        message.setFrom(a);
        message.setTo(b);
        messageService.addNewMessage(message);
        return "redirect:/dialogs/" + login;
    }
}

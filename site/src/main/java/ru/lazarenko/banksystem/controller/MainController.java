package ru.lazarenko.banksystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lazarenko.banksystem.client.CustomerClient;
import ru.lazarenko.model.ClientRegisterRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final CustomerClient customerClient;

    @GetMapping("/")
    public String getStartPage() {
        return "index";
    }

    @GetMapping("/reg")
    public String getRegistrationPage(Model model) {
        log.info("get Registration Page");
        model.addAttribute("request", new ClientRegisterRequest());
        return "registration";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        log.info("get Login Page");
        return "login";
    }

    @PostMapping("/reg")
    @ResponseBody
    public void registration(@ModelAttribute("request") ClientRegisterRequest request) {
        log.info("request for registration new Client {}", request);
        customerClient.reg(request);
    }
}

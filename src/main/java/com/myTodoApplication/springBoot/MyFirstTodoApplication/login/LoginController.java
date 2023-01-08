package com.myTodoApplication.springBoot.MyFirstTodoApplication.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
    @RequestMapping(value="login",method = RequestMethod.GET)
    public String gotologin() {
        return "Login";
    }
    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
        if(authenticationService.authenticate(name,password)){
            model.put("name",name);
            return "Welcome";
        }
        model.put("errorMessage","invalid Credentilas !please try again");
        return "Login";
    }
}

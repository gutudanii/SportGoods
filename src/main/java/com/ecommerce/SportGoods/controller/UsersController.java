package com.ecommerce.SportGoods.controller;

import com.ecommerce.SportGoods.mail.Mail;
import com.ecommerce.SportGoods.mail.MailService;
import com.ecommerce.SportGoods.model.Cart;
import com.ecommerce.SportGoods.model.SportAcc;
import com.ecommerce.SportGoods.model.Users;
import com.ecommerce.SportGoods.repository.CartRepository;
import com.ecommerce.SportGoods.repository.UsersRepository;
import com.ecommerce.SportGoods.service.CartService;
import com.ecommerce.SportGoods.service.SportAccService;
import com.ecommerce.SportGoods.service.UsersService;
import com.ecommerce.SportGoods.session.SessionHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
//@RequestMapping(value = {"/users"})
public class UsersController implements ErrorController {

    @Autowired
    private final UsersService usersService;

    @Autowired
    private final SportAccService sportAccService;

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final CartService cartService;
    @Autowired
    private final MailService mailService;

    @Autowired
    private final UsersRepository usersRepository;

    @PostMapping("/users/signup")
    public String saveUser(@ModelAttribute Users users) {
        try {
            Users existingUser = usersRepository.findByUsername(users.getUsername()).orElse(null);
            if (existingUser != null) {
                return "redirect:/users/create?error=true"; // Redirect to the create page with the error parameter

            }
            users.setBlock(true);
            usersService.saveUser(users);

            Mail mail = new Mail();
            mail.setEmail(users.getEmailAddress());
            String link = "http://localhost:8080/activate/"+users.getUserId().toString();
            System.out.println(link);
            mailService.sendMail(mail, users.getFullName(), link);

            return "redirect:/users/login"; // Redirect to the login page after successful signup
        } catch (Exception e) {
            // Handle the exception here, such as logging or showing a generic error page
            return "redirect:/users/create?pr=true"; // Redirect to an error page
        }

    }

    @GetMapping("/users/create")
    public String getRegisterPage(Users users, Model model) {
        model.addAttribute("user", users);
        model.addAttribute("errorMessage", "Username already exists. Please choose a different username.");
        model.addAttribute("pr", "An error occurred during signup. check your email or if this problem persists again contact us");
        return "register.html";
    }

    @GetMapping("/users/login")
    public String getLoginPage(Principal principal, Model model) {
        String nm = SessionHandler.getSession().getUsername();
            if (principal != null) {
                Users users1 = usersRepository.findByUsername(principal.getName()).get();
                model.addAttribute("pr", nm);
                model.addAttribute("message", " X You have been successfully signed in as " + principal.getName());
                model.addAttribute("rmv", false);
                model.addAttribute("apr", true);
                List<SportAcc> accList = sportAccService.getAllSportAccs();
                model.addAttribute("sportAcc", accList);
                if (users1.getRole().equals("user")) {
                    model.addAttribute("sportAcc", accList);
                    return "redirect:/home";
                } else {
                    return "redirect:/dashboard";
                }
            } else {
                return "login.html";
            }
        }

    @GetMapping("/logouts")
    public String logoutHandler(Model model) {
        model.addAttribute("msg", true);
        model.addAttribute("msgnr", "| X | You have successfully logged out");
        return "redirect:/";
    }

    @GetMapping("/users/profile")
    public String getProfile(Model model, Principal p) {
        Users users = usersRepository.findByUsername(p.getName()).get();
        model.addAttribute("sportAcc", users);
        return "profile";
    }

    @RequestMapping(path = {"/users/delete", "/users/delete/{id}"})
    public String deleteTasks(@PathVariable("id") Long id) {
        usersService.deleteUser(id);
        return "redirect:/dashboard";
    }

    @RequestMapping({"/activate", "/activate/{id}"})
    public String updateUser(@PathVariable("id")Long id){
        usersService.updateUser(id);
        return "redirect:/users/login";
    }

    @GetMapping("/users/carts")
    public String getCarts(Model model, Authentication authentication) {
        String username = authentication.getName();
        Users user = usersRepository.findByUsername(username).orElse(null);

        if (user != null) {
            model.addAttribute("user", user);

            // Fetch user's carts and associated sport accessories
            List<Cart> carts = cartService.getAllCartByUserId(user.getUserId());
            List<SportAcc> sportAccList = new ArrayList<>();

            for (Cart cart : carts) {
                List<SportAcc> sportAccs = sportAccService.getAllSportAccByOrderId(cart.getOrderId());
                sportAccList.addAll(sportAccs);
            }
            int sum = 0;
            for (int i=0; i<sportAccList.size(); i++){
                sum+=sportAccList.get(i).getOpdPrice();
            }

            model.addAttribute("price", sum);
            model.addAttribute("cartM", carts);
            model.addAttribute("sportM", sportAccList);
            model.addAttribute("sportAcc", user.getUsername());
        }
        return "carts";
    }
}
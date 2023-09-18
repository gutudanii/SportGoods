package com.ecommerce.SportGoods.controller;

import com.ecommerce.SportGoods.model.*;
import com.ecommerce.SportGoods.repository.PaymentRepository;
import com.ecommerce.SportGoods.repository.SportAccRepository;
import com.ecommerce.SportGoods.repository.UsersRepository;
import com.ecommerce.SportGoods.service.*;
import com.ecommerce.SportGoods.session.SessionHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class SportAccController {

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final SportAccRepository sportAccRepository;
    @Autowired
    private final UsersService usersService;
    @Autowired
    private final NotificationService notificationService;
    @Autowired
    private final CartService cartService;
    @Autowired
    private final SportAccService sportAccService;
    @Autowired
    private final PaymentService paymentService;
    @Autowired
    private final PaymentRepository paymentRepository;
    @GetMapping("/")
    public String getLanding(Authentication authentication, Model model){
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home"; // Redirect to home page if authenticated
        }
        List<SportAcc> getAllSportAcc = sportAccService.getAllSportAccs();

        model.addAttribute("sportAcc", getAllSportAcc);
        model.addAttribute("crt", new Cart());

        List<SportAcc> getFeatured = sportAccService.getAllByType("Featured");
        List<SportAcc> getNormal = sportAccService.getAllByType("Normal");
        List<SportAcc> getSale = sportAccService.getAllByType("Sale");

        model.addAttribute("featured", getFeatured);
        model.addAttribute("normal", getNormal);
        model.addAttribute("sale", getSale);

        return "landing.html";
    }
    @GetMapping("/home")
    public String getHome(@RequestParam(name = "query", required = false) String query, Principal principal, Model model) {
        String nm = SessionHandler.getSession().getUsername();
            if (principal != null) {
            // User is logged in, so show profile dropdown
            model.addAttribute("pr", nm);
            model.addAttribute("apr", true);
            model.addAttribute("rmv", false);

            Users users = usersRepository.findByUsername(principal.getName()).get();
            if (users.getRole().equals("user")){
                model.addAttribute("crt", new Cart());
                Users user = usersRepository.findByUsername(principal.getName()).get();
                List<Cart> carts = cartService.getAllCartByUserId(user.getUserId());
                model.addAttribute("cartSize", carts.size());
                if (users.isBlock() == true){
                    model.addAttribute("isBlock", true);
                    model.addAttribute("message", " (X) Your Account is not Activated please activate using info sent to your mail");
                }
                if (query != null && !query.isEmpty()) {
                    List<SportAcc> searchResults = sportAccRepository.findByOrderNameContainingIgnoreCase(query);
                    model.addAttribute("sportAcc", searchResults);
                }
                else {
                    List<SportAcc> getAllSportAcc = sportAccService.getAllSportAccs();
                    model.addAttribute("sportAcc",getAllSportAcc);
                }
                return "home.html";
            }
            else{
                return "redirect:/dashboard";
            }
        }
        else {
            // User is not logged in, so show Register and Login links
                if (query != null && !query.isEmpty()) {
                    List<SportAcc> searchResults = sportAccRepository.findByOrderNameContainingIgnoreCase(query);
                    model.addAttribute("sportAcc", searchResults);
                }
                else {
                    List<SportAcc> getAllSportAcc = sportAccService.getAllSportAccs();
                    model.addAttribute("sportAcc",getAllSportAcc);
                }
            model.addAttribute("apr", false);
            model.addAttribute("rmv", true);
            model.addAttribute("crt", new Cart());
            return "home.html";
        }
    }
     @GetMapping("error")
     public String getError(){
        return "error";
    }
    @GetMapping("/dashboard")
    public String getDashboard(Model model){
       List<Users> getAllUser  = usersService.getAllUser();
       List<SportAcc> getAllSportAcc = sportAccService.getAllSportAccs();
       model.addAttribute("usr",getAllUser.size());
        model.addAttribute("users", getAllUser);
        model.addAttribute("sportAcc", getAllSportAcc);
        model.addAttribute("sportAc", new SportAcc());

        List<Payment> paymentList = paymentService.getAllPayment();
        model.addAttribute("payment", paymentList);

        List<Notification> notificationList = notificationService.getAllNotifications();
        model.addAttribute("notifications", notificationList);

        List<Payment> getAllPaymentsDoneAmount = paymentRepository.findAll();
        double price = 0;
        for (int i=0; i<getAllPaymentsDoneAmount.size(); i++){
            if (getAllPaymentsDoneAmount.get(i).isApproved()){
            price+=getAllPaymentsDoneAmount.get(i).getAmount();
            }
           }
        model.addAttribute("totalSale", price);

          return "dashboard";
    }
    @GetMapping("/list")
    public String listSportAccs(Model model) {
        List<SportAcc> sportAccs = sportAccService.getAllSportAccs();
        model.addAttribute("sportAccs", sportAccs);
        return "sportacc/list";
    }

    @GetMapping("/create")
    public String createSportAccForm(Model model) {
        model.addAttribute("sportAcc", new SportAcc());
        return "add";
    }

    @PostMapping("/sport/create")
    public String createSportAcc(@ModelAttribute SportAcc sportAcc,
                                 @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            sportAcc.setName(file.getOriginalFilename());
            sportAcc.setImage(file.getBytes());
            sportAccService.createSportAcc(sportAcc);
        }
        else {
            sportAcc.setName("");
            sportAcc.setImage(null);
            sportAccService.createSportAcc(sportAcc);
        }
        return "redirect:/dashboard";
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> viewImage(@PathVariable Long id) {
        Optional<SportAcc> imageEntityOptional = sportAccRepository.findById(id);
        if (imageEntityOptional.isPresent()) {
            SportAcc imageEntity = imageEntityOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageEntity.getImage(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/edit/{id}")
    public String editSportAccForm(@PathVariable Long id, Model model) {
        SportAcc sportAcc = sportAccService.getSportAccById(id);
        model.addAttribute("sportAcc", sportAcc);
        return "sportacc/edit";
    }

    @PostMapping("/edit/{id}")
    public String editSportAcc(@PathVariable Long id, @ModelAttribute SportAcc sportAcc) {
        sportAccService.updateSportAcc(id, sportAcc);
        return "redirect:/sportacc/list";
    }

    @GetMapping("/sport/delete/{id}")
    public String deleteSportAcc(@PathVariable Long id) {
        sportAccService.deleteSportAcc(id);
        return "redirect:/dashboard";
    }

}


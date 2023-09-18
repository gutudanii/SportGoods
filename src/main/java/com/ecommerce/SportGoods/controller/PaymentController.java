package com.ecommerce.SportGoods.controller;

import com.ecommerce.SportGoods.model.Payment;
import com.ecommerce.SportGoods.model.Shipping;
import com.ecommerce.SportGoods.model.SportAcc;
import com.ecommerce.SportGoods.repository.PaymentRepository;
import com.ecommerce.SportGoods.service.PaymentService;
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
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PaymentController {

        @Autowired
        private final PaymentService paymentService;

        @Autowired
        private final PaymentRepository paymentRepository;

        @PostMapping("/payment/done")
        public String payNow(@ModelAttribute Payment payment, Authentication authentication,
                             @RequestParam("imageFile")MultipartFile file) throws IOException {
            if (!file.isEmpty()){
                payment.setTransImg(file.getBytes());
            }
            else {
                payment.setTransImg(null);
            }
            paymentService.createPayment(payment, authentication);
            return "redirect:/shipping/payment";
        }

        @GetMapping("/shipping/payment")
        public String payments(Model model){
            model.addAttribute("shipping", new Shipping());
            return "shipping.html";
        }
        @RequestMapping({"/approve/payment", "/approve/payment/{id}"})
        public String approvePayment(@PathVariable("id") Long id){
            paymentService.approvePayment(id);
            return "redirect:/dashboard";
        }
    @RequestMapping({"/notApprove/payment", "/notApprove/payment/{id}"})
    public String approvePayments(@PathVariable("id") Long id){
        paymentService.approvePayments(id);
        return "redirect:/dashboard";
    }
    @GetMapping("/payment/{amount}")
    public String getPaymentWithAmount(@PathVariable double amount, Model model, Principal principal) {
        model.addAttribute("pr", principal.getName());
        model.addAttribute("amount", amount);
        model.addAttribute("paymentDone", new Payment());
        return "payment.html";
    }

    @GetMapping("/image/payment/{id}")
    public ResponseEntity<byte[]> viewImage(@PathVariable Long id) {
        Optional<Payment> imageEntityOptional = paymentRepository.findById(id);
        if (imageEntityOptional.isPresent()) {
            Payment imageEntity = imageEntityOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageEntity.getTransImg(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

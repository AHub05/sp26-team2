package com.CSC340.MinervasList.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CSC340.MinervasList.entity.Customer;
import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.service.CustomerService;
import com.CSC340.MinervasList.service.ListingService;
import com.CSC340.MinervasList.service.PurchaseService;
import com.CSC340.MinervasList.service.ReviewService;
import com.CSC340.MinervasList.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerUiController {
    
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired 
    private PurchaseService purchaseService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ListingService listingService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            Customer customer = (Customer)userService.getUserByEmail(email);
            if (customer != null) {
                session.setAttribute("customerId", customer.getUserId());
                return "redirect:/customer/";
            }
            return "redirect:/login";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @GetMapping({"/", "/browse"})
    public String browseProducts(HttpSession session, Model model) {
        Long customerId = (Long)session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        List<Listing> allListings = listingService.getAllListings();
        model.addAttribute("productListings", allListings);
        return "customer/browse";
    }

}

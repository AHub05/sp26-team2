package com.CSC340.MinervasList.mvc;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CSC340.MinervasList.entity.Customer;
import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Purchase;
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

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Long customerId = (Long)session.getAttribute("customerId");
        if(customerId == null) {
            return "redirect:/home";
        }

        return "customer/home";
    }

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

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        Long customerId = (Long)session.getAttribute("customerId");
        if(customerId == null) {
            return "redirect:/login";
        }

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return "redirect:/login";
        }

        List<Purchase> purchases = purchaseService.getPurchasesByCustomerId(customer.getUserId());
        
        model.addAttribute("purchases", purchases);
        model.addAttribute("customer", customer);

        return "customer/profile-page";
    }

    @GetMapping("/shop/{listingId}")
    public String productListing(@PathVariable long listingId, Model model) {
        Listing listing = listingService.getListingById(listingId);
        if (listing != null) {
            model.addAttribute("listing", listing);
            return "customer/item-listing";
        }

        return "custome/browse";
    }

    @PostMapping("/shop/{listingId}/purchase")
    public String purchaseProduct(HttpSession session, @PathVariable long listingId, @RequestParam Double quantity) {
        Long customerId = (Long)session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        Customer customer = customerService.getCustomerById(customerId);
        Listing listing = listingService.getListingById(listingId);
        if (customer == null || listing == null) {
            return "redirect:/customer/browse";
        }

        Purchase purchase = new Purchase();
        purchase.setQuantity(quantity.intValue());
        purchase.setTotalPrice(listing.getPrice().doubleValue() * quantity);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setCustomer(customer);
        purchase.setListing(listing);
        purchaseService.createPurchase(purchase);

        return "redirect:/customer/profile?success";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}

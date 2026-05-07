package com.CSC340.MinervasList.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.CSC340.MinervasList.entity.Customer;
import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.service.ListingService;
import com.CSC340.MinervasList.service.SellerService;
import com.CSC340.MinervasList.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/seller")
public class SellerUiController {

    private static final Logger log = LoggerFactory.getLogger(SellerUiController.class);

    private final ListingService listingService;
    private final SellerService sellerService;
    private final UserService userService;

    public SellerUiController(ListingService listingService,
                              SellerService sellerService,
                              UserService userService) {
        this.listingService = listingService;
        this.sellerService = sellerService;
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("seller", new Seller());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Seller seller,
                         @RequestParam(name = "profilePhoto", required = false) MultipartFile profilePhoto) {
        try {
            Seller createdSeller = sellerService.createSellerWithPhoto(seller, profilePhoto);
            return "redirect:/login?sellerCreated=" + createdSeller.getUserId();
        } catch (RuntimeException ex) {
            log.warn("Seller signup failed for email '{}': {}", seller.getEmail(), ex.getMessage());
            return "redirect:/signup?error=seller";
        }
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email,
                         @RequestParam String password,
                         HttpSession session) {
        try {
            String userType = sellerService.getUserTypeForEmail(email).trim();

            if ("SELLER".equalsIgnoreCase(userType)) {
                Seller seller = sellerService.getSellerByEmail(email);

                if (!sellerService.credentialsMatch(seller, password)) {
                    log.warn("Seller login rejected for email '{}': password mismatch", email);
                    return "redirect:/login?error=sellerPassword";
                }

                session.setAttribute("sellerId", seller.getUserId());
                log.info("Seller login succeeded for email '{}' with sellerId {}", seller.getEmail(), seller.getUserId());
                return "redirect:/seller/home";
            }

            if ("CUSTOMER".equalsIgnoreCase(userType)
                    && userService.getUserByEmail(email) instanceof Customer customer) {
                if (customer.getPassword().equals(password)) {
                    session.setAttribute("customerId", customer.getUserId());
                    log.info("Customer login routed from seller tab for email '{}'", customer.getEmail());
                    return "redirect:/customer/home";
                }
                return "redirect:/login?error=customer";
            }

            log.warn("Seller login rejected for email '{}': found user_type '{}'", email, userType);
            return "MISSING".equals(userType)
                    ? "redirect:/login?error=sellerMissing"
                    : "redirect:/login?error=sellerWrongType";
        } catch (Exception e) {
            String userType = sellerService.getUserTypeForEmail(email);
            log.warn("Seller login failed for email '{}': {}. Native user_type lookup: {}", email, e.getMessage(), userType);
            return "MISSING".equals(userType)
                    ? "redirect:/login?error=sellerMissing"
                    : "redirect:/login?error=sellerWrongType";
        }
    }

    @GetMapping({"/", "/home"})
    public String sellerHome(HttpSession session, Model model) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        model.addAttribute("seller", seller);
        model.addAttribute("stats", sellerService.getSellerStats(seller.getUserId()));
        model.addAttribute("recentListings", listingService.getListingsBySellerId(seller.getUserId()));
        return "seller/home";
    }

    @GetMapping("/profile")
    public String sellerProfile(HttpSession session, Model model) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        model.addAttribute("seller", seller);
        model.addAttribute("stats", sellerService.getSellerStats(seller.getUserId()));
        return "seller/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(HttpSession session,
                                @ModelAttribute Seller sellerForm,
                                @RequestParam(name = "profilePhoto", required = false) MultipartFile profilePhoto) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        try {
            sellerService.updateSellerWithPhoto(seller.getUserId(), sellerForm, profilePhoto);
            return "redirect:/seller/profile?updated";
        } catch (RuntimeException ex) {
            log.warn("Seller profile update failed for sellerId {}: {}", seller.getUserId(), ex.getMessage(), ex);
            return "redirect:/seller/profile?error=update";
        }
    }

    @GetMapping("/listings")
    public String sellerListings(HttpSession session, Model model) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        try {
            model.addAttribute("seller", seller);
            model.addAttribute("selectedSeller", seller);
            model.addAttribute("selectedSellerId", seller.getUserId());
            model.addAttribute("listings", listingService.getListingsBySellerId(seller.getUserId()));
            model.addAttribute("stats", sellerService.getSellerStats(seller.getUserId()));
            return "seller-listings";
        } catch (RuntimeException ex) {
            log.warn("Seller listings page failed for sellerId {}: {}", seller.getUserId(), ex.getMessage(), ex);
            return "redirect:/seller/home?error=listings";
        }
    }

    @GetMapping("/listings/new")
    public String newListingForm(HttpSession session, Model model) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        addListingFormAttributes(model, seller.getUserId(), new Listing(), "Create Listing", "/seller/listings");
        return "seller-listing-form";
    }

    @PostMapping("/listings")
    public String createListing(HttpSession session,
                                @ModelAttribute Listing listing,
                                @RequestParam(name = "listingPhoto", required = false) MultipartFile listingPhoto) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        listingService.createListingForSellerWithPhoto(seller.getUserId(), listing, listingPhoto);
        return "redirect:/seller/listings";
    }

    @GetMapping("/listings/{listingId}/edit")
    public String editListingForm(@PathVariable Long listingId, HttpSession session, Model model) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        Listing listing = listingService.getSellerListing(seller.getUserId(), listingId);
        addListingFormAttributes(model, seller.getUserId(), listing, "Update Listing",
                "/seller/listings/" + listingId);
        return "seller-listing-form";
    }

    @PostMapping("/listings/{listingId}")
    public String updateListing(@PathVariable Long listingId,
                                HttpSession session,
                                @ModelAttribute Listing listing,
                                @RequestParam(name = "listingPhoto", required = false) MultipartFile listingPhoto) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        listingService.updateSellerListingWithPhoto(seller.getUserId(), listingId, listing, listingPhoto);
        return "redirect:/seller/listings";
    }

    @PostMapping("/listings/{listingId}/delete")
    public String deleteListing(@PathVariable Long listingId, HttpSession session) {
        Seller seller = requireSellerOrRedirect(session);
        if (seller == null) {
            return "redirect:/login";
        }
        try {
            listingService.deleteSellerListing(seller.getUserId(), listingId);
            return "redirect:/seller/listings?deleted";
        } catch (RuntimeException ex) {
            log.warn("Failed to delete listing {} for seller {}: {}", listingId, seller.getUserId(), ex.getMessage());
            return "redirect:/seller/listings?error=hasPurchases";
        }
    }

    

    @GetMapping("/photo/{sellerId}")
    public ResponseEntity<byte[]> sellerPhoto(@PathVariable Long sellerId) {
        Seller seller = sellerService.getSellerById(sellerId);
        if (!seller.hasStoredProfilePhoto()) {
            return ResponseEntity.notFound().build();
        }

        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        if (seller.getProfilePhotoContentType() != null) {
            mediaType = MediaType.parseMediaType(seller.getProfilePhotoContentType());
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
                .contentType(mediaType)
                .body(seller.getProfilePhotoData());
    }

    @GetMapping("/listings/{listingId}/photo")
    public ResponseEntity<byte[]> listingPhoto(@PathVariable Long listingId) {
        Listing listing = listingService.getListingById(listingId);
        if (!listing.hasStoredPhoto()) {
            return ResponseEntity.notFound().build();
        }

        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        if (listing.getPhotoContentType() != null) {
            mediaType = MediaType.parseMediaType(listing.getPhotoContentType());
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
                .contentType(mediaType)
                .body(listing.getPhotoData());
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private void addListingFormAttributes(Model model, Long sellerId, Listing listing, String formTitle,
                                          String formAction) {
        Seller seller = sellerService.getSellerById(sellerId);
        model.addAttribute("seller", seller);
        model.addAttribute("sellerId", sellerId);
        model.addAttribute("listing", listing);
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("formAction", formAction);
        model.addAttribute("categories", Listing.Category.values());
        model.addAttribute("conditions", Listing.ItemCondition.values());
        model.addAttribute("statuses", Listing.ListingStatus.values());
    }

    private Seller requireSellerOrRedirect(HttpSession session) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return null;
        }
        return sellerService.getSellerById(sellerId);
    }
}

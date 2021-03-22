package com.ldp.vigilantBean.controller.rest;

import com.ldp.vigilantBean.domain.order.Cart;
import com.ldp.vigilantBean.domain.order.CartItem;
import com.ldp.vigilantBean.domain.order.CartItemDTO;
import com.ldp.vigilantBean.service.CartService;
import com.ldp.vigilantBean.validator.EntityProcessingResponse;
import com.ldp.vigilantBean.validator.NewCartItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.ldp.vigilantBean.controller.extractor.ParamExtractor.safelyExtractLong;

@RestController
@RequestMapping("api/cart")
public class CartRestController {

   private NewCartItemValidator cartItemValidator;

   private MessageSource messageSource;

   private CartService cartService;

   public CartRestController(
           @Autowired
           CartService cartService,
           @Autowired
           NewCartItemValidator cartItemValidator,
           @Autowired
           MessageSource messageSource) {

      this.cartItemValidator = cartItemValidator;
      this.messageSource = messageSource;
      this.cartService = cartService;
   }

   @GetMapping("/getCart")
   public Cart getCart(HttpServletResponse response) {
      Optional<Cart> optCart = cartService.getCart();

      if (optCart.isPresent())
         return optCart.get();
      else
          throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
   }

   @GetMapping("/getProductCount")
   public ResponseEntity<Long> getProductCount() {

      return new ResponseEntity<Long>(3L, HttpStatus.OK);

   }

   @PostMapping("/removeCartItem")
   public ResponseEntity<Boolean> removeCartItem(
           @RequestParam("cartItemId") String id) {

      boolean removed = cartService.removeCartItem(Long.parseLong(id));

      if (removed)
         return new ResponseEntity<>(HttpStatus.OK);
      else
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @PutMapping("/updateCartItem")
   public ResponseEntity<CartItem> updateCartItem(
           @RequestParam("cartItemId") Long cartItemId,
           @RequestParam("quantity") Long quantity) {

      Optional<CartItem> optUpdatedCartItem =
              cartService.updateCartItemQuantity(cartItemId, quantity);

      if (optUpdatedCartItem.isPresent())
         return new ResponseEntity<>(optUpdatedCartItem.get(), HttpStatus.OK);
      else
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @PutMapping("/addProduct")
   public ResponseEntity<EntityProcessingResponse> addProductToCart(HttpServletRequest request) {

      CartItemDTO cartItemDTO = extractCartItem(request);

      EntityProcessingResponse response =
              new EntityProcessingResponse(request.getLocale(), messageSource);
      cartItemValidator.validate(cartItemDTO, response);

      if (response.hasErrors()) {
         response.externalizeMessages();
         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
      }

      try {
         cartService.addCartItem(cartItemDTO);
      } catch (AuthenticationCredentialsNotFoundException userNotAuthenticatedException) {

         response.addErrorCode("validation.cart.addProduct.userNotAuthenticated");
         response.externalizeMessages();
         return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
      }

      response.externalizeMessages();
      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   private CartItemDTO extractCartItem(HttpServletRequest request) {

      return CartItemDTO.builder()
                          .quantity(safelyExtractLong(request, "quantity"))
                          .productId(safelyExtractLong(request, "productId"))
                        .build();
   }

}

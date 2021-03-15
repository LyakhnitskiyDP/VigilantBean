package com.ldp.vigilantBean.controller.rest;

import com.ldp.vigilantBean.domain.order.CartItemDTO;
import com.ldp.vigilantBean.service.CartService;
import com.ldp.vigilantBean.validator.EntityProcessingResponse;
import com.ldp.vigilantBean.validator.NewCartItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

   @PutMapping("/addProduct")
   public ResponseEntity<EntityProcessingResponse> addProductToCart(HttpServletRequest request) {

      CartItemDTO cartItemDTO = extractCartItem(request);

      EntityProcessingResponse response =
              new EntityProcessingResponse(request.getLocale(), messageSource);
      cartItemValidator.validate(cartItemDTO, response);
      response.externalizeMessages();

      if (response.hasErrors())
         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

      try {
         cartService.addCartItem(cartItemDTO);
      } catch (AuthenticationCredentialsNotFoundException userNotAuthenticatedException) {

         response.addErrorCode("validation.cart.addProduct.userNotAuthenticated");
         return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
      }

      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   private CartItemDTO extractCartItem(HttpServletRequest request) {

      return CartItemDTO.builder()
                          .quantity(safelyExtractLong(request, "quantity"))
                          .productId(safelyExtractLong(request, "productId"))
                        .build();
   }

}

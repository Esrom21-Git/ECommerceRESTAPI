//package org.yearup.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//import org.yearup.data.ProductDao;
//import org.yearup.data.ShoppingCartDao;
//import org.yearup.data.UserDao;
//import org.yearup.models.Product;
//import org.yearup.models.ShoppingCart;
//import org.yearup.models.ShoppingCartItem;
//import org.yearup.models.User;
//
//import java.security.Principal;
//
//@RestController
//@RequestMapping("/cart")
//@CrossOrigin
//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//public class ShoppingCartController {
//
//    private final ShoppingCartDao shoppingCartDao;
//    private final UserDao userDao;
//    private final ProductDao productDao;
//
//    @Autowired
//    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
//        this.shoppingCartDao = shoppingCartDao;
//        this.userDao = userDao;
//        this.productDao = productDao;
//    }
//
//    /**
//     * GET /cart - Retrieve the current user's shopping cart
//     */
//    @GetMapping
//    public ShoppingCart getCart(Principal principal) {
//        try {
//            int userId = getCurrentUserId(principal);
//            return shoppingCartDao.getByUserId(userId);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve cart");
//        }
//    }
//
//    /**
//     * POST /cart/products/{productId} - Add a product to the cart
//     */
//    @PostMapping("/products/{productId}")
//    public void addProductToCart(@PathVariable int productId, Principal principal) {
//        try {
//            int userId = getCurrentUserId(principal);
//
//            // Verify product exists
//            validateProductExists(productId);
//
//            // Add product to cart with default quantity of 1
//            shoppingCartDao.addItem(userId, productId, 1);
//        } catch (ResponseStatusException ex) {
//            throw ex;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add product to cart");
//        }
//    }
//
//    /**
//     * PUT /cart/products/{productId} - Update product quantity in cart
//     */
//    @PutMapping("/products/{productId}")
//    public void updateProductInCart(@PathVariable int productId,
//                                    @RequestBody ShoppingCartItem item,
//                                    Principal principal) {
//        try {
//            int userId = getCurrentUserId(principal);
//
//            // Verify product exists
//            validateProductExists(productId);
//
//            // Validate quantity
//            if (item.getQuantity() <= 0) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than 0");
//            }
//
//            // Update product quantity in cart
//            shoppingCartDao.updateItem(userId, productId, item.getQuantity());
//        } catch (ResponseStatusException ex) {
//            throw ex;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update cart item");
//        }
//    }
//
//    /**
//     * DELETE /cart/products/{productId} - Remove a specific product from cart
//     */
//    @DeleteMapping("/products/{productId}")
//    public void removeProductFromCart(@PathVariable int productId, Principal principal) {
//        try {
//            int userId = getCurrentUserId(principal);
//            shoppingCartDao.removeItem(userId, productId);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to remove product from cart");
//        }
//    }
//
//    /**
//     * DELETE /cart - Clear all products from the current user's cart
//     */
//    @DeleteMapping
//    public void clearCart(Principal principal) {
//        try {
//            int userId = getCurrentUserId(principal);
//            shoppingCartDao.clearCart(userId);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to clear cart");
//        }
//    }
//
//    /**
//     * GET /cart/count - Get total item count in cart
//     */
//    @GetMapping("/count")
//    public int getCartItemCount(Principal principal) {
//        try {
//            int userId = getCurrentUserId(principal);
//            ShoppingCart cart = shoppingCartDao.getByUserId(userId);
//            return cart.getItems().values().stream()
//                    .mapToInt(ShoppingCartItem::getQuantity)
//                    .sum();
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get cart count");
//        }
//    }
//
//    /**
//     * Helper method to get current user ID from Principal
//     */
//    private int getCurrentUserId(Principal principal) {
//        String userName = principal.getName();
//        User user = userDao.getByUserName(userName);
//        if (user == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
//        }
//        return user.getId();
//    }
//
//    /**
//     * Helper method to validate that a product exists
//     */
//    private void validateProductExists(int productId) {
//        Product product = productDao.getById(productId);
//        if (product == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
//        }
//    }
//}
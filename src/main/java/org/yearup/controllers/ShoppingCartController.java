package org.yearup.controllers;

import org.yearup.models.ShoppingCartItem;
import java.util.List;

interface SimpleShoppingCartDao{
    List<ShoppingCartItem> getCartByUserId(int userId);
    void addProductToCart(int userId, int productId);
    void updateProductQuantity(int userId, int productId, int quantity);
    void clearCart(int userId);
}
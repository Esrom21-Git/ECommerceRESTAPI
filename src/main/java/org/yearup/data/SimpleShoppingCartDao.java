package org.yearup.data;

import org.yearup.models.ShoppingCartItem;
import java.util.List;

public interface SimpleShoppingCartDao {
    /**
     * Retrieves all items in the shopping cart for a specific user
     * @param userId the ID of the user
     * @return List of ShoppingCartItem objects
     */
    List<ShoppingCartItem> getCartByUserId(int userId);

    /**
     * Adds a product to the user's shopping cart with default quantity of 1
     * @param userId the ID of the user
     * @param productId the ID of the product to add
     */
    void addProductToCart(int userId, int productId);

    /**
     * Adds a product to the user's shopping cart with specified quantity
     * @param userId the ID of the user
     * @param productId the ID of the product to add
     * @param quantity the quantity of the product to add
     */
    void addProductToCart(int userId, int productId, int quantity);

    /**
     * Updates the quantity of a specific product in the user's cart
     * @param userId the ID of the user
     * @param productId the ID of the product to update
     * @param quantity the new quantity
     */
    void updateProductQuantity(int userId, int productId, int quantity);

    /**
     * Removes a specific product from the user's cart
     * @param userId the ID of the user
     * @param productId the ID of the product to remove
     */
    void removeProductFromCart(int userId, int productId);

    /**
     * Clears all items from the user's shopping cart
     * @param userId the ID of the user
     */
    void clearCart(int userId);

    /**
     * Gets the total number of items in the user's cart
     * @param userId the ID of the user
     * @return total quantity of all items
     */
    int getCartItemCount(int userId);

    /**
     * Checks if a specific product exists in the user's cart
     * @param userId the ID of the user
     * @param productId the ID of the product to check
     * @return true if product exists in cart, false otherwise
     */
    boolean isProductInCart(int userId, int productId);

    /**
     * Gets a specific cart item by user ID and product ID
     * @param userId the ID of the user
     * @param productId the ID of the product
     * @return ShoppingCartItem if found, null otherwise
     */
    ShoppingCartItem getCartItem(int userId, int productId);
}
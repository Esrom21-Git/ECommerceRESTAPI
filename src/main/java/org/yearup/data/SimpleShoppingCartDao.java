//// ShoppingCartDao.java
//package org.yearup.data;
//
//import org.yearup.models.ShoppingCart;
//
//interface ShoppingCartDao {
//    ShoppingCart getByUserId(int userId);
//    void addItem(int userId, int productId, int quantity);
//    void updateItem(int userId, int productId, int quantity);
//    void removeItem(int userId, int productId);
//    void clearCart(int userId);
//}
//
//// SimpleShoppingCartDao.java
//package org.yearup.data;
//
//import org.yearup.models.ShoppingCart;
//import org.yearup.models.ShoppingCartItem;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SimpleShoppingCartDao implements ShoppingCartDao {
//
//    private Map<Integer, ShoppingCart> carts = new HashMap<>();
//
//    @Override
//    public ShoppingCart getByUserId(int userId) {
//        if (carts.get(userId) == null) {
//            carts.put(userId, new ShoppingCart());
//        }
//        return carts.get(userId);
//    }
//
//    @Override
//    public void addItem(int userId, int productId, int quantity) {
//        ShoppingCart cart = getByUserId(userId);
//        ShoppingCartItem item = cart.get(productId);
//
//        if (item != null) {
//            item.setQuantity(item.getQuantity() + quantity);
//        } else {
//            ShoppingCartItem newItem = new ShoppingCartItem();
//            newItem.setProductId(productId);
//            newItem.setQuantity(quantity);
//            cart.add(newItem);
//        }
//    }
//
//    @Override
//    public void updateItem(int userId, int productId, int quantity) {
//        ShoppingCart cart = getByUserId(userId);
//        ShoppingCartItem item = cart.get(productId);
//
//        if (item != null) {
//            if (quantity <= 0) {
//                cart.remove(productId);
//            } else {
//                item.setQuantity(quantity);
//            }
//        }
//    }
//
//    @Override
//    public void removeItem(int userId, int productId) {
//        ShoppingCart cart = getByUserId(userId);
//        cart.remove(productId);
//    }
//
//    @Override
//    public void clearCart(int userId) {
//        ShoppingCart cart = getByUserId(userId);
//        cart.clear();
//    }
//}
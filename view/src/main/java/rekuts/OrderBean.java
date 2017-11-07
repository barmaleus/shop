package rekuts;

import rekuts.domain.Order;
import rekuts.domain.Product;
import rekuts.ejb.OrdersManagerBean;
import rekuts.ejb.ProductsManagerBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class OrderBean implements Serializable {

    private Order order;
    private String name;
    private int quantity;

    @EJB
    private OrdersManagerBean ordersManagerBean;

    @EJB
    private ProductsManagerBean productsManagerBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void createOrder() {
        if(order == null) {
            order = ordersManagerBean.createOrder();
        }
    }

    public void createProduct() {
        productsManagerBean.createProduct(name, quantity);
    }

    public List<Product> getProducts() {
        return productsManagerBean.getProducts();
    }

    public void addProduct(Product product) {
        if(order == null) {
            return;
        }

        ordersManagerBean.addToOrder(product.getId(), order.getId(), 1);
    }

    public List<Product> getProductsInOrder() {
        if(order == null) {
            return  Collections.emptyList();
        }

        return ordersManagerBean.getProductsInOrder(order.getId());
    }
}

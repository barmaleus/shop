package rekuts.ejb;

import rekuts.domain.Product;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class ProductsManagerBean {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public Product createProduct(String name, int price) {
        Product product = new Product();
        product.setName(name);
        product.setQuantity(price);
        entityManager.persist(product);

        return product;
    }

    public List<Product> getProducts() {
        TypedQuery<Product> query = entityManager.createQuery("select c from Product c", Product.class);

        return query.getResultList();
    }
}

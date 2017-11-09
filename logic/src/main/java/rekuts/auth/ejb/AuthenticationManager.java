package rekuts.auth.ejb;

import org.apache.commons.lang3.StringUtils;
import rekuts.auth.domain.Credentials;
import rekuts.auth.domain.ShopUser;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AuthenticationManager {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public ShopUser.Role login(String email, String password) {
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return null;
        }

        Credentials credentials = entityManager.find(Credentials.class, email);
        if(credentials == null) {
            return null;
        }

       if(!password.equals(credentials.getPassword())) {
            return null;
       }

       ShopUser shopUser = credentials.getShopUser();
        if(shopUser == null) {
            return null;
        }

        return shopUser.getRole();
    }
}

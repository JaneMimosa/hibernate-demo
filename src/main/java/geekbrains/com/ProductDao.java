package geekbrains.com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {

    public static SessionFactory sessionFactory;

    public ProductDao() {
        init();
    }

    private static void init() {
        sessionFactory = new Configuration()
                .configure("config/ hibernate.cfg.xml")
                .buildSessionFactory();
    }

    private static void shutdown() {
        sessionFactory.close();
    }


    public Product findById(Long id) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product productFromBD = session.createQuery("SELECT p from Product p WHERE p.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.close();
            return productFromBD;
        }
    }

    public List<Product> findAll() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("FROM Product", Product.class)
                    .getResultList();
            session.close();
            return productList;
        }
    }

    public void deleteById(Long id) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product productFromBD = session.get(Product.class, id);
            session.remove(productFromBD);
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

}

package geekbrains.com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.saveOrUpdate(new Product("title", 100));
        System.out.println(productDao.findAll().toString());
    }
}

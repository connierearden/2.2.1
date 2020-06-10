package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public Car getCarByName(String name, int series) {
        return (Car) sessionFactory.getCurrentSession()
                .createQuery("from Car c where c.name = :name and c.series = :series")
                .setParameter("name", name)
                .setParameter("series", series)
                .getSingleResult();
    }

    @Override
    public User getUserByCar(Car car) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("from User u where u.car = :car")
                .setParameter("car", car)
                .getSingleResult();
    }
}

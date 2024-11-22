package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserServiceImp;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUserByCarDetails(String model, Integer series) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("FROM Car AS c JOIN FETCH c.user WHERE model =:model AND series =:series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult().getUser();
    }

}

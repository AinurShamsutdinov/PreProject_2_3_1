package web.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean updateUserById(long id, User user) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update("id", user);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            User user = getUserById(id);
            session.delete(user);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUsersList() {
        Session session =sessionFactory.openSession();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> userList = query.getResultList();
        return userList;
    }

}

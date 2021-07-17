package peaksoft.dao;

import org.hibernate.*;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery( "create table if not exists users " +
                "(   id  serial primary key,   " +
                "    name      varchar," +
                "    last_name varchar," +
                "    age       integer" +
                ");").executeUpdate();
        session.getTransaction().commit();
        session.close();
           System.out.println(" Created table");
       } catch (Exception e){
           System.out.println(e.getMessage());
       }


    }

    @Override
    public void dropUsersTable() {
        try {
            Session  session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP  TABLE  IF  EXISTS users ").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println(" Table deleted ");
        } catch (Exception e ){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void saveUser(  String name, String lastName, byte age) {
   
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User u = new User();
            u.setName(name);
            u.setLastName(lastName);
            u.setAge(age);
            session.save(u);
            session.getTransaction().commit();
            session.close();
            System.out.println(name + " added to the database ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
        @Override
        public void removeUserById ( long id){
            try {
                Session session = Util.getSessionFactory().openSession();
                session.beginTransaction();
                session.createSQLQuery("DELETE  FROM users WHERE id = ?").executeUpdate();
                session.getTransaction().commit();
                session.close();
                System.out.println( id + " deleted!!! ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        @Override
        public List<User> getAllUsers () {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<User> userList = session.createQuery("FROM User").list();
            session.getTransaction().commit();
            session.close();
            System.out.println("Finded " + userList.size() + "userList");
            return userList;
        } 

        @Override
        public void cleanUsersTable () {
            try {
                Session session = Util.getSessionFactory().openSession();
                session.beginTransaction();
                session.createSQLQuery("DELETE FROM  users").executeUpdate();
                session.getTransaction().commit();
                session.close();
                System.out.println(" clean table ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

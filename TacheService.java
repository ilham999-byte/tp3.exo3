

package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Tache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;

import java.util.List;

public class TacheService implements IDao<Tache> {

    @Override
    public boolean create(Tache o) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return false;
    }

    

    @Override
    public Tache getById(int id) {
        Session session = null;
        Tache t = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            t = (Tache) session.get(Tache.class, id);
            session.getTransaction().commit();
            return t;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return t;
    }

    @Override
    public List<Tache> getAll() {
        Session session = null;
        List<Tache> taches = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            taches = session.createQuery("from Tache").list();
            session.getTransaction().commit();
            return taches;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return taches;
    }

    public List<Tache> findTachesAvecPrixSuperieurA1000() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}

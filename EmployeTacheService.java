
package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.EmployeTache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;

import java.util.List;
import ma.projet.classes.Tache;
import org.hibernate.Query;

import java.util.Date;
public class EmployeTacheService implements IDao<EmployeTache> {

    @Override
    public boolean create(EmployeTache o) {
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
    public EmployeTache getById(int id) {
        Session session = null;
        EmployeTache et = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            et = (EmployeTache) session.get(EmployeTache.class, id);
            session.getTransaction().commit();
            return et;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return et;
    }

    @Override
    public List<EmployeTache> getAll() {
        Session session = null;
        List<EmployeTache> employeTaches = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            employeTaches = session.createQuery("from EmployeTache").list();
            session.getTransaction().commit();
            return employeTaches;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return employeTaches;
    }
   public List<Tache> findTachesWithPriceGreaterThan(double price) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Query<Tache> query = session.createQuery("FROM Tache WHERE prix > :price", Tache.class);
        query.setParameter("price", price);
        return query.getResultList();
    } catch (HibernateException e) {
        e.printStackTrace(); // Affiche la trace de l'exception
        System.out.println("Erreur dans findTachesWithPriceGreaterThan: " + e.getMessage());
    }
    return null; // Retourne null en cas d'erreur
}

public List<Tache> findTachesRealiseesEntreDeuxDates(Date dateDebut, Date dateFin) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Query<Tache> query = session.createQuery("FROM Tache WHERE dateFin BETWEEN :dateDebut AND :dateFin", Tache.class);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        return query.getResultList();
    } catch (HibernateException e) {
        e.printStackTrace(); // Affiche la trace de l'exception
        System.out.println("Erreur dans findTachesRealiseesEntreDeuxDates: " + e.getMessage());
    }
    return null; // Retourne null en cas d'erreur
}

}

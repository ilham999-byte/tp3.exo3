
package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Employe;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;

import java.util.List;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe o) {
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
    public Employe getById(int id) {
        Session session = null;
        Employe e = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (Employe) session.get(Employe.class, id);
            session.getTransaction().commit();
            return e;
        } catch (HibernateException e1) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return e;
    }

    @Override
    public List<Employe> getAll() {
        Session session = null;
        List<Employe> employes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            employes = session.createQuery("from Employe").list();
            session.getTransaction().commit();
            return employes;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return employes;
    }

    // Méthode pour afficher la liste des tâches réalisées par un employé
    public List<Tache> getTachesRealisees(Employe employe) {
        Session session = null;
        List<Tache> taches = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            taches = session.createQuery("SELECT t FROM Tache t JOIN EmployeTache et ON t.id = et.tache.id WHERE et.employe.id = :id")
                    .setParameter("id", employe.getId())
                    .list();
            session.getTransaction().commit();
            return taches;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return taches;
    }

    // Méthode pour afficher la liste des projets gérés par un employé
    public List<Projet> getProjetsGerés(Employe employe) {
        Session session = null;
        List<Projet> projets = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            projets = session.createQuery("SELECT p FROM Projet p WHERE p.chefDeProjet.id = :id")
                    .setParameter("id", employe.getId())
                    .list();
            session.getTransaction().commit();
            return projets;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
        }
        return projets;
    }

    public void afficherTachesRealiseesParEmploye(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void afficherProjetsGerésParEmploye(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}





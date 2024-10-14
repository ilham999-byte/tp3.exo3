
package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();  // Ajout d'une trace pour déboguer
        }
        return false;
    }

    public boolean delete(Projet o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();  // Ajout d'une trace pour déboguer
        }
        return false;
    }

    public boolean update(Projet o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();  // Ajout d'une trace pour déboguer
        }
        return false;
    }

    @Override
    public Projet getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Projet.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();  // Ajout d'une trace pour déboguer
        }
        return null;
    }

    @Override
    public List<Projet> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Projet", Projet.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();  // Ajout d'une trace pour déboguer
        }
        return null;
    }

    // Méthode pour afficher les tâches planifiées pour un projet
    public List<Tache> findTachesPlanifieesPourProjet(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tache WHERE projet.id = :projetId", Tache.class)
                    .setParameter("projetId", projetId)
                    .list();
        }
    }

    // Méthode pour afficher les tâches réalisées dans un projet
    public void afficherTachesRealiseesDansProjet(int projetId) {
        Projet projet = getById(projetId);
        System.out.println("Projet : " + projetId + " - Nom : " + projet.getNom() + " - Date début : " + projet.getDateDebut());
        System.out.println("Liste des tâches :");
        System.out.println("Num\tNom\tDate Début Réelle\tDate Fin Réelle");
        List<Tache> taches = findTachesRealiseesPourProjet(projetId);
        for (Tache tache : taches) {
            System.out.println(tache.getId() + "\t" + tache.getNom() + "\t" + tache.getDateDebut() + "\t" + tache.getDateFin());
        }
    }

    // Méthode auxiliaire pour récupérer les tâches réalisées pour un projet
    private List<Tache> findTachesRealiseesPourProjet(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT t FROM Tache t JOIN EmployeTache et ON t.id = et.tache.id WHERE et.projet.id = :projetId AND t.estRealisee = true", Tache.class)
                    .setParameter("projetId", projetId)
                    .list();
        }
    }

    public void afficherTachesRealiseesDansProjet(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
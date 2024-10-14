/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.test;

import java.util.List;
import ma.projet.classes.Tache;
import ma.projet.service.EmployeService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;

/**
 *
 * @author hp
 */
public class Test {
  
    public static void main(String[] args) {
        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();

        // Tester les méthodes de EmployeService
        employeService.afficherTachesRealiseesParEmploye(1);
        employeService.afficherProjetsGerésParEmploye(1);

        // Tester les méthodes de ProjetService
        projetService.afficherTachesRealiseesDansProjet(4);

        // Tester les méthodes de TacheService
        List<Tache> taches = tacheService.findTachesAvecPrixSuperieurA1000();
        for (Tache tache : taches) {
            System.out.println(tache.getNom());
        }
    }
}

    


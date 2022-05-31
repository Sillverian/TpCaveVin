package fr.formation.service.bouteille;

import fr.formation.entity.Bouteille;

import java.util.List;
import java.util.Optional;

public interface BouteilleService {

    void ajouterBouteille(Bouteille b) throws BouteilleException;

    void modifierBouteille(Bouteille b);

    void supprimerBouteille(int id);

    List<Bouteille> listerBouteilles();

    List<Bouteille> listerBouteillesParNom(String nom);

    Optional<Bouteille> infoBouteille(int id);

    List<Bouteille> listerBouteillesTrieesParNomAsc();
    List<Bouteille> listerBouteillesTrieesParNomDesc();

    List<Bouteille> listerBouteillesTrieesParPetillantAsc();
    List<Bouteille> listerBouteillesTrieesParpetillantDesc();

    List<Bouteille> listerBouteillesTrieesParMillesimeAsc();
    List<Bouteille> listerBouteillesTrieesParMillesimeDesc();

    List<Bouteille> listerBouteillesTrieesParQuantiteAsc();
    List<Bouteille> listerBouteillesTrieesParQuantiteDesc();

    List<Bouteille> listerBouteillesTrieesParCouleurAsc();
    List<Bouteille> listerBouteillesTrieesParCouleurDesc();

    List<Bouteille> listerBouteillesTrieesParRegionAsc();
    List<Bouteille> listerBouteillesTrieesParRegionDesc();
}

package fr.formation.service.couleur;

import fr.formation.entity.Couleur;

import java.util.List;

public interface CouleurService {
    void ajouterCouleur(Couleur c) throws CouleurException;

    void modifierCouleur(Couleur c);

    void supprimerCouleur(int id);

    List<Couleur> listerCouleur();

    List<Couleur> listerCouleurParNom(String nom);

    List<Couleur> listerCouleurTrieesParIdAsc();
    List<Couleur> listerCouleurTrieesParIdDesc();

    List<Couleur> listerCouleurTrieesParNomAsc();
    List<Couleur> listerCouleurTrieesParNomDesc();
}

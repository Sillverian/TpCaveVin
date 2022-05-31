package fr.formation.service.couleur;

import fr.formation.entity.Couleur;
import fr.formation.repository.CouleurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouleurServImpl implements CouleurService{

    @Autowired
    CouleurDAO cDao;

    @Override
    public void ajouterCouleur(Couleur c) throws CouleurException {
        if (listerCouleurParNom(c.getNom()).isEmpty()) {
            cDao.save(c);
        }
        else {
            throw new CouleurException("Il ne peut pas y avoir deux nom de couleurs identiques !");
        }
    }

    @Override
    public void modifierCouleur(Couleur c) {
        cDao.save(c);
    }

    @Override
    public void supprimerCouleur(int id) {
        cDao.deleteById(id);
    }

    @Override
    public List<Couleur> listerCouleur() {
        return cDao.findAll();
    }

    @Override
    public List<Couleur> listerCouleurParNom(String nom) {
        return cDao.findByNom(nom);
    }

    @Override
    public List<Couleur> listerCouleurTrieesParIdAsc() {
        return cDao.findByOrderByIdAsc();
    }

    @Override
    public List<Couleur> listerCouleurTrieesParIdDesc() {
        return cDao.findByOrderByIdDesc();
    }

    @Override
    public List<Couleur> listerCouleurTrieesParNomAsc() {
        return cDao.findByOrderByNomAsc();
    }

    @Override
    public List<Couleur> listerCouleurTrieesParNomDesc() {
        return cDao.findByOrderByNomDesc();
    }
}

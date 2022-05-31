package fr.formation.service.bouteille;

import fr.formation.entity.Bouteille;
import fr.formation.repository.BouteilleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BouteilleServImpl implements BouteilleService {

    @Autowired
    BouteilleDAO bDao;

    @Override
    public void ajouterBouteille(Bouteille b) throws BouteilleException {
        if (listerBouteillesParNom(b.getNom()).isEmpty()){
            bDao.save(b);
        }
        else {
            throw new BouteilleException("On ne peut pas insérer deux bouteilles identiques");
        }

    }

    @Override
    public void modifierBouteille(Bouteille b) {
        bDao.save(b);
    }

    @Override
    public void supprimerBouteille(int id) {
        bDao.deleteById(id);
    }

    @Override
    public List<Bouteille> listerBouteilles() {
        return bDao.findAll();
    }

    @Override
    public List<Bouteille> listerBouteillesParNom(String nom) {
        return bDao.findByNom(nom);
    }

    @Override
    public Optional<Bouteille> infoBouteille(int id) {
        return bDao.findById(id);
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParNomAsc() {
        return bDao.findByOrderByNomAsc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParNomDesc() {
        return bDao.findByOrderByNomDesc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParPetillantAsc() {
        return bDao.findByOrderByPetillantAsc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParpetillantDesc() {
        return bDao.findByOrderByPetillantDesc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParMillesimeAsc() {
        return bDao.findByOrderByMillesimeAsc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParMillesimeDesc() {
        return bDao.findByOrderByMillesimeDesc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParQuantiteAsc() {
        return bDao.findByOrderByQuantiteAsc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParQuantiteDesc() {
        return bDao.findByOrderByQuantiteDesc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParCouleurAsc() {
        return bDao.findByOrderByCouleurNomAsc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParCouleurDesc() {
        return bDao.findByOrderByCouleurNomDesc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParRegionAsc() {
        return bDao.findByOrderByRegionNomAsc();
    }

    @Override
    public List<Bouteille> listerBouteillesTrieesParRegionDesc() {
        return bDao.findByOrderByRegionNomDesc();
    }
}

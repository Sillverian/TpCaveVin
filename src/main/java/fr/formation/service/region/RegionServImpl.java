package fr.formation.service.region;

import fr.formation.entity.Region;
import fr.formation.repository.RegionDAO;
import fr.formation.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServImpl implements RegionService {

    @Autowired
    RegionDAO rDao;

    @Override
    public void ajouterRegion(Region r) throws RegionException {
        if (listerRegionsParNom(r.getNom()).isEmpty()) {
            rDao.save(r);
        } else {
            throw new RegionException("Il ne peut pas y avoir deux regions identiques !");
        }
    }

    @Override
    public void modifierRegion(Region r) {
        rDao.save(r);
    }

    @Override
    public void supprimerRegion(int id) {
        rDao.deleteById(id);
    }

    @Override
    public List<Region> listerRegions() {
        return rDao.findAll();
    }

    @Override
    public List<Region> listerRegionsParNom(String nom) {
        return rDao.findByNom(nom);
    }

    @Override
    public List<Region> listerRegionsTrieesParIdAsc() {
        return rDao.findByOrderByIdAsc();
    }

    @Override
    public List<Region> listerRegionsTrieesParIdDesc() {
        return rDao.findByOrderByIdDesc();
    }

    @Override
    public List<Region> listerRegionsTrieesParNomAsc() {
        return rDao.findByOrderByNomAsc();
    }

    @Override
    public List<Region> listerRegionsTrieesParNomDesc() {
        return rDao.findByOrderByNomDesc();
    }
}

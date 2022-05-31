package fr.formation.service.region;

import fr.formation.entity.Region;


import java.util.List;

public interface RegionService {

    void ajouterRegion(Region r) throws RegionException;

    void modifierRegion(Region r);

    void supprimerRegion(int id);

    List<Region> listerRegions();

    List<Region> listerRegionsParNom(String nom);

    List<Region> listerRegionsTrieesParIdAsc();
    List<Region> listerRegionsTrieesParIdDesc();

    List<Region> listerRegionsTrieesParNomAsc();
    List<Region> listerRegionsTrieesParNomDesc();
}

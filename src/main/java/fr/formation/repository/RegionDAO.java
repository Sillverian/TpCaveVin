package fr.formation.repository;

import fr.formation.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionDAO extends JpaRepository<Region,Integer> {
    List<Region> findByNom(String nom);

    List<Region> findByOrderByIdAsc();
    List<Region> findByOrderByIdDesc();

    List<Region> findByOrderByNomAsc();
    List<Region> findByOrderByNomDesc();
}

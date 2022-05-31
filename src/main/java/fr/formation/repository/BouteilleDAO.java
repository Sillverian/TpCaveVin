package fr.formation.repository;

import fr.formation.entity.Bouteille;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BouteilleDAO extends JpaRepository<Bouteille, Integer> {

    List<Bouteille> findByNom(String nom);

    List<Bouteille> findByOrderByNomAsc();
    List<Bouteille> findByOrderByNomDesc();

    List<Bouteille> findByOrderByPetillantAsc();
    List<Bouteille> findByOrderByPetillantDesc();

    List<Bouteille> findByOrderByMillesimeAsc();
    List<Bouteille> findByOrderByMillesimeDesc();

    List<Bouteille> findByOrderByQuantiteAsc();
    List<Bouteille> findByOrderByQuantiteDesc();

    List<Bouteille> findByOrderByCouleurNomAsc();
    List<Bouteille> findByOrderByCouleurNomDesc();

    List<Bouteille> findByOrderByRegionNomAsc();
    List<Bouteille> findByOrderByRegionNomDesc();
}

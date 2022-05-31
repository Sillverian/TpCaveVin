package fr.formation.repository;

import fr.formation.entity.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouleurDAO extends JpaRepository<Couleur, Integer> {

    List<Couleur> findByNom(String nom);

    List<Couleur> findByOrderByIdAsc();
    List<Couleur> findByOrderByIdDesc();

    List<Couleur> findByOrderByNomAsc();
    List<Couleur> findByOrderByNomDesc();
}

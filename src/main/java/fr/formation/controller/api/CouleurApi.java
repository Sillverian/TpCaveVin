package fr.formation.controller.api;

import fr.formation.entity.Couleur;
import fr.formation.service.couleur.CouleurException;
import fr.formation.service.couleur.CouleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couleur")
public class CouleurApi {

    @Autowired
    CouleurService cServ;

    @GetMapping
    public ResponseEntity<List<Couleur>> getCouleurs() {
        List<Couleur> lCoul = cServ.listerCouleur();

        return new ResponseEntity<List<Couleur>>(lCoul, HttpStatus.OK);
    }

    @GetMapping("/triNomAsc")
    public ResponseEntity<List<Couleur>> getCouleursTriNomAsc(){
        List<Couleur> lCoulTriNomAsc = cServ.listerCouleurTrieesParNomAsc();

        return new ResponseEntity<List<Couleur>>(lCoulTriNomAsc, HttpStatus.OK);
    }

    @GetMapping("/triNomDesc")
    public ResponseEntity<List<Couleur>> getCouleursTriNomDesc(){
        List<Couleur> lCoulTriNomDesc = cServ.listerCouleurTrieesParNomDesc();

        return new ResponseEntity<List<Couleur>>(lCoulTriNomDesc, HttpStatus.OK);
    }
    @GetMapping("/triIdAsc")
    public ResponseEntity<List<Couleur>> getCouleursTriIdAsc(){
        List<Couleur> lCoulTriIdAsc = cServ.listerCouleurTrieesParIdAsc();

        return new ResponseEntity<List<Couleur>>(lCoulTriIdAsc, HttpStatus.OK);
    }
    @GetMapping("/triIdDesc")
    public ResponseEntity<List<Couleur>> getCouleursTriIdDesc(){
        List<Couleur> lCoulTriNomDesc = cServ.listerCouleurTrieesParIdDesc();

        return new ResponseEntity<List<Couleur>>(lCoulTriNomDesc, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> postCouleur(@RequestBody Couleur c) {
        try {
            cServ.ajouterCouleur(c);
        } catch (CouleurException e) {
            System.out.println("Erreur trouvé");

            HttpHeaders headers = new HttpHeaders();
            headers.set("coulDupli", "Il ne peut pas y avoir deux nom de couleurs identiques !");

            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void putCouleur(@PathVariable("id") Integer id, @RequestBody Couleur c) {
//        System.out.println("id = " + id);
//        System.out.println("couleur = " + c);
        c.setId(id);
        cServ.modifierCouleur(c);

    }

    @DeleteMapping("/{id}")
    public void deleteCouleur(@PathVariable("id") Integer id) {
        cServ.supprimerCouleur(id);
    }
}


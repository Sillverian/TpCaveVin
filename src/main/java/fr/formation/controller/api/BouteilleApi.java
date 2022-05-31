package fr.formation.controller.api;

import fr.formation.entity.Bouteille;
import fr.formation.service.bouteille.BouteilleException;
import fr.formation.service.bouteille.BouteilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bouteille")
public class BouteilleApi {

    @Autowired
    BouteilleService bServ;

    @GetMapping
    public ResponseEntity<List<Bouteille>> getBouteilles() {
        List<Bouteille> lBout = bServ.listerBouteilles();

        return new ResponseEntity<List<Bouteille>>(lBout, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bouteille>> getBouteille(@PathVariable("id") Integer id) {
        Optional<Bouteille> bout = bServ.infoBouteille(id);

        return new ResponseEntity<Optional<Bouteille>>(bout, HttpStatus.OK);
    }

    // NOM
    @GetMapping("/triNomAsc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriNomAsc(){
        List<Bouteille> lBoutTriNomAsc = bServ.listerBouteillesTrieesParNomAsc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriNomAsc, HttpStatus.OK);
    }

    @GetMapping("/triNomDesc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriNomDesc(){
        List<Bouteille> lBoutTriNomDesc = bServ.listerBouteillesTrieesParNomDesc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriNomDesc, HttpStatus.OK);
    }

    // PETTILANT
    @GetMapping("/triPetillantAsc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriPetillantAsc(){
        List<Bouteille> lBoutTriPetillantAsc = bServ.listerBouteillesTrieesParPetillantAsc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriPetillantAsc, HttpStatus.OK);
    }

    @GetMapping("/triPetillantDesc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriPetillantDesc(){
        List<Bouteille> lBoutTriPetillantDesc = bServ.listerBouteillesTrieesParpetillantDesc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriPetillantDesc, HttpStatus.OK);
    }

    // MILLESIME
    @GetMapping("/triMillesimeAsc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriMillesimeAsc(){
        List<Bouteille> lBoutTriMillesimeAsc = bServ.listerBouteillesTrieesParMillesimeAsc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriMillesimeAsc, HttpStatus.OK);
    }

    @GetMapping("/triMillesimeDesc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriMillesimeDesc(){
        List<Bouteille> lBoutTriMillesimeDesc = bServ.listerBouteillesTrieesParMillesimeDesc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriMillesimeDesc, HttpStatus.OK);
    }

    // QUANTITE
    @GetMapping("/triQteAsc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriQteAsc(){
        List<Bouteille> lBoutTriQteAsc = bServ.listerBouteillesTrieesParQuantiteAsc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriQteAsc, HttpStatus.OK);
    }

    @GetMapping("/triQteDesc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriQteDesc(){
        List<Bouteille> lBoutTriQteDesc = bServ.listerBouteillesTrieesParQuantiteDesc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriQteDesc, HttpStatus.OK);
    }

    // COULEUR
    @GetMapping("/triCoulAsc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriCoulAsc(){
        List<Bouteille> lBoutTriCoulAsc = bServ.listerBouteillesTrieesParCouleurAsc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriCoulAsc, HttpStatus.OK);
    }

    @GetMapping("/triCoulDesc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriCoulDesc(){
        List<Bouteille> lBoutTriCoulDesc = bServ.listerBouteillesTrieesParCouleurDesc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriCoulDesc, HttpStatus.OK);
    }

    // REGION
    @GetMapping("/triRegAsc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriRegionAsc(){
        List<Bouteille> lBoutTriRegAsc = bServ.listerBouteillesTrieesParRegionAsc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriRegAsc, HttpStatus.OK);
    }

    @GetMapping("/triRegDesc")
    public ResponseEntity<List<Bouteille>> getBouteillesTriRegionDesc(){
        List<Bouteille> lBoutTriRegDesc = bServ.listerBouteillesTrieesParRegionDesc();

        return new ResponseEntity<List<Bouteille>>(lBoutTriRegDesc, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> postBouteille(@RequestBody Bouteille b){
        try {
            bServ.ajouterBouteille(b);
        } catch (BouteilleException e) {
            System.out.println("Erreur trouvé");

            HttpHeaders headers = new HttpHeaders();
            headers.set("boutDupli", "Il ne peut pas y avoir deux nom de bouteilles identiques !");

            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void putCouleur(@PathVariable("id") Integer id, @RequestBody Bouteille b){
        b.setId(id);
        bServ.modifierBouteille(b);
    }

    @DeleteMapping("/{id}")
    public void deleteCouleur(@PathVariable("id") Integer id){
        bServ.supprimerBouteille(id);
    }
}

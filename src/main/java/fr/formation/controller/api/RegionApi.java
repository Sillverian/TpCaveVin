package fr.formation.controller.api;

import fr.formation.entity.Couleur;
import fr.formation.entity.Region;
import fr.formation.repository.RegionDAO;
import fr.formation.service.region.RegionException;
import fr.formation.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionApi {

    @Autowired
    RegionService rServ;

    @GetMapping
    public ResponseEntity<List<Region>> getRegions(){
        List<Region> lReg = rServ.listerRegions();

        return new ResponseEntity<List<Region>>(lReg, HttpStatus.OK);
    }

    @GetMapping("/triNomAsc")
    public ResponseEntity<List<Region>> getRegionsTriNomAsc(){
        List<Region> lRegTriNomAsc = rServ.listerRegionsTrieesParNomAsc();

        return new ResponseEntity<List<Region>>(lRegTriNomAsc, HttpStatus.OK);
    }

    @GetMapping("/triNomDesc")
    public ResponseEntity<List<Region>> getRegionsTriNomDesc(){
        List<Region> lRegTriNomDesc = rServ.listerRegionsTrieesParNomDesc();

        return new ResponseEntity<List<Region>>(lRegTriNomDesc, HttpStatus.OK);
    }
    @GetMapping("/triIdAsc")
    public ResponseEntity<List<Region>> getRegionsTriIdAsc(){
        List<Region> lRegTriIdAsc = rServ.listerRegionsTrieesParIdAsc();

        return new ResponseEntity<List<Region>>(lRegTriIdAsc, HttpStatus.OK);
    }
    @GetMapping("/triIdDesc")
    public ResponseEntity<List<Region>> getRegionsTriIdDesc(){
        List<Region> lRegTriNomDesc = rServ.listerRegionsTrieesParIdDesc();

        return new ResponseEntity<List<Region>>(lRegTriNomDesc, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> postCouleur(@RequestBody Region r){
        try {
            rServ.ajouterRegion(r);
        } catch (RegionException e) {
            System.out.println("Erreur trouvé");

            HttpHeaders headers = new HttpHeaders();
            headers.set("regDupli", "Il ne peut pas y avoir deux nom de régions identiques !");

            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void putCouleur(@PathVariable("id") Integer id, @RequestBody Region r){
        r.setId(id);
        rServ.modifierRegion(r);
    }

    @DeleteMapping("/{id}")
    public void deleteCouleur(@PathVariable("id") Integer id){
        rServ.supprimerRegion(id);
    }
}

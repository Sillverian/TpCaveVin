package fr.formation.entity;

import javax.persistence.*;

@Entity
public class Bouteille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private boolean petillant;

    private String millesime;

    private int quantite;

    @ManyToOne
    private Couleur couleur;

    @ManyToOne
    private Region region;


    public Bouteille() {
    }

    public Bouteille(String nom, boolean petillant, String millesime, int quantite, Couleur couleur, Region region) {
        this.nom = nom;
        this.petillant = petillant;
        this.millesime = millesime;
        this.quantite = quantite;
        this.couleur = couleur;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isPetillant() {
        return petillant;
    }

    public void setPetillant(boolean petillant) {
        this.petillant = petillant;
    }

    public String getMillesime() {
        return millesime;
    }

    public void setMillesime(String millesime) {
        this.millesime = millesime;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Bouteille{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", petillant=" + petillant +
                ", millesime='" + millesime + '\'' +
                ", quantite=" + quantite +
                ", couleur=" + couleur +
                ", region=" + region +
                '}';
    }
}

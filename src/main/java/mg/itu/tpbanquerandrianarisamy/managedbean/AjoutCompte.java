/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerandrianarisamy.managedbean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import mg.itu.tpbanquerandrianarisamy.ejb.GestionnaireCompte;
import mg.itu.tpbanquerandrianarisamy.entities.CompteBancaire;
import mg.itu.tpbanquerandrianarisamy.util.Util;

/**
 *
 * @author jolivot
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    private String nomProprietaire;

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    private int solde;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    /**
     * Creates a new instance of AjoutCompte
     */
    
    public String enregistrerAjout(){
        CompteBancaire c = new CompteBancaire();
        c.setNom(nomProprietaire);
        c.setSolde(solde);
        gestionnaireCompte.creerCompte(c);
        Util.addFlashInfoMessage("compte de "+nomProprietaire +" créé avec succès");
        return "listeComptes?faces-redirect=true";
    }
    
    public AjoutCompte() {
    }
    
}

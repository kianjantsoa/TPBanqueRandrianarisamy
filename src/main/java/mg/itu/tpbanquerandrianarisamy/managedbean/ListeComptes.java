/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerandrianarisamy.managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import mg.itu.tpbanquerandrianarisamy.ejb.GestionnaireCompte;
import mg.itu.tpbanquerandrianarisamy.entities.CompteBancaire;
import mg.itu.tpbanquerandrianarisamy.util.Util;

/**
 *
 * @author jolivot
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    private List<CompteBancaire> liste;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    
    public List<CompteBancaire> getAllComptes(){
        if(liste == null){
            liste = gestionnaireCompte.getAllComptes();
        }
        return liste;
    }
    
    public String supprimerCompte(CompteBancaire compte){
        gestionnaireCompte.supprimerCompte(compte);
        Util.addFlashInfoMessage("Le compte de "+ compte.getNom() +" a eté supprimer avec succes");
        return "listeComptes?faces-redirect=true";
    }
    
}

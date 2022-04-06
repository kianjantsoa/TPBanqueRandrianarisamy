/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerandrianarisamy.managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import mg.itu.tpbanquerandrianarisamy.ejb.GestionnaireCompte;
import mg.itu.tpbanquerandrianarisamy.entities.CompteBancaire;
import mg.itu.tpbanquerandrianarisamy.util.Util;

/**
 *
 * @author jolivot
 */
@Named(value = "modificationNom")
@ViewScoped
public class ModificationNom implements Serializable {
    private long idCompte;
    private CompteBancaire compteBancaire ;

    @EJB
    GestionnaireCompte gestionnaireCompte;

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }
    public void loadCompte(){
        compteBancaire = gestionnaireCompte.getById(idCompte); 
    }
    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }
    
    public String enregistrer(){
        gestionnaireCompte.update(compteBancaire);
        Util.addFlashInfoMessage("Compte modifié avec succès");
        return "listeComptes?faces-redirect=true";
    }
    
    /**
     * Creates a new instance of modificationCompte
     */
    public ModificationNom() {
    }
    
}

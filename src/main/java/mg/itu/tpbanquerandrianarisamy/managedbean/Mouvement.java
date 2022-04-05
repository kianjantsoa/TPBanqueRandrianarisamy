/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerandrianarisamy.managedbean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import mg.itu.tpbanquerandrianarisamy.ejb.GestionnaireCompte;
import mg.itu.tpbanquerandrianarisamy.entities.CompteBancaire;
import mg.itu.tpbanquerandrianarisamy.util.Util;

/**
 *
 * @author jolivot
 */
@Named(value = "mouvement")
@ViewScoped
public class Mouvement implements Serializable{
    private long idCompte;
    private CompteBancaire compteCourant;
    private int solde;
    private String typeMouvement;

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    /**
     * Creates a new instance of Mouvement
     */
    
    public void loadCompte(){
        compteCourant = gestionnaireCompte.getById(idCompte);
    }
    
    public String effectuerMouvement(){
         if(typeMouvement.equals("retrait")){
            gestionnaireCompte.retrait(compteCourant, solde);
         }
         else{
            gestionnaireCompte.versement(compteCourant, solde);
         }
         Util.addFlashInfoMessage(typeMouvement+" correctement effectué entre ");
      
        return "listeComptes?faces-redirect=true";
    }
    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        UIInput composantTypeMouvement = (UIInput)composant.findComponent("typeMouvement");
        // Sans entrer dans les détails, il faut parfois utiliser
        // getSubmittedValue() à la place de getLocalValue.
        // typeMouvement n'est pas encore mis tant que la validation n'est pas finie.
        String valeurTypeMouvement = (String)composantTypeMouvement.getLocalValue();
        if (valeurTypeMouvement.equals("retrait")) {
          int retrait = (int) valeur;
          if (compteCourant.getSolde() < retrait) {
            FacesMessage message
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                         "Le retrait doit être inférieur au solde du compte",
                                         "Le retrait doit être inférieur au solde du compte");
            throw new ValidatorException(message);
          }
        }
     }
    
    public Mouvement() {
    }
    public CompteBancaire getCompteCourant() {
        return compteCourant;
    }

    public void setCompteCourant(CompteBancaire compteCourant) {
        this.compteCourant = compteCourant;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }
}

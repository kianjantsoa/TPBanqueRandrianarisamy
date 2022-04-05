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
@Named(value = "transfert")
@RequestScoped
public class Transfert {
    private long idCompteSource;
    private long idCompteDestinataire;
    private int solde;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;

    public long getIdCompteSource() {
        return idCompteSource;
    }

    public void setIdCompteSource(long idCompteSource) {
        this.idCompteSource = idCompteSource;
    }

    public long getIdCompteDestinataire() {
        return idCompteDestinataire;
    }

    public void setIdCompteDestinataire(long idCompteDestinataire) {
        this.idCompteDestinataire = idCompteDestinataire;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }
    
    public String enregistrerTransfert(){
        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.getById(idCompteSource);
        if(source == null){
            Util.messageErreur("Aucun compte avec cet Id!","Aucun compte avec cet Id!","form:source");
            erreur = true;
        }
        else{
            if(source.getSolde() < solde){
                Util.messageErreur("Solde insuffisant!","Solde insuffisant!","form:source");
            erreur = true;
            }
        }
        
        CompteBancaire destinataire = gestionnaireCompte.getById(idCompteDestinataire);
        if(destinataire == null){
            Util.messageErreur("Aucun compte avec cet Id!","Aucun compte avec cet Id!","form:destinataire");
            erreur = true;
        }
        
        if(erreur){
            return null;
        }
        gestionnaireCompte.transferer(source, destinataire, solde);
        Util.addFlashInfoMessage("Transfert correctement effectué entre "+source.getNom()+" et "+destinataire.getNom()+" pour le montant de "+solde);
        return "listeComptes.xhtml?faces-redirect=true";
    }
}

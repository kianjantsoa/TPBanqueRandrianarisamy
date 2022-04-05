/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package mg.itu.tpbanquerandrianarisamy.ejb;

import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mg.itu.tpbanquerandrianarisamy.entities.CompteBancaire;

/**
 *
 * @author jolivot
 */
@DataSourceDefinition(
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              // nom et
    password="admin", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class GestionnaireCompte {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    
    public void creerCompte(CompteBancaire c){
        em.persist(c);
    }
    
    public List<CompteBancaire> getAllComptes(){
       String s = "SELECT c FROM CompteBancaire c";
       TypedQuery<CompteBancaire> query = em.createQuery(s, CompteBancaire.class);
       List<CompteBancaire> liste = query.getResultList();
       return liste;
    }
    
    public long nbComptes() {
        TypedQuery<Long> query
            = em.createQuery("select count(c) from CompteBancaire c", Long.class);
        return query.getSingleResult();
    }
    
    public void transferer(CompteBancaire source , CompteBancaire destinataire , int solde){
        try{
            source.retirer(solde);
            destinataire.deposer(solde);
            update(source);
            update(destinataire);
        }
        catch(Exception ex){
            
        }
    }
    
    public void retrait(CompteBancaire compte , int solde){
        compte.retirer(solde);
        update(compte);
    }
    
    public void versement(CompteBancaire compte ,int solde){
        compte.deposer(solde);
        update(compte);
    }
    
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
    
    public CompteBancaire getById(long id){
        return em.find(CompteBancaire.class, id);
    }
}

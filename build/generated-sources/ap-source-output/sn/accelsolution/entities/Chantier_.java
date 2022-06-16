package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Brouillard;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Mouvementdebourse;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Chantier.class)
public class Chantier_ { 

    public static volatile SingularAttribute<Chantier, String> etatchantier;
    public static volatile SingularAttribute<Chantier, String> dateCreation;
    public static volatile ListAttribute<Chantier, Prestataire> prestataireList;
    public static volatile ListAttribute<Chantier, Commande> commandeList;
    public static volatile ListAttribute<Chantier, Acompte> acompteList;
    public static volatile SingularAttribute<Chantier, Marche> idMarche;
    public static volatile SingularAttribute<Chantier, Prestataire> idPrestataire;
    public static volatile SingularAttribute<Chantier, Utilisateur> idUtilisateur;
    public static volatile ListAttribute<Chantier, Brouillard> brouillardList;
    public static volatile ListAttribute<Chantier, Management> managementList;
    public static volatile ListAttribute<Chantier, Sortiestock> sortiestockList;
    public static volatile ListAttribute<Chantier, Operation> operationList;
    public static volatile SingularAttribute<Chantier, Integer> idChantier;
    public static volatile ListAttribute<Chantier, Mouvementdebourse> mouvementdebourseList;
    public static volatile ListAttribute<Chantier, Expressionbesoin> expressionbesoinList;
    public static volatile SingularAttribute<Chantier, String> siteChantier;
    public static volatile ListAttribute<Chantier, Livraison> livraisonList;
    public static volatile ListAttribute<Chantier, Detaillechantier> detaillechantierList;

}
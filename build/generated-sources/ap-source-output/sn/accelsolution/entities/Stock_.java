package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Unitemesure;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Stock.class)
public class Stock_ { 

    public static volatile SingularAttribute<Stock, Integer> qtSeuille;
    public static volatile SingularAttribute<Stock, String> dateEntreStock;
    public static volatile SingularAttribute<Stock, Integer> qt;
    public static volatile SingularAttribute<Stock, String> pu;
    public static volatile SingularAttribute<Stock, Unitemesure> idUnitemesure;
    public static volatile SingularAttribute<Stock, Integer> qtStock;
    public static volatile SingularAttribute<Stock, Utilisateur> idUtilisateur;
    public static volatile ListAttribute<Stock, Sortiestock> sortiestockList;
    public static volatile SingularAttribute<Stock, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Stock, Entrepot> idEntrepot;
    public static volatile SingularAttribute<Stock, Marchandise> idMarchandise;
    public static volatile SingularAttribute<Stock, String> puStock;
    public static volatile SingularAttribute<Stock, String> dateLastAppro;
    public static volatile SingularAttribute<Stock, Integer> idSotk;
    public static volatile SingularAttribute<Stock, String> designation;
    public static volatile SingularAttribute<Stock, String> commentaire;
    public static volatile SingularAttribute<Stock, Commande> idCommande;

}
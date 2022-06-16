package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.DetailleLivraison;
import sn.accelsolution.entities.Fournisseur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Livraison.class)
public class Livraison_ { 

    public static volatile SingularAttribute<Livraison, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Livraison, Chantier> idChantier;
    public static volatile SingularAttribute<Livraison, Integer> qtRestante;
    public static volatile SingularAttribute<Livraison, String> pu;
    public static volatile SingularAttribute<Livraison, String> dateLivraison;
    public static volatile ListAttribute<Livraison, DetailleLivraison> detailleLivraisonList;
    public static volatile SingularAttribute<Livraison, String> designation;
    public static volatile SingularAttribute<Livraison, Integer> qtLivre;
    public static volatile SingularAttribute<Livraison, Integer> idLivraison;
    public static volatile SingularAttribute<Livraison, Commande> idCommande;

}
package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Fournisseur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Historiquelivraison.class)
public class Historiquelivraison_ { 

    public static volatile SingularAttribute<Historiquelivraison, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Historiquelivraison, Entrepot> idEntrepot;
    public static volatile SingularAttribute<Historiquelivraison, Integer> idHistoLivraison;
    public static volatile SingularAttribute<Historiquelivraison, Integer> qtRestante;
    public static volatile SingularAttribute<Historiquelivraison, String> pu;
    public static volatile SingularAttribute<Historiquelivraison, String> dateLivraison;
    public static volatile SingularAttribute<Historiquelivraison, Integer> qtLivree;
    public static volatile SingularAttribute<Historiquelivraison, String> designation;
    public static volatile SingularAttribute<Historiquelivraison, Commande> idCommande;

}
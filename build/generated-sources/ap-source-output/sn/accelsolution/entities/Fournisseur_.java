package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Acomptefournisseur;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Historiquelivraison;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Pays;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Reffournisseur;
import sn.accelsolution.entities.Ressource;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Vente;
import sn.accelsolution.entities.Ville;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Fournisseur.class)
public class Fournisseur_ { 

    public static volatile SingularAttribute<Fournisseur, String> produit;
    public static volatile ListAttribute<Fournisseur, Commande> commandeList;
    public static volatile ListAttribute<Fournisseur, Stock> stockList;
    public static volatile SingularAttribute<Fournisseur, String> nomFournisseur;
    public static volatile SingularAttribute<Fournisseur, String> prixProdtui;
    public static volatile SingularAttribute<Fournisseur, byte[]> faxe;
    public static volatile SingularAttribute<Fournisseur, String> telephoneFournisseur;
    public static volatile SingularAttribute<Fournisseur, String> ninea;
    public static volatile ListAttribute<Fournisseur, Ressource> ressourceList;
    public static volatile SingularAttribute<Fournisseur, Integer> idFournisseur;
    public static volatile ListAttribute<Fournisseur, Reffournisseur> reffournisseurList;
    public static volatile SingularAttribute<Fournisseur, Pays> idPays;
    public static volatile SingularAttribute<Fournisseur, String> contact;
    public static volatile SingularAttribute<Fournisseur, Ville> idVille;
    public static volatile ListAttribute<Fournisseur, Acomptefournisseur> acomptefournisseurList;
    public static volatile ListAttribute<Fournisseur, Prix> prixList;
    public static volatile SingularAttribute<Fournisseur, String> fonctionContact;
    public static volatile ListAttribute<Fournisseur, Vente> venteList;
    public static volatile ListAttribute<Fournisseur, Livraison> livraisonList;
    public static volatile SingularAttribute<Fournisseur, String> mailFournisseur;
    public static volatile SingularAttribute<Fournisseur, String> adresseFournisseur;
    public static volatile ListAttribute<Fournisseur, Historiquelivraison> historiquelivraisonList;

}
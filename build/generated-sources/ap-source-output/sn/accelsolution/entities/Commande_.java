package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Acomptefournisseur;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Historiquelivraison;
import sn.accelsolution.entities.Livraison;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Paiement;
import sn.accelsolution.entities.Reffournisseur;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Taxe;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Commande.class)
public class Commande_ { 

    public static volatile SingularAttribute<Commande, Taxe> idTaxe;
    public static volatile SingularAttribute<Commande, Integer> positionLivraison;
    public static volatile SingularAttribute<Commande, String> code;
    public static volatile SingularAttribute<Commande, String> dateechance;
    public static volatile SingularAttribute<Commande, Client> idClient;
    public static volatile ListAttribute<Commande, Stock> stockList;
    public static volatile SingularAttribute<Commande, Marche> idMarche;
    public static volatile SingularAttribute<Commande, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Commande, String> etat;
    public static volatile SingularAttribute<Commande, String> livree;
    public static volatile SingularAttribute<Commande, Entrepot> idEntrepot;
    public static volatile SingularAttribute<Commande, Expressionbesoin> idExpression;
    public static volatile ListAttribute<Commande, DetailleCommande> detailleCommandeList;
    public static volatile ListAttribute<Commande, Paiement> paiementList;
    public static volatile SingularAttribute<Commande, String> motif;
    public static volatile ListAttribute<Commande, Livraison> livraisonList;
    public static volatile ListAttribute<Commande, Notification> notificationList;
    public static volatile SingularAttribute<Commande, String> observation;
    public static volatile ListAttribute<Commande, Reffournisseur> reffournisseurList;
    public static volatile SingularAttribute<Commande, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Commande, Chantier> idChantier;
    public static volatile SingularAttribute<Commande, String> modepaiment;
    public static volatile ListAttribute<Commande, Acomptefournisseur> acomptefournisseurList;
    public static volatile SingularAttribute<Commande, String> typecommande;
    public static volatile SingularAttribute<Commande, Integer> idCommande;
    public static volatile ListAttribute<Commande, Historiquelivraison> historiquelivraisonList;

}
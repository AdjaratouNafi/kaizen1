package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Ressource;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(DetailleCommande.class)
public class DetailleCommande_ { 

    public static volatile SingularAttribute<DetailleCommande, String> montanthortaxe;
    public static volatile SingularAttribute<DetailleCommande, String> ttc;
    public static volatile SingularAttribute<DetailleCommande, Integer> qtrestante;
    public static volatile SingularAttribute<DetailleCommande, String> pu;
    public static volatile SingularAttribute<DetailleCommande, String> unite;
    public static volatile SingularAttribute<DetailleCommande, String> observation;
    public static volatile SingularAttribute<DetailleCommande, String> puhortaxe;
    public static volatile SingularAttribute<DetailleCommande, String> montant;
    public static volatile SingularAttribute<DetailleCommande, String> remise;
    public static volatile SingularAttribute<DetailleCommande, String> dateEchance;
    public static volatile SingularAttribute<DetailleCommande, String> reference;
    public static volatile SingularAttribute<DetailleCommande, Marchandise> idMarchandise;
    public static volatile SingularAttribute<DetailleCommande, Integer> idDetailleCommande;
    public static volatile SingularAttribute<DetailleCommande, String> traiter;
    public static volatile SingularAttribute<DetailleCommande, String> designation;
    public static volatile SingularAttribute<DetailleCommande, Ressource> idRessource;
    public static volatile SingularAttribute<DetailleCommande, Integer> qtlivree;
    public static volatile SingularAttribute<DetailleCommande, Commande> idCommande;
    public static volatile SingularAttribute<DetailleCommande, Integer> quantite;

}
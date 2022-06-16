package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Acomptefournisseur;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Fournisseur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Reffournisseur.class)
public class Reffournisseur_ { 

    public static volatile SingularAttribute<Reffournisseur, String> nomcomplet;
    public static volatile SingularAttribute<Reffournisseur, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Reffournisseur, String> nature;
    public static volatile SingularAttribute<Reffournisseur, String> accord;
    public static volatile SingularAttribute<Reffournisseur, String> voyant;
    public static volatile SingularAttribute<Reffournisseur, String> accompte;
    public static volatile ListAttribute<Reffournisseur, Acomptefournisseur> acomptefournisseurList;
    public static volatile SingularAttribute<Reffournisseur, String> telephone;
    public static volatile SingularAttribute<Reffournisseur, String> reliquant;
    public static volatile SingularAttribute<Reffournisseur, String> echeance;
    public static volatile SingularAttribute<Reffournisseur, Commande> idCommande;
    public static volatile SingularAttribute<Reffournisseur, Integer> idReffournisseur;

}
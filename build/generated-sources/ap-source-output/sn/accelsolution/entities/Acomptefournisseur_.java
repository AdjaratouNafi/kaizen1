package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Reffournisseur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Acomptefournisseur.class)
public class Acomptefournisseur_ { 

    public static volatile SingularAttribute<Acomptefournisseur, Integer> idAcomptefournisseur;
    public static volatile SingularAttribute<Acomptefournisseur, String> choixtva;
    public static volatile SingularAttribute<Acomptefournisseur, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Acomptefournisseur, String> dateAcompte;
    public static volatile SingularAttribute<Acomptefournisseur, String> numeroacomptef;
    public static volatile SingularAttribute<Acomptefournisseur, String> echeanceAcompte;
    public static volatile SingularAttribute<Acomptefournisseur, String> montantAcompte;
    public static volatile SingularAttribute<Acomptefournisseur, Commande> idCommande;
    public static volatile SingularAttribute<Acomptefournisseur, String> numerocheque;
    public static volatile SingularAttribute<Acomptefournisseur, Reffournisseur> idReffournisseur;

}
package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Ville;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Pays.class)
public class Pays_ { 

    public static volatile SingularAttribute<Pays, Integer> idPays;
    public static volatile ListAttribute<Pays, Ville> villeList;
    public static volatile SingularAttribute<Pays, String> nomPays;
    public static volatile ListAttribute<Pays, Fournisseur> fournisseurList;

}
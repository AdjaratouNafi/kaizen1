package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Pays;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Ville.class)
public class Ville_ { 

    public static volatile SingularAttribute<Ville, String> nomVille;
    public static volatile SingularAttribute<Ville, Pays> idPays;
    public static volatile SingularAttribute<Ville, Integer> idVille;
    public static volatile ListAttribute<Ville, Fournisseur> fournisseurList;

}
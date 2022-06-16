package sn.accelsolution.entities;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Taxe.class)
public class Taxe_ { 

    public static volatile SingularAttribute<Taxe, Integer> idTaxe;
    public static volatile ListAttribute<Taxe, Commande> commandeList;
    public static volatile SingularAttribute<Taxe, String> natureTaxe;
    public static volatile SingularAttribute<Taxe, BigInteger> montantTaxe;

}
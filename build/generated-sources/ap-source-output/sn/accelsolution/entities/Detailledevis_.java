package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Unitemesure;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Detailledevis.class)
public class Detailledevis_ { 

    public static volatile SingularAttribute<Detailledevis, String> reference;
    public static volatile SingularAttribute<Detailledevis, Integer> idDetailDevis;
    public static volatile SingularAttribute<Detailledevis, String> typeitem;
    public static volatile SingularAttribute<Detailledevis, Devis> idDevis;
    public static volatile SingularAttribute<Detailledevis, String> unite;
    public static volatile SingularAttribute<Detailledevis, String> pu;
    public static volatile SingularAttribute<Detailledevis, Unitemesure> idUnitemesure;
    public static volatile SingularAttribute<Detailledevis, String> designation;
    public static volatile SingularAttribute<Detailledevis, Integer> quantite;

}
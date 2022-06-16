package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Unitemesure;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Detaillenewfacture.class)
public class Detaillenewfacture_ { 

    public static volatile SingularAttribute<Detaillenewfacture, Integer> idDetailleNewfacture;
    public static volatile SingularAttribute<Detaillenewfacture, String> reference;
    public static volatile SingularAttribute<Detaillenewfacture, String> typeitem;
    public static volatile SingularAttribute<Detaillenewfacture, String> unite;
    public static volatile SingularAttribute<Detaillenewfacture, String> pu;
    public static volatile SingularAttribute<Detaillenewfacture, Unitemesure> idUnitemesure;
    public static volatile SingularAttribute<Detaillenewfacture, String> designation;
    public static volatile SingularAttribute<Detaillenewfacture, Newfacture> idNewfacture;
    public static volatile SingularAttribute<Detaillenewfacture, Integer> quantite;

}
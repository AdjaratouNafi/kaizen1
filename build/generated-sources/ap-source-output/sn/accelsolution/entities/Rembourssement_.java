package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Pret;
import sn.accelsolution.entities.Salaire;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Rembourssement.class)
public class Rembourssement_ { 

    public static volatile SingularAttribute<Rembourssement, Pret> idPret;
    public static volatile SingularAttribute<Rembourssement, Integer> idRembourssement;
    public static volatile SingularAttribute<Rembourssement, Salaire> idSalaire;

}
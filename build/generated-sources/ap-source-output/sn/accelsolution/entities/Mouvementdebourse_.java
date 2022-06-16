package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Deboursser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Mouvementdebourse.class)
public class Mouvementdebourse_ { 

    public static volatile SingularAttribute<Mouvementdebourse, String> datesortiedeb;
    public static volatile SingularAttribute<Mouvementdebourse, Integer> idSortiedeb;
    public static volatile SingularAttribute<Mouvementdebourse, Chantier> idChantier;
    public static volatile SingularAttribute<Mouvementdebourse, Deboursser> idDeboursse;
    public static volatile SingularAttribute<Mouvementdebourse, Integer> quantite;

}
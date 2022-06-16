package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Mouvementdebourse;
import sn.accelsolution.entities.Nivodeboursser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Deboursser.class)
public class Deboursser_ { 

    public static volatile ListAttribute<Deboursser, Mouvementdebourse> mouvementdebourseList;
    public static volatile SingularAttribute<Deboursser, String> unite;
    public static volatile SingularAttribute<Deboursser, Marchandise> idMateriel;
    public static volatile SingularAttribute<Deboursser, Nivodeboursser> idNivodeboursser;
    public static volatile SingularAttribute<Deboursser, Integer> idDeboursser;
    public static volatile SingularAttribute<Deboursser, String> quantite;

}
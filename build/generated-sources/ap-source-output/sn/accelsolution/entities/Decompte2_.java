package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Detailledecompte2;
import sn.accelsolution.entities.Marche;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Decompte2.class)
public class Decompte2_ { 

    public static volatile ListAttribute<Decompte2, Detailledecompte2> detailledecompte2List;
    public static volatile SingularAttribute<Decompte2, String> numeroDecompte2;
    public static volatile SingularAttribute<Decompte2, Marche> idMarche;
    public static volatile SingularAttribute<Decompte2, String> dateDecompte2;
    public static volatile SingularAttribute<Decompte2, Integer> idDecompte2;

}
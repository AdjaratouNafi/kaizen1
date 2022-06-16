package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Corpsetat;
import sn.accelsolution.entities.Marchandise;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Lottechnique.class)
public class Lottechnique_ { 

    public static volatile SingularAttribute<Lottechnique, Integer> idLottehcnique;
    public static volatile ListAttribute<Lottechnique, Corpsetat> corpsetatList;
    public static volatile SingularAttribute<Lottechnique, String> libellelottechnique;
    public static volatile ListAttribute<Lottechnique, Marchandise> marchandiseList;

}
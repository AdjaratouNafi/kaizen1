package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Lottechnique;
import sn.accelsolution.entities.Marchandise;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Corpsetat.class)
public class Corpsetat_ { 

    public static volatile SingularAttribute<Corpsetat, Lottechnique> idLottehcnique;
    public static volatile SingularAttribute<Corpsetat, Integer> idCorpsetat;
    public static volatile SingularAttribute<Corpsetat, String> libellecoprsetat;
    public static volatile ListAttribute<Corpsetat, Marchandise> marchandiseList;

}
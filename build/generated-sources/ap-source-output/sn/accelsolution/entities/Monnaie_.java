package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Brouillard;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Monnaie.class)
public class Monnaie_ { 

    public static volatile SingularAttribute<Monnaie, Integer> idMonnaie;
    public static volatile SingularAttribute<Monnaie, String> dateMonnaie;
    public static volatile SingularAttribute<Monnaie, String> montantlettre;
    public static volatile SingularAttribute<Monnaie, String> nature;
    public static volatile SingularAttribute<Monnaie, String> piece;
    public static volatile SingularAttribute<Monnaie, String> observation;
    public static volatile SingularAttribute<Monnaie, String> totalemonnaie;
    public static volatile SingularAttribute<Monnaie, String> numeropiece;
    public static volatile SingularAttribute<Monnaie, String> monnnaie;
    public static volatile SingularAttribute<Monnaie, String> devise;
    public static volatile ListAttribute<Monnaie, Brouillard> brouillardList;
    public static volatile SingularAttribute<Monnaie, String> rendrepar;

}
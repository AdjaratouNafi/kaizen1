package sn.accelsolution.entities;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Monnaie;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Brouillard.class)
public class Brouillard_ { 

    public static volatile SingularAttribute<Brouillard, BigInteger> total;
    public static volatile SingularAttribute<Brouillard, Monnaie> idMonnaie;
    public static volatile SingularAttribute<Brouillard, Integer> idBrouillard;
    public static volatile SingularAttribute<Brouillard, Chantier> idChantier;
    public static volatile SingularAttribute<Brouillard, String> objetcredit;
    public static volatile SingularAttribute<Brouillard, Marche> idMarche;
    public static volatile SingularAttribute<Brouillard, String> typebrouillard;
    public static volatile SingularAttribute<Brouillard, String> objetdebit;
    public static volatile SingularAttribute<Brouillard, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Brouillard, BigInteger> debit;
    public static volatile SingularAttribute<Brouillard, BigInteger> credit;

}
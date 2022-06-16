package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Prix;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Devise.class)
public class Devise_ { 

    public static volatile SingularAttribute<Devise, String> libelle;
    public static volatile SingularAttribute<Devise, String> symbole;
    public static volatile ListAttribute<Devise, Prix> prixList;
    public static volatile SingularAttribute<Devise, Integer> idDevise;
    public static volatile ListAttribute<Devise, Devis> devisList;
    public static volatile ListAttribute<Devise, Marche> marcheList;

}
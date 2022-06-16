package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Assurance;
import sn.accelsolution.entities.Assureur;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Assurer.class)
public class Assurer_ { 

    public static volatile SingularAttribute<Assurer, Integer> idAssurer;
    public static volatile SingularAttribute<Assurer, Assureur> idAssureur;
    public static volatile SingularAttribute<Assurer, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Assurer, Assurance> idAssurance;

}
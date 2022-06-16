package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Prestataire;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Acompte.class)
public class Acompte_ { 

    public static volatile SingularAttribute<Acompte, String> choixtva;
    public static volatile SingularAttribute<Acompte, Chantier> idChantier;
    public static volatile SingularAttribute<Acompte, String> dateAcompte;
    public static volatile SingularAttribute<Acompte, String> echeanceAcompte;
    public static volatile SingularAttribute<Acompte, Prestataire> idPrestataire;
    public static volatile SingularAttribute<Acompte, String> numeroacompte;
    public static volatile SingularAttribute<Acompte, Integer> idAcompte;
    public static volatile SingularAttribute<Acompte, String> montantAcompte;
    public static volatile SingularAttribute<Acompte, String> numerocheque;

}
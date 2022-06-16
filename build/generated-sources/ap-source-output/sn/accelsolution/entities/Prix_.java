package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Devise;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;
import sn.accelsolution.entities.Unitemesure;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Prix.class)
public class Prix_ { 

    public static volatile SingularAttribute<Prix, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Prix, Marchandise> idMarchandise;
    public static volatile SingularAttribute<Prix, String> prix;
    public static volatile SingularAttribute<Prix, Unitemesure> idUnitemesure;
    public static volatile SingularAttribute<Prix, Integer> idPrix;
    public static volatile SingularAttribute<Prix, Devise> idDevise;

}
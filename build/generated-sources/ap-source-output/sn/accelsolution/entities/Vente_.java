package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.Marchandise;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Vente.class)
public class Vente_ { 

    public static volatile SingularAttribute<Vente, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Vente, Marchandise> idMarchandise;
    public static volatile SingularAttribute<Vente, Integer> idVente;

}
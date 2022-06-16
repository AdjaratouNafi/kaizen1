package sn.accelsolution.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Indemnite;
import sn.accelsolution.entities.Pret;
import sn.accelsolution.entities.TypeDemande;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Demande.class)
public class Demande_ { 

    public static volatile SingularAttribute<Demande, Date> dateCreationDemande;
    public static volatile SingularAttribute<Demande, Indemnite> idIndemnite;
    public static volatile SingularAttribute<Demande, Pret> idPret;
    public static volatile SingularAttribute<Demande, Integer> idDemande;
    public static volatile SingularAttribute<Demande, String> description;
    public static volatile SingularAttribute<Demande, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Demande, String> destinataire;
    public static volatile SingularAttribute<Demande, String> objet;
    public static volatile SingularAttribute<Demande, TypeDemande> idTypeDemande;
    public static volatile SingularAttribute<Demande, Integer> idSalaire;

}
package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Banque;
import sn.accelsolution.entities.Caisse;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Operation.class)
public class Operation_ { 

    public static volatile SingularAttribute<Operation, String> montantlettre;
    public static volatile SingularAttribute<Operation, Client> idClient;
    public static volatile SingularAttribute<Operation, String> nature;
    public static volatile SingularAttribute<Operation, String> typebeneficiaire;
    public static volatile SingularAttribute<Operation, Integer> idOperation;
    public static volatile SingularAttribute<Operation, String> observation;
    public static volatile SingularAttribute<Operation, Marche> idMarche;
    public static volatile SingularAttribute<Operation, String> nouveausolde;
    public static volatile SingularAttribute<Operation, String> nombanque;
    public static volatile SingularAttribute<Operation, String> dateoperation;
    public static volatile SingularAttribute<Operation, String> beneficiaire;
    public static volatile SingularAttribute<Operation, String> valuecredit;
    public static volatile SingularAttribute<Operation, String> objet;
    public static volatile SingularAttribute<Operation, String> anciensolde;
    public static volatile SingularAttribute<Operation, Caisse> idCaisse;
    public static volatile SingularAttribute<Operation, Chantier> idChantier;
    public static volatile SingularAttribute<Operation, String> validateur;
    public static volatile SingularAttribute<Operation, String> typeoperation;
    public static volatile SingularAttribute<Operation, Utilisateur> idApprobateur;
    public static volatile SingularAttribute<Operation, Banque> idBanque;
    public static volatile SingularAttribute<Operation, String> debit;

}
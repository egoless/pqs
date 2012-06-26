/*
 * Nome file: Storage.java
 * Data creazione: March 18, 2007, 5:04 PM
 * Info svn: $Id: Storage.java 684 2007-03-25 09:59:00Z eric $
 */

package ws.didattica.stub;

import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 * Classe usata per memorizzare su DB tutti gli oggetti creati per le prove.
 */
@Entity
public class Storage implements Serializable {
   
   //Costanti per determinare il tipo di oggetto memorizzato
   //in uno storage
   public static final String TIPO_VOTO="TipoVoto";
   public static final String ASSENZA="Assenza";
   public static final String VOTO="Voto";
   public static final String STUDENTE="Studente";
   public static final String INSEGNANTE="Insegnante";
   public static final String CLASSE="Classe";
   public static final String TIPO_ASSENZA="TipoAssenza";
   public static final String MATERIA="Materia";
   public static final String CONTATTO="Contatto";
   public static final String TIPO_PROVA="TipoProva";
   
   /**
    * Funzione di aiuto per salvare nel database un generico oggetto.
    * @param <T> Tipo di oggetto da salvare nel DB
    * @param em {@link EntityManager} usato per salvare l'oggetto.
    * @param c Contiene le informazioni sul tipo T.
    * @param newT Oggetto di tipo T da memorizzare.
    * @param elementName Nome da dare all'elemento memorizzato in XML.
    * @return Id con cui l'oggetto e' stato salvato nel DB.
    */
   public static <T> int persist(EntityManager em, Class<T> c, T newT, String elementName) {
      Writer wr=new StringWriter();
      JAXBContext jc;
      JAXBElement<T> je;
      Marshaller m;
      
      Storage st=new Storage(); //"Unita'" di salvataggio nel DB
      st.setKind(elementName);
      
      try {
         //Creiamo il contesto di serializzazione
         jc = JAXBContext.newInstance(c);
         
         je=new JAXBElement<T>(new QName(elementName), c, newT);
         m = jc.createMarshaller();
         m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                 Boolean.TRUE);
         m.marshal(je, wr);
      } catch (JAXBException ex) {
         ex.printStackTrace();
      }
      
      st.setContent(wr.toString());
      em.persist(st);
      
      return st.getId();
   }
   
   /**
    * Funzione di aiuto per salvare nel database un generico oggetto.
    * @param <T> Tipo di oggetto da salvare nel DB
    * @param em {@link EntityManager} usato per salvare l'oggetto.
    * @param c Contiene le informazioni sul tipo T.
    * @param newT Oggetto di tipo T da memorizzare.
    * @param elementName Nome da dare all'elemento memorizzato in XML.
    * @return Id con cui l'oggetto e' stato salvato nel DB.
    */
   public static <T> void persistExisting(EntityManager em, Class<T> c, T existingT, String elementName, int id) {
      Writer wr=new StringWriter();
      JAXBContext jc;
      JAXBElement<T> je;
      Marshaller m;
      
      Storage st=em.find(Storage.class, id);
//    st=new Storage(); //"Unita'" di salvataggio nel DB
//         st.setKind(elementName);
//         st.setId(id);
      if (st!=null) {
         try {
            //Creiamo il contesto di serializzazione
            jc = JAXBContext.newInstance(c);
            
            je=new JAXBElement<T>(new QName(elementName), c, existingT);
            m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            m.marshal(je, wr);
         } catch (JAXBException ex) {
            ex.printStackTrace();
         }
         
         st.setContent(wr.toString());
         em.persist(st);
      }
   }
   
   /*
    * Recupera da DB tutte le entita' di un certo tipo.
    * @param em {@link} EntityManager da usare per accedere al DB.
    * @param kind Stringa che identifica il tipo di oggetto.
    * @return Lista di oggetti Storage che contengono gli oggetti voluti.
    */
   public static List<Storage> retrieve(EntityManager em, String kind) {
      List<Storage> result=new ArrayList<Storage>();
      
      Query q=em.createQuery("SELECT st FROM Storage st WHERE st.kind = :kind");
      q.setParameter("kind", kind);
      List l=q.getResultList();
      
      for(Object obj : l) {
         Storage st=(Storage) obj;
         result.add(st);
      }
      
      return result;
   }
   
   public static void delete(EntityManager em, int id) {
      Storage st=em.find(Storage.class, id);
      
      if (st!=null) {
         em.remove(st);
      }
   }
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   
   /** Creates a new instance of Storage */
   public Storage() {
   }
   
   /**
    * Gets the id of this Storage.
    * @return the id
    */
   public int getId() {
      return this.id;
   }
   
   /**
    * Sets the id of this Storage to the specified value.
    * @param id the new id
    */
   public void setId(int id) {
      this.id = id;
   }
   
   /**
    * Returns a hash code value for the object.  This implementation computes
    * a hash code value based on the id fields in this object.
    * @return a hash code value for this object.
    */
   @Override
   public int hashCode() {
      int hash = 0;
      hash += (int)id;
      return hash;
   }
   
   /**
    * Determines whether another object is equal to this Storage.  The result is
    * <code>true</code> if and only if the argument is not null and is a Storage object that
    * has the same id field values as this object.
    * @param object the reference object with which to compare
    * @return <code>true</code> if this object is the same as the argument;
    * <code>false</code> otherwise.
    */
   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof Storage)) {
         return false;
      }
      Storage other = (Storage)object;
      if (this.id != other.id) return false;
      return true;
   }
   
   /**
    * Returns a string representation of the object.  This implementation constructs
    * that representation based on the id fields.
    * @return a string representation of the object.
    */
   @Override
   public String toString() {
      return "ws.didattica.stub.Storage[id=" + id + "]";
   }
   
   /**
    * Holds value of property kind.
    */
   private String kind;
   
   /**
    * Getter for property type.
    * @return Value of property type.
    */
   public String getKind() {
      return this.kind;
   }
   
   /**
    * Setter for property type.
    * @param type New value of property type.
    */
   public void setKind(String kind) {
      this.kind = kind;
   }
   
   /**
    * Holds value of property content.
    */
   @Column(length=1024)
   private String content;
   
   /**
    * Getter for property content.
    * @return Value of property content.
    */
   public String getContent() {
      return this.content;
   }
   
   /**
    * Setter for property content.
    * @param content New value of property content.
    */
   public void setContent(String content) {
      this.content = content;
   }
   
}

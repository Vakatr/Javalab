package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Box")
public class Box implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "Nomerbox")
    private String Nomerbox;
    @ManyToMany(mappedBy = "boxCollection")
    private Collection<Owner> ownerCollection;


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Box)) {
            return false;
        }
        Box other = (Box) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Box[ id=" + id + " ]";
    }

    /**
     * @return the Nomerbox
     */
    public String getNomerbox() {
        return Nomerbox;
    }

    /**
     * @param Nomerbox the Nomerbox to set
     */
    public void setNomerbox(String Nomerbox) {
        this.Nomerbox = Nomerbox;
    }

    /**
     * @return the ownerCollection
     */
    public Collection<Owner> getOwnerCollection() {
        return ownerCollection;
    }

    /**
     * @param ownerCollection the ownerCollection to set
     */
    public void setOwnerCollection(Collection<Owner> ownerCollection) {
        this.ownerCollection = ownerCollection;
    }
}

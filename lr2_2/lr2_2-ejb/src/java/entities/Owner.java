package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import model.OwnerEntity;

@Entity
@Table(name = "Owner")
@NamedQuery(name = "Owner.findAlll", query = "SELECT o FROM Owner o")
@EntityListeners (OwnerEntity.class)
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "Driving_license_owner")
    private String Driving_license_owner;
    @Column(name = "Surname")
    private String Surname;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Patronymic")
    private String Patronymic;
    @Column(name = "Address")
    private String Address;
    @Column(name = "Phone")
    private String Phone;
    @OneToMany(mappedBy = "owner")
    private Collection<Car> carCollection;
    @ManyToMany
    @JoinTable(name = "owner_box", joinColumns = @JoinColumn(name = "ID_Owner"),inverseJoinColumns = @JoinColumn(name = "ID_Box"))
    private Collection<Box> boxCollection;

    public int getId() {
        return id;
    }

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
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+id+"";
    }

    /**
     * @return the Driving_license_owner
     */
    public String getDriving_license_owner() {
        return Driving_license_owner;
    }

    /**
     * @param Driving_license_owner the Driving_license_owner to set
     */
    public void setDriving_license_owner(String Driving_license_owner) {
        this.Driving_license_owner = Driving_license_owner;
    }

    /**
     * @return the Surname
     */
    public String getSurname() {
        return Surname;
    }

    /**
     * @param Surname the Surname to set
     */
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Patronymic
     */
    public String getPatronymic() {
        return Patronymic;
    }

    /**
     * @param Patronymic the Patronymic to set
     */
    public void setPatronymic(String Patronymic) {
        this.Patronymic = Patronymic;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone the Phone to set
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /**
     * @return the carCollection
     */
    public Collection<Car> getCarCollection() {
        return carCollection;
    }

    /**
     * @param carCollection the carCollection to set
     */
    public void setCarCollection(Collection<Car> carCollection) {
        this.carCollection = carCollection;
    }

    /**
     * @return the boxCollection
     */
    public Collection<Box> getBoxCollection() {
        return boxCollection;
    }

    /**
     * @param boxCollection the boxCollection to set
     */
    public void setBoxCollection(Collection<Box> boxCollection) {
        this.boxCollection = boxCollection;
    }
    
}

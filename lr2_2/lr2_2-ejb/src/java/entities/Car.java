package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "Car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ID_Owner")
    private Owner owner;
    @Column(name = "State_number")
    private String State_number;
    @Column(name = "Brand")
    private String Brand;
    @Column(name = "Release_year")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Release_year;
    @Column(name = "Power")
    private String Power;
    @Column(name = "Color")
    private String Color;

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
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Car[ id=" + id + " ]";
    }

    /**
     * @return the ID_Owner
     */
    public Owner getOwner() {
        return owner;
    }


    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * @return the State_number
     */
    public String getState_number() {
        return State_number;
    }

    /**
     * @param State_number the State_number to set
     */
    public void setState_number(String State_number) {
        this.State_number = State_number;
    }

    /**
     * @return the Brand
     */
    public String getBrand() {
        return Brand;
    }

    /**
     * @param Brand the Brand to set
     */
    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    /**
     * @return the Release_year
     */
    public Date getRelease_year() {
        return Release_year;
    }

    /**
     * @param Release_year the Release_year to set
     */
    public void setRelease_year(Date Release_year) {
        this.Release_year = Release_year;
    }

    /**
     * @return the Power
     */
    public String getPower() {
        return Power;
    }

    /**
     * @param Power the Power to set
     */
    public void setPower(String Power) {
        this.Power = Power;
    }

    /**
     * @return the Color
     */
    public String getColor() {
        return Color;
    }

    /**
     * @param Color the Color to set
     */
    public void setColor(String Color) {
        this.Color = Color;
    }
    
}

package pl.ciszemar.androidfirstapp.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.v4.widget.CircularProgressDrawable;

/**
 * Created by uninetix on 23.11.17.
 */

@Entity(tableName = "contacts")
public class Contact {

    @PrimaryKey
    @ColumnInfo(name="contactid")
    private int id;
    @ColumnInfo(name = "firstname")
    private String firstName;
    @ColumnInfo(name = "lastname")
    private String lastName;
    @ColumnInfo(name = "phonenumber")
    private String phoneNumber;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "favorite")
    private boolean favorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", favorite=" + favorite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (favorite != contact.favorite) return false;
        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(contact.phoneNumber) : contact.phoneNumber != null)
            return false;
        return email != null ? email.equals(contact.email) : contact.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (favorite ? 1 : 0);
        return result;
    }
}

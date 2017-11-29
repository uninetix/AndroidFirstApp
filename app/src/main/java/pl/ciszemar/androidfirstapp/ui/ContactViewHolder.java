package pl.ciszemar.androidfirstapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pl.ciszemar.androidfirstapp.R;
import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by uninetix on 29.11.17.
 */

class ContactViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private Contact contact;

    private TextView firstName;
    private TextView lastName;
    private TextView phoneNumber;
    private TextView email;

    public ContactViewHolder(View itemTask) {
        super(itemTask);
        firstName = itemTask.findViewById(R.id.firstName);
        lastName = itemTask.findViewById(R.id.lastName);
        phoneNumber = itemTask.findViewById(R.id.phoneNumber);
        email = itemTask.findViewById(R.id.email);
    }

    public void setContact(Contact contact) {
        this.contact = contact;

        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        phoneNumber.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
    }

}

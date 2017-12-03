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

    private Contact contact;

    private TextView name;
    private TextView phoneNumber;
    private TextView email;

    public ContactViewHolder(View itemTask) {
        super(itemTask);
        name = itemTask.findViewById(R.id.name);
        phoneNumber = itemTask.findViewById(R.id.phoneNumber);
        email = itemTask.findViewById(R.id.email);
    }

    public void setContact(Contact contact) {
        this.contact = contact;

        name.setText(contact.getFirstName() + " " + contact.getLastName());
        phoneNumber.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
    }

}

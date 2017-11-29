package pl.ciszemar.androidfirstapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import pl.ciszemar.androidfirstapp.R;
import pl.ciszemar.androidfirstapp.entity.Contact;

/**
 * Created by uninetix on 29.11.17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>{

    List<Contact> data = Collections.emptyList();

    public ContactAdapter() {
    }

    public void setData(List<Contact> data) {
        this.data = data;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =
                LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(
                R.layout.item_contact,
                viewGroup,
                false
        );
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder viewHolder, int position) {
        Contact contact = data.get(position);
        viewHolder.setContact(contact);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

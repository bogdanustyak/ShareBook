package com.leoart.sharebook.contacts;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leoart.sharebook.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    private List<Contact> contactList;
    private int layout;


    public ContactsAdapter(int layout, List<Contact> contactList) {
        this.layout = layout;
        this.contactList = contactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        final ViewHolder dataObjectHolder = new ViewHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        if(!TextUtils.isEmpty(contact.getFirstName())) {
            holder.tvNamee.setText(contact.getFirstName());
        }else{
            holder.tvNamee.setText("");
        }
        if(!TextUtils.isEmpty(contact.getUserName())) {
            holder.tvUserName.setText(contact.getUserName());
        }else{
            holder.tvUserName.setText("");
        }
    }

    @Override
    public int getItemCount() {
        if(contactList!=null)
            return contactList.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAvatar;
        TextView tvUserName, tvNamee;

        public ViewHolder(View itemView) {
            super(itemView);

            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvNamee = (TextView) itemView.findViewById(R.id.tvNamee);
        }
    }

}

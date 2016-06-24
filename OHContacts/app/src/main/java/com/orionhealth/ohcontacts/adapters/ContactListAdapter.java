package com.orionhealth.ohcontacts.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orionhealth.ohcontacts.R;
import com.orionhealth.ohcontacts.fragments.ContactsListFragment;
import com.orionhealth.ohcontacts.models.Contact;
import com.orionhealth.ohcontacts.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashokjangra70@gmail.com on 11/06/16.
 */


public class ContactListAdapter extends BaseAdapter {

    Context mContext;
    public ContactsListFragment fragment;
    List<Contact> mContacts = new ArrayList<>();

    public ContactListAdapter(Context context, List<Contact> contacts, ContactsListFragment fragment) {
        if(contacts != null && contacts.size() > 0){
            this.mContacts.clear();
            this.mContacts.addAll(contacts);
            notifyDataSetChanged();
        }
        this.mContext = context;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_item, null);
            holder = new ViewHolder();
            holder.displayEmail = (TextView) convertView.findViewById(R.id.email);
            holder.displayName = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = mContacts.get(position);

        if (!MyUtils.isEmpty(contact.name)) {
            holder.displayName.setVisibility(View.VISIBLE);
            holder.displayName.setText(contact.name);
        } else {
            holder.displayName.setVisibility(View.GONE);
        }

        if (!MyUtils.isEmpty(contact.email)) {
            holder.displayEmail.setVisibility(View.VISIBLE);
            holder.displayEmail.setText(contact.email);
        } else {
            holder.displayEmail.setVisibility(View.GONE);
        }

        return convertView;
    }

    public void setItemList(List<Contact> contacts) {
        if(contacts != null && contacts.size() > 0){
            this.mContacts.clear();
            this.mContacts.addAll(contacts);
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        private TextView displayName;
        private TextView displayEmail;
    }
}
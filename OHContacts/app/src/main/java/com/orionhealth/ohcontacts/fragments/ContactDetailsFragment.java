package com.orionhealth.ohcontacts.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orionhealth.ohcontacts.R;
import com.orionhealth.ohcontacts.models.Contact;
import com.orionhealth.ohcontacts.utils.MyUtils;

/**
 * Created by ashokjangra70@gmail.com on 12/06/16.
 */
public class ContactDetailsFragment extends Fragment {

    public static final String INTENT_EXTRA_CONTACT = "CONTACT_OBJ";
    private Contact contact;

    private TextView tv_username_val;
    private TextView tv_username;
    private TextView tv_website_val;
    private TextView tv_website;
    private TextView tv_phone_val;
    private TextView tv_phone;
    private TextView tv_address_val;
    private TextView tv_address;
    private TextView tv_company_val;
    private TextView tv_company;

    public ContactDetailsFragment() {
    }

    public static ContactDetailsFragment newInstance(Contact cntct) {
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_EXTRA_CONTACT, cntct);
        if (cntct != null) {
            fragment.contact = cntct;
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, null);

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        }

        contact = (Contact) getArguments().get(INTENT_EXTRA_CONTACT);
        if (contact != null) {
            if (!MyUtils.isEmpty(contact.name)) {
                getActivity().setTitle(contact.name);
            }
            tv_username = (TextView) view.findViewById(R.id.username);
            tv_username_val = (TextView) view.findViewById(R.id.username_val);
            tv_phone = (TextView) view.findViewById(R.id.phone);
            tv_phone_val = (TextView) view.findViewById(R.id.phone_val);
            tv_company = (TextView) view.findViewById(R.id.company);
            tv_company_val = (TextView) view.findViewById(R.id.company_val);
            tv_address = (TextView) view.findViewById(R.id.oh_address);
            tv_address_val = (TextView) view.findViewById(R.id.oh_address_val);
            tv_website = (TextView) view.findViewById(R.id.website);
            tv_website_val = (TextView) view.findViewById(R.id.website_val);

            if (MyUtils.isEmpty(contact.username)) {
                tv_username.setVisibility(View.GONE);
                tv_username_val.setVisibility(View.GONE);
            } else {
                tv_username.setVisibility(View.VISIBLE);
                tv_username_val.setVisibility(View.VISIBLE);
                tv_username_val.setText(contact.username);
            }

            if (MyUtils.isEmpty(contact.phone)) {
                tv_phone.setVisibility(View.GONE);
                tv_phone_val.setVisibility(View.GONE);
            } else {
                tv_phone.setVisibility(View.VISIBLE);
                tv_phone_val.setVisibility(View.VISIBLE);
                tv_phone_val.setText(contact.phone);
            }

            if (MyUtils.isEmpty(contact.website)) {
                tv_website.setVisibility(View.GONE);
                tv_website_val.setVisibility(View.GONE);
            } else {
                tv_website.setVisibility(View.VISIBLE);
                tv_website_val.setVisibility(View.VISIBLE);
                tv_website_val.setText(contact.website);
            }

            if (contact.ohAddress == null) {
                tv_address.setVisibility(View.GONE);
                tv_address_val.setVisibility(View.GONE);
            } else {
                tv_address.setVisibility(View.VISIBLE);
                tv_address_val.setVisibility(View.VISIBLE);
                tv_address_val.setText(contact.ohAddress.street + ", " + contact.ohAddress.suite
                        + ", \n" + contact.ohAddress.city + ", " + contact.ohAddress.zipcode);
            }

            if (contact.company == null) {
                tv_company.setVisibility(View.GONE);
                tv_company_val.setVisibility(View.GONE);
            } else {
                tv_company.setVisibility(View.VISIBLE);
                tv_company_val.setVisibility(View.VISIBLE);
                tv_company_val.setText(contact.company.name + ", \n"
                        + contact.company.catchPhrase + ", \n" + contact.company.bs);
            }
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            if (!MyUtils.isEmpty(contact.name)) {
                actionBar.setTitle(contact.name);
            } else {
                actionBar.setTitle(R.string.app_name);
            }
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
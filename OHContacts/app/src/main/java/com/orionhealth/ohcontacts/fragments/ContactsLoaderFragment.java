package com.orionhealth.ohcontacts.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionhealth.ohcontacts.ContactsActivity;
import com.orionhealth.ohcontacts.R;

/**
 * A placeholder fragment containing a simple view to display loading.
 */
public class ContactsLoaderFragment extends android.app.Fragment {

    public ContactsLoaderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        ((ContactsActivity) getActivity()).fetchList();
        View view = inflater.inflate(R.layout.fragment_loader, null);
        return view;
    }
}

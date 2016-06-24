package com.orionhealth.ohcontacts.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.orionhealth.ohcontacts.ContactsActivity;
import com.orionhealth.ohcontacts.R;
import com.orionhealth.ohcontacts.adapters.ContactListAdapter;
import com.orionhealth.ohcontacts.models.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ashokjangra70@gmail.com on 11/06/16.
 */
public class ContactsListFragment extends Fragment {
    private ContactListAdapter adapter;
    private List<Contact> mList = new ArrayList<>();
    private ListView listView;
    ViewSwitcher viewSwitcher;


    @SuppressLint("ValidFragment")
    public ContactsListFragment(List<Contact> list) {
        this.mList.clear();
        this.mList.addAll(list);
    }

    public ContactsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);
        ((ContactsActivity) getActivity()).fetchList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(ContactsActivity.TAG, "mList.size() = " + mList.size());
        View view = inflater.inflate(R.layout.fragment_list, null);
        listView = (ListView) view.findViewById(R.id.list);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.vs_contacts);
        viewSwitcher.setDisplayedChild(0);
        adapter = new ContactListAdapter(getActivity(), mList, ContactsListFragment.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact c = mList.get(position);
                ContactDetailsFragment fragment = ContactDetailsFragment.newInstance(c);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragment, fragment, fragment.getClass().getName()).addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commitAllowingStateLoss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.vs_contacts);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // If we are returning here from a screen orientation
        // and the AsyncTask is still working, re-create and display the
        // progress dialog.
        if(mList != null && mList.size() > 0){
            viewSwitcher.setDisplayedChild(1);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_contacts, menu);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_sort_asc) {
            Collections.sort(mList, new Comparator<Contact>() {
                public int compare(Contact c1, Contact c2) {
                    return c1.name.compareTo(c2.name);
                }
            });

            //Collections.sort(mContactsList);  --> can be used by implementing Comparable for Contact
            Log.d(ContactsActivity.TAG, "mContactsList ASC -->" + mList);
            refreshData(mList);

            return true;
        }
        if (id == R.id.action_sort_desc) {
            Collections.sort(mList, new Comparator<Contact>() {
                public int compare(Contact c1, Contact c2) {
                    return c2.name.compareTo(c1.name);
                }
            });
            Log.d(ContactsActivity.TAG, "mContactsList DESC -->" + mList);
            refreshData(mList);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshData(List<Contact> list) {
        List<Contact> temp = new ArrayList<>();
        temp.addAll(list);
        if (list != null && list.size() > 0) {
            this.mList.clear();
            this.mList.addAll(temp);
            if (viewSwitcher != null) {
                viewSwitcher.setDisplayedChild(1);
            }
            if (listView != null) {
                ((ContactListAdapter) listView.getAdapter()).setItemList(mList);
            }
        }
    }
}
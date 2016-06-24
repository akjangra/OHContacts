package com.orionhealth.ohcontacts;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.orionhealth.ohcontacts.fragments.ContactsListFragment;
import com.orionhealth.ohcontacts.models.Contact;
import com.orionhealth.ohcontacts.utils.MyUtils;
import com.orionhealth.ohcontacts.utils.OkHttpHelper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    public static final String URL_TO_FETCH_CONTACTS = "http://jsonplaceholder.typicode.com/users";
    public static final String TAG = "OrionHealth";
    OkHttpHelper okHttpHelper = new OkHttpHelper();
    private List<Contact> mContactsList = new ArrayList<>();
    FragmentManager fragmentManager = getFragmentManager();
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //Check for Internet connection
        /*if(MyUtils.isNetworkAvailable(this)){
            Toast.makeText(this, "Internet not working. Check your Wifi/data connection.", Toast.LENGTH_LONG).show();
        }*/

        // If the Fragment is non-null, then it is being retained for a configuration change.
        fragment = fragmentManager.findFragmentById(R.id.fragment);
        if (fragment == null) {
            fragment = new ContactsListFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment, fragment, "ContactsListFragment")
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeButtonEnabled(true);
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setHomeButtonEnabled(false);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

            if (getFragmentManager().getBackStackEntryCount() > 1) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setHomeButtonEnabled(false);
            }
            return;
        }
        super.onBackPressed();
    }

    public class MyAsyncTask extends AsyncTask<String, Void, List<Contact>> {
        //ProgressBar dialog is removed, added ViewSwitcher to match UX design
        //private final ProgressDialog dialog = new ProgressDialog(ContactsActivity.this);

        @Override
        protected void onPostExecute(List<Contact> result) {
            super.onPostExecute(result);
            //ProgressBar dialog is removed, added ViewSwitcher to match UX design
            //dialog.dismiss();

            fragment = fragmentManager.findFragmentById(R.id.fragment);
            if (fragment == null) {
                fragment = new ContactsListFragment(result);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment, fragment.getClass().getName());
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commitAllowingStateLoss();
            } else {
                if (fragment instanceof ContactsListFragment) {
                    ((ContactsListFragment) fragment).refreshData(result);
                }
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //ProgressBar dialog is removed, added ViewSwitcher to match UX design
            //dialog.setMessage("Loading contacts...");
            //dialog.setIndeterminate(true);
            //dialog.show();
        }

        @Override
        protected List<Contact> doInBackground(String... params) {
            String response = "";

            Type type = new TypeToken<ArrayList<Contact>>() {
            }.getType();

            try {
                response = okHttpHelper.doGetRequest(params[0].toString());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Exception = " + e.getMessage());
            }

            if (!MyUtils.isEmpty(response)) {
                mContactsList = new GsonBuilder().create().fromJson(response, type);
                Log.d(TAG, "mContactsList.size() = " + mContactsList.size());
            } else {
                Log.d(TAG, "Response is empty or null : " + response.toString());
            }

            return mContactsList;
        }

    }

    public void fetchList() {
        new MyAsyncTask().execute(URL_TO_FETCH_CONTACTS);
    }
}
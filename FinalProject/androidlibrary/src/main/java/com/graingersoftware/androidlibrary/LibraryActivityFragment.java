package com.graingersoftware.androidlibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class LibraryActivityFragment extends Fragment
{

    public LibraryActivityFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_library, container, false);
        TextView jokeTextView = (TextView)rootView.findViewById(R.id.jokeTextView);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null)
        {
            String joke = bundle.getString(LibraryActivity.JOKE_EXTRA_STRING);
            jokeTextView.setText(joke);
        }
        return rootView;
    }
}

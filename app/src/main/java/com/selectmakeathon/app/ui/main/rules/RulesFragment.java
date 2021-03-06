package com.selectmakeathon.app.ui.main.rules;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.selectmakeathon.app.R;
import com.selectmakeathon.app.ui.main.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */

public class RulesFragment extends androidx.fragment.app.Fragment {


    private ImageView navIcon;

    public RulesFragment() {
        // Required empty public constructor
    }

    public static RulesFragment newInstance() {
        return new RulesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rules, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navIcon = view.findViewById(R.id.frag_itinerary_nav_icon);

        navIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openSideNav();
            }
        });
    }
}

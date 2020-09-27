package com.ycdev.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ycdev.myapplication.MainActivity;
import com.ycdev.myapplication.R;

public class AuthorPageFragment extends Fragment {
    private static AuthorPageFragment instance;

    public static AuthorPageFragment newInstance() {
        if (instance == null) {
            instance = new AuthorPageFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.author_page_fragment, container, false);

        Button btnRequest = view.findViewById(R.id.btn_request);
        btnRequest.setOnClickListener(v -> {
            ((MainActivity) getActivity()).replaceFragment(BrowserPageFragment.newInstance());
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

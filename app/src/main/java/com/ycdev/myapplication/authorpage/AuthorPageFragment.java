package com.ycdev.myapplication.authorpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ycdev.myapplication.R;

public class AuthorPageFragment extends Fragment {
    private static AuthorPageFragment instance;

    private AuthorPageViewModel mViewModel;

    public static AuthorPageFragment newInstance() {
        if (instance == null) {
            instance = new AuthorPageFragment();
        }

        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.author_page_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AuthorPageViewModel.class);
        // TODO: Use the ViewModel
    }

}

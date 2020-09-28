package com.ycdev.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ycdev.myapplication.R;
import com.ycdev.myapplication.databinding.BrowserPageFragmentBinding;
import com.ycdev.myapplication.viewmodel.BrowserPageViewModel;

public class BrowserPageFragment extends Fragment {
    private static BrowserPageFragment instance;

    private BrowserPageViewModel viewModel;
    private BrowserPageFragmentBinding binding;

    public static BrowserPageFragment newInstance() {
        if (instance == null) {
            instance = new BrowserPageFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browser_page_fragment, container, false);
        binding = BrowserPageFragmentBinding.bind(view);

        viewModel = new ViewModelProvider(this).get(BrowserPageViewModel.class);
        binding.setBrowserViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.loadPhotos();
    }
}

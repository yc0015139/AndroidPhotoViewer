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
import com.ycdev.myapplication.databinding.PhotoFragmentBinding;
import com.ycdev.myapplication.viewmodel.PhotoViewModel;

public class PhotoFragment extends Fragment {

    private PhotoViewModel viewModel;
    private PhotoFragmentBinding binding;

    private static PhotoFragment instance;

    public static PhotoFragment getInstance() {
        if (instance == null) {
            instance = new PhotoFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment, container, false);
        binding = PhotoFragmentBinding.bind(view);

        viewModel = new ViewModelProvider(requireActivity()).get(PhotoViewModel.class);
        binding.setPhotoViewModel(viewModel);
        binding.setPhotoView(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

package com.ycdev.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ycdev.myapplication.MainActivity;
import com.ycdev.myapplication.R;
import com.ycdev.myapplication.adapter.ImageAdapter;
import com.ycdev.myapplication.databinding.BrowserPageFragmentBinding;
import com.ycdev.myapplication.model.Photo;
import com.ycdev.myapplication.viewmodel.BrowserPageViewModel;
import com.ycdev.myapplication.viewmodel.PhotoViewModel;

public class BrowserPageFragment extends Fragment implements ImageAdapter.OnImageClickListener {
    private static BrowserPageFragment instance;

    private BrowserPageViewModel viewModel;
    private BrowserPageFragmentBinding binding;
    private ImageAdapter imageAdapter;


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

        viewModel = new ViewModelProvider(requireActivity()).get(BrowserPageViewModel.class);
        binding.setBrowserViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpRecyclerView();
        viewModel.loadPhotos();
    }

    private void setUpRecyclerView() {
        imageAdapter = new ImageAdapter(this);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(imageAdapter);

        viewModel.getPhotos().observe(getViewLifecycleOwner(), photos -> {
            imageAdapter.setPhotos(photos);
        });
    }

    @Override
    public void onImageClicked(Photo photo) {
        PhotoViewModel photoViewModel = new ViewModelProvider(requireActivity()).get(PhotoViewModel.class);
        photoViewModel.setPhoto(photo);

        ((MainActivity) requireActivity()).replaceFragment(PhotoFragment.getInstance(), true);
    }
}

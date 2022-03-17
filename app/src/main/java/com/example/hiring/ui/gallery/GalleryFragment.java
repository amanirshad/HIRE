package com.example.hiring.ui.gallery;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.hiring.R;
import com.example.hiring.databinding.FragmentGalleryBinding;
import com.example.hiring.ui.home.HomeFragment;
import com.example.hiring.ui.slideshow.SlideshowFragment;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        binding.btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String describe = binding.etDescribe.getText().toString().trim();
                String motivate = binding.etMotivates.getText().toString().trim();
                if (TextUtils.isEmpty(describe)) {
                    binding.etDescribe.setError("Required");
                } else if (TextUtils.isEmpty(motivate)) {
                    binding.etMotivates.setError("Required");
                } else if (motivate.length() < 100) {
                    binding.etMotivates.setError("Enter more than 100 words");
                } else if (describe.length() < 100) {
                    binding.etDescribe.setError("Enter more than 100 words");
                } else {
                    SlideshowFragment slideshowFragment = new SlideshowFragment();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment_content_nav, slideshowFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        binding.btnPrev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment_content_nav, homeFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
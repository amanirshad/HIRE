package com.example.hiring.ui.Video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hiring.databinding.VideoBinding;

public class VideoFragment extends Fragment {
    private VideoBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
       binding = VideoBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        return root;
    }
}

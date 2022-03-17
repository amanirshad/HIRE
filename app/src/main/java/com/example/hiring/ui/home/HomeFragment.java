package com.example.hiring.ui.home;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.example.hiring.Model.Profile;
import com.example.hiring.R;
import com.example.hiring.databinding.FragmentHomeBinding;
import com.example.hiring.ui.gallery.GalleryFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        database=FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        binding.btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = binding.etFirstName.getText().toString().trim();
                String last_name = binding.etLastName.getText().toString().trim();
                String pic = binding.profileImage.toString().trim();
                if (TextUtils.isEmpty(first_name)) {
                    binding.etFirstName.setError("Required");
                } else if (TextUtils.isEmpty(last_name)) {
                    binding.etLastName.setError("Required");
                } else {
                        FirebaseUser currentFirebaseUser = mAuth.getCurrentUser();
                        Profile profile = new Profile(first_name,last_name,pic);
                       DatabaseReference db= database.getReference();
                       db.child(currentFirebaseUser.getUid()).child("Profile").setValue(profile);
//                        database.getReference().child(id).child("Profile").setValue(profile);
                        GalleryFragment galleryFragment = new GalleryFragment();
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment_content_nav, galleryFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();


                }
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
package com.example.quiz.ui.login.ui.profile;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.example.quiz.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    RecyclerView recyclerView;
    HistoryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel notificationsViewModel =
                new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.userHistory;
        LinearLayoutManager manager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(manager);
        final RecyclerView recyclerView = binding.userHistory;
        adapter = new HistoryAdapter(notificationsViewModel.getHistory(), binding.getRoot().getContext());
        recyclerView.setAdapter(adapter);
        final TextView textView = binding.textProfile;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
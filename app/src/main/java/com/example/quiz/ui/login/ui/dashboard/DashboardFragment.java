package com.example.quiz.ui.login.ui.dashboard;

import android.content.Context;
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

import com.example.quiz.R;
import com.example.quiz.databinding.FragmentDashboardBinding;
import com.example.quiz.ui.login.ui.profile.ProfileViewModel;

import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    RecyclerView recyclerView;
    ScoreAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        recyclerView = binding.leaderboard;
        View root = binding.getRoot();
        LinearLayoutManager manager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(manager);
        final RecyclerView recyclerView = binding.leaderboard;
        adapter = new ScoreAdapter(dashboardViewModel.getUsers(), binding.getRoot().getContext());
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
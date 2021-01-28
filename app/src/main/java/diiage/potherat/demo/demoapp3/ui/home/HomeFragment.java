package diiage.potherat.demo.demoapp3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.databinding.FragmentHomeBinding;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    @Inject
    HomeViewModel viewModel;
    private FragmentHomeBinding binding;
    TextView txtNumberOfQuotes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        txtNumberOfQuotes = binding.getRoot().findViewById(R.id.txtNumberOfQuotes);
        ready();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this,getDefaultViewModelProviderFactory()).get(HomeViewModel.class);
        ready();
    }

    private void ready(){
        if (binding != null && viewModel != null){
            binding.setLifecycleOwner(this);
            viewModel.getCountQuote().observe(getViewLifecycleOwner(), s -> {
                txtNumberOfQuotes.setText(s.toString());
            });
            binding.setViewModel(viewModel);
        }
    }
}
package diiage.potherat.demo.demoapp3.ui.vehicles;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.databinding.VehiclesFragmentBinding;

@AndroidEntryPoint
public class VehiclesFragment extends Fragment {
    @Inject
    VehiclesViewModel vehicleViewModel ;
    private VehiclesFragmentBinding binding;
    TextView txtName;
    TextView txtModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = VehiclesFragmentBinding.inflate(inflater,container,false);
        txtName =  binding.getRoot().findViewById(R.id.vehicleName);
        txtModel = binding.getRoot().findViewById(R.id.vehicleModel);
        ready();
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vehicleViewModel = new ViewModelProvider(this,getDefaultViewModelProviderFactory()).get(VehiclesViewModel.class);
        ready();
    }
    private void ready(){
        if (binding != null && vehicleViewModel != null){
            binding.setLifecycleOwner(this);
            vehicleViewModel.getVehicle().observe(getViewLifecycleOwner(), vehicle -> {
                txtName.setText(vehicle.name);
                txtModel.setText(vehicle.model);
            });
            binding.setViewmodel(vehicleViewModel);
        }
    }
}
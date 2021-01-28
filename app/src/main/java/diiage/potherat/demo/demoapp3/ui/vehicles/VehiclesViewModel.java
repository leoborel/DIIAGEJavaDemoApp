package diiage.potherat.demo.demoapp3.ui.vehicles;
import android.util.Log;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.dal.repository.SWRepository;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiErrorResponse;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiSuccessResponse;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

public class VehiclesViewModel extends ViewModel {
    QuoteRepository quoteRepository;
    ExecutorService executorService;
    LiveData<Vehicle> vehicle;
    @Inject
    @ViewModelInject
    public VehiclesViewModel(ExecutorService executorService, QuoteRepository quoteRepository,  SWRepository swRepository) {
        this.quoteRepository = quoteRepository;
        this.executorService = executorService;
        vehicle = Transformations.map(swRepository.getVehicle(4), input -> {
                    if(input instanceof ApiSuccessResponse) {
                        return ((ApiSuccessResponse<Vehicle>) input).getBody();
                    } else if (input instanceof ApiErrorResponse){
                        Log.e("debug",((ApiErrorResponse<Vehicle>) input).getErrorMessage()+"");
                    }
                    return null;
                }
        );
    }
    public LiveData<Vehicle> getVehicle() {
        return vehicle;
    }
}
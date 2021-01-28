package diiage.potherat.demo.demoapp3.ui.home;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.common.Event;
import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.model.Quote;

public class HomeViewModel extends ViewModel {

    QuoteRepository quoteRepository;
    ExecutorService executorService;

    @Inject
    @ViewModelInject
    public HomeViewModel(ExecutorService executorService, QuoteRepository quoteRepository){
        this.quoteRepository = quoteRepository;
        this.executorService = executorService;
    }

    public LiveData<Integer> getCountQuote() {
        return quoteRepository.getCountQuote();
    }
}
package rui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private MutableLiveData<String> liveData = new MutableLiveData<>();

    public MutableLiveData<String> getLiveData() {
        return liveData;
    }

    public void setLiveData(MutableLiveData<String> liveData) {
        this.liveData = liveData;
    }
}

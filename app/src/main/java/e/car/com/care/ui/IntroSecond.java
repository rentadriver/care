package e.car.com.care.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import agency.tango.materialintroscreen.SlideFragment;
import e.car.com.care.R;

public class IntroSecond extends SlideFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.whoarewe,container,false);
    }

    @Override
    public int backgroundColor() {
        return R.color.colorAccent;
    }

    @Override
    public int buttonsColor() {
        return R.color.colorPrimaryDark;
    }
}

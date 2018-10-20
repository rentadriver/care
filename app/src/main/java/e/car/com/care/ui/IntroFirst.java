package e.car.com.care.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import agency.tango.materialintroscreen.SlideFragment;
import e.car.com.care.R;

public class IntroFirst extends SlideFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_intro_screen,container,false);
        return view;
    }

    @Override
    public int backgroundColor() {
        return R.color.colorPrimary;
    }

    @Override
    public int buttonsColor() {
        return R.color.colorPrimaryDark;
    }

    @Override
    public boolean hasAnyPermissionsToGrant() {
        return super.hasAnyPermissionsToGrant();
    }

    @Override
    public boolean hasNeededPermissionsToGrant() {
        return super.hasNeededPermissionsToGrant();
    }

    @Override
    public boolean canMoveFurther() {
        return super.canMoveFurther();
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        return super.cantMoveFurtherErrorMessage();
    }

    @Override
    public void askForPermissions() {
        super.askForPermissions();
    }
}

package e.car.com.care;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragment;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import e.car.com.care.ui.FinalScrene;
import e.car.com.care.ui.IntroFirst;
import e.car.com.care.ui.IntroSecond;

public class IntroScreen extends MaterialIntroActivity {

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        addSlide(new IntroFirst());

        addSlide(new IntroSecond());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimary)
                .buttonsColor(R.color.colorPrimaryDark)
                .image(R.drawable.logo)
                .title("Car.e")
                .description("Want to join us?")
                .build(),new MessageButtonBehaviour(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shatredPrefs = ((MaterialIntroActivity)mContext).getPreferences(MODE_PRIVATE);
                String registerid = shatredPrefs.getString(getResources().getString(R.string.register),"");
                if (registerid.length()==0) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(),RegisterCompleteActivity.class);
                    intent.putExtra("isRegistered",1);
                    startActivity(intent);
                }
            }
        },"Register now."));

//        addSlide(new SlideFragmentBuilder()
//        .backgroundColor(R.color.colorPrimary)
//        .title("Register now")
//        .build(),new MessageButtonBehaviour(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        },"Click to register"));
    }

}

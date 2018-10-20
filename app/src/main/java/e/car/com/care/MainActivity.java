package e.car.com.care;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.ref.SoftReference;
import java.util.Calendar;
import java.util.Date;

import e.car.com.care.data.RegisterData;

public class MainActivity extends AppCompatActivity {

    Spinner experiance,district,familiar;
    EditText name,number,vphone,email,license,address,adhar;
    Button submit;

    RegisterData data=null;

    boolean isvalid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        experiance = findViewById(R.id.experiance);
        district = findViewById(R.id.district);
        familiar = findViewById(R.id.familiar);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        vphone = findViewById(R.id.v_phone);
        email = findViewById(R.id.email);
        license = findViewById(R.id.licens);
        address = findViewById(R.id.address);
        adhar = findViewById(R.id.adhar);

        submit = findViewById(R.id.register);

        SetSpinnerAdapters();
        SetTextChangeListners();
        SetButtonClickListeners();


    }

    private void SetTextChangeListners() {
        try{
            vphone.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().length()==10) {
                        if (s.toString().equals(number.getText().toString())) {
                            isvalid = true;
                        } else {
                            isvalid = false;
                            vphone.setError("Number missmatch.");
                        }
                    }
                    else {
                        isvalid = false;
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void SetButtonClickListeners() {
        try{
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ValidateData();
                    if (isvalid){
                        SaveData();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Please check your mobile number",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void SaveData() {
        try{
            final String collectionId = String.valueOf(new Date().getTime());
            FirebaseFirestore firestoredb = FirebaseFirestore.getInstance();

                    DocumentReference docref = firestoredb.collection("Users").document("Drivers");
                    docref.collection(collectionId).add(data)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    AfterRegister(collectionId);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    e.printStackTrace();
                                }
                            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void AfterRegister(String collectionId) {
        try{
            SharedPreferences shatredPrefs = this.getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = shatredPrefs.edit();
            editor.putString(getResources().getString(R.string.register),collectionId);
            editor.apply();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void ValidateData() {
        data = new RegisterData();
        try{
            if (name.getText()!=null && name.getText().length()>0){
                data.setName(name.getText().toString());
            }
            else {
                name.setError("Please enter your name");
            }

            if (number.getText()!=null && name.getText().length()>0){
                data.setNumber(number.getText().toString());
            }
            else {
                number.setError("We need your contact number");
            }
            if (email.getText()!=null && email.getText().length()>0){
                data.setEmail(email.getText().toString());
            }
            else {
                email.setError("Your email is required");
            }
            if (license.getText()!=null&&license.getText().length()>0){
                data.setLicense(license.getText().toString());
            }
            else {
                license.setError("This field is required");
            }
            if (address.getText()!=null && address.getText().length()>0){
                data.setAddress(address.getText().toString());
            }
            else {
                address.setError("Please provide your address");
            }
            if (adhar.getText()!=null&& adhar.getText().length()>0){
                adhar.setError("Provide your ADHAAR info");
            }
            else {
                adhar.setError("This information is required");
            }
            if (experiance.isSelected()){
                data.setExperiance(experiance.getSelectedItem().toString());
            }
            else {
                Toast.makeText(getApplicationContext(),"Please choose your years of experiance",Toast.LENGTH_SHORT).show();
            }
            if (district.isSelected()){
                data.setDistrict(district.getSelectedItem().toString());
            }
            else{
                Toast.makeText(getApplicationContext(),"Please choose your district",Toast.LENGTH_SHORT).show();
            }

            if (familiar.isSelected()){
                data.setFamiliar(familiar.getSelectedItem().toString());
            }
            else {
                Toast.makeText(getApplicationContext(),"Please provide your experiance details",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void SetSpinnerAdapters() {
        try{
            ArrayAdapter<CharSequence> experianceAdapter = ArrayAdapter.createFromResource(this,R.array.experiance,android.R.layout.simple_spinner_item);
            experianceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            experiance.setAdapter(experianceAdapter);
            experiance.setPrompt("Your experiance as a driver");

            ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,R.array.district,android.R.layout.simple_spinner_item);
            experianceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            district.setAdapter(districtAdapter);
            district.setPrompt("District");


            ArrayAdapter<CharSequence> familiarAdapter = ArrayAdapter.createFromResource(this,R.array.familiar,android.R.layout.simple_spinner_item);
            experianceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            familiar.setAdapter(familiarAdapter);
            familiar.setPrompt("Familiar");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

package com.example.phonenoverificationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.phonenoverificationfirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.spinnercountry.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        binding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[binding.spinnercountry.getSelectedItemPosition()];

                String number = binding.editTextMobile.getText().toString().trim();

                if (number.isEmpty() || number.length() < 11) {
                    binding.editTextMobile.setError("Valid number is required");
                    binding.editTextMobile.requestFocus();
                    return;
                }

                String phoneNumber = "+" + code + number;

                Intent intent = new Intent(MainActivity.this, VerifyPhone_Activity.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);
            }
        });

    }
}

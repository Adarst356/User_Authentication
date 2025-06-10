package com.devdroid.task6.Fragment;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.devdroid.task6.Database.AppDatabase;
import com.devdroid.task6.Database.UserDao;
import com.devdroid.task6.Model.User;
import com.devdroid.task6.R;

public class RegisterFragment extends Fragment {

    EditText fullName, email, etmobile, address, password, confirmPassword;
    TextView tvFullNameError, tvEmailError, tvPasswordError, tvConfirmPasswordError;
    Button createAccountBtn;
    TextView tvLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Initialize views
        fullName = view.findViewById(R.id.fullName);
        email = view.findViewById(R.id.email);
        etmobile = view.findViewById(R.id.etmobile);
        address = view.findViewById(R.id.Address);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirmPassword);

        // Error TextViews
        tvFullNameError = view.findViewById(R.id.tvFullNameError);
        tvEmailError = view.findViewById(R.id.tvEmailError);
        tvPasswordError = view.findViewById(R.id.tvPasswordError);
        tvConfirmPasswordError = view.findViewById(R.id.tvConfirmPasswordError);

        createAccountBtn = view.findViewById(R.id.createAccountBtn);
        tvLogin = view.findViewById(R.id.tvSignin);

        createAccountBtn.setOnClickListener(v -> {
            String name = fullName.getText().toString().trim();
            String emailInput = email.getText().toString().trim();
            String mobileInput = etmobile.getText().toString().trim();
            String addressInput = address.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();

            boolean isValid = true;

            // Hide previous errors
            tvFullNameError.setVisibility(View.GONE);
            tvEmailError.setVisibility(View.GONE);
            tvPasswordError.setVisibility(View.GONE);
            tvConfirmPasswordError.setVisibility(View.GONE);
            etmobile.setError(null);
            address.setError(null);


            // Validations
            if (name.isEmpty()) {
                tvFullNameError.setText("Full name is required");
                tvFullNameError.setVisibility(View.VISIBLE);
                isValid = false;
            }

            if (emailInput.isEmpty()) {
                tvEmailError.setText("Email is required");
                tvEmailError.setVisibility(View.VISIBLE);
                isValid = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                tvEmailError.setText("Invalid email");
                tvEmailError.setVisibility(View.VISIBLE);
                isValid = false;
            }

            if (mobileInput.isEmpty()) {
                etmobile.setError("Mobile number is required");
                isValid = false;
            } else if (!mobileInput.matches("\\d{10}")) {
                etmobile.setError("Mobile number must be 10 digits");
                isValid = false;
            }

            if (addressInput.isEmpty()) {
                address.setError("Address is required");
                isValid = false;
            }

            if (pass.isEmpty()) {
                tvPasswordError.setText("Password is required");
                tvPasswordError.setVisibility(View.VISIBLE);
                isValid = false;
            } else if (pass.length() < 6) {
                tvPasswordError.setText("Password must be at least 6 characters");
                tvPasswordError.setVisibility(View.VISIBLE);
                isValid = false;
            }

            if (confirmPass.isEmpty()) {
                tvConfirmPasswordError.setText("Please confirm your password");
                tvConfirmPasswordError.setVisibility(View.VISIBLE);
                isValid = false;
            } else if (!confirmPass.equals(pass)) {
                tvConfirmPasswordError.setText("Passwords do not match");
                tvConfirmPasswordError.setVisibility(View.VISIBLE);
                isValid = false;
            }

            // Save to Room if valid
            if (isValid) {
                AppDatabase db = AppDatabase.getInstance(getContext());
                UserDao userDao = db.userDao();

                User existingUser = userDao.getUserByEmail(emailInput);
                if (existingUser != null) {
                    Toast.makeText(getContext(), "Email is already registered", Toast.LENGTH_SHORT).show();
                    return;
                }

                User newUser = new User(name, emailInput, mobileInput, addressInput, pass);
                userDao.insertUser(newUser);

                Toast.makeText(getContext(), "Registration successful", Toast.LENGTH_SHORT).show();

                // Clear all fields
                fullName.setText("");
                email.setText("");
                etmobile.setText("");
                address.setText("");
                password.setText("");
                confirmPassword.setText("");

                // Switch to Login tab
                ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
                if (viewPager != null) {
                    viewPager.setCurrentItem(0, true);
                }
            }
        });

        tvLogin.setOnClickListener(v -> {
            ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
            if (viewPager != null) {
                viewPager.setCurrentItem(0, true);
            }
        });

        return view;
    }
}

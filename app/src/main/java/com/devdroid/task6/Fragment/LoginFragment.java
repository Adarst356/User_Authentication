package com.devdroid.task6.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.devdroid.task6.Activity.DashboardActivity;
import com.devdroid.task6.Database.AppDatabase;
import com.devdroid.task6.Database.UserDao;
import com.devdroid.task6.Model.User;
import com.devdroid.task6.R;

public class LoginFragment extends Fragment {

    EditText etEmail, etPassword;
    TextView tvForgot, tvRegister;
    TextView tvEmailError, tvPasswordError;
    Button btnLogin;
    ImageView ivTogglePassword;
    boolean isPasswordVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Bind views
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        tvEmailError = view.findViewById(R.id.tvEmailError);
        tvPasswordError = view.findViewById(R.id.tvPasswordError);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvForgot = view.findViewById(R.id.tvForgot);
        tvRegister = view.findViewById(R.id.tvRegister);
        ivTogglePassword = view.findViewById(R.id.ivTogglePassword);

        // Password visibility toggle
        ivTogglePassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                ivTogglePassword.setImageResource(R.drawable.ic_eye_closed);
            } else {
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                ivTogglePassword.setImageResource(R.drawable.ic_eye_open);
            }
            isPasswordVisible = !isPasswordVisible;
            etPassword.setSelection(etPassword.getText().length());
        });

        // Login button logic
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            boolean valid = true;

            tvEmailError.setVisibility(View.GONE);
            tvPasswordError.setVisibility(View.GONE);

            if (email.isEmpty()) {
                tvEmailError.setText("Email is required");
                tvEmailError.setVisibility(View.VISIBLE);
                valid = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tvEmailError.setText("Invalid email format");
                tvEmailError.setVisibility(View.VISIBLE);
                valid = false;
            }

            if (password.isEmpty()) {
                tvPasswordError.setText("Password is required");
                tvPasswordError.setVisibility(View.VISIBLE);
                valid = false;
            } else if (password.length() < 6) {
                tvPasswordError.setText("Minimum 6 characters");
                tvPasswordError.setVisibility(View.VISIBLE);
                valid = false;
            }

            if (valid) {
                AppDatabase db = AppDatabase.getInstance(getContext());
                UserDao userDao = db.userDao();

                User user = userDao.login(email, password);
                if (user != null) {
                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(requireActivity(), DashboardActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                } else {
                    Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Switch to Register tab
        tvRegister.setOnClickListener(v -> {
            ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
            if (viewPager != null) {
                viewPager.setCurrentItem(1, true);
            }
        });

        return view;
    }
}

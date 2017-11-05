package com.fbla.jonat.e_parc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity {
    private EditText mNameView;
    private EditText mPhoneNumberView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mCarBrandView;
    private EditText mLicensePlateView;
    private EditText mCreditCardNumberView;
    private EditText mCVVNumberView;
    private View mProgressView;
    private View mRegisterFormView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mNameView = (EditText) findViewById(R.id.name);
        mPhoneNumberView = (EditText) findViewById(R.id.phoneNumber);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mCarBrandView = (EditText) findViewById(R.id.carBrand);
        mLicensePlateView = (EditText) findViewById(R.id.licensePlate);
        mCreditCardNumberView = (EditText) findViewById(R.id.creditCardNumber);
        mCVVNumberView = (EditText) findViewById(R.id.CVVNumber);
        mRegisterFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.register_progress);

        Button signInButton = (Button) findViewById(R.id.registerButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
    }

    private void attemptRegister() {
        // Reset errors.
        mNameView.setError(null);
        mPhoneNumberView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mCarBrandView.setError(null);
        mLicensePlateView.setError(null);
        mCreditCardNumberView.setError(null);
        mCVVNumberView.setError(null);

        // Store values at the time of the register attempt.
        String name = mNameView.getText().toString();
        String phoneNumber = mPhoneNumberView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String carBrand = mCarBrandView.getText().toString();
        String licensePlate = mLicensePlateView.getText().toString();
        String creditCardNumber = mCreditCardNumberView.getText().toString();
        String CVVNumber = mCVVNumberView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //Check for name
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }

        // Check for valid phone number
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberView.setError(getString(R.string.error_field_required));
            focusView = mPhoneNumberView;
            cancel = true;
        } else if (!isPhoneNumberValid(phoneNumber)){
            mPhoneNumberView.setError(getString(R.string.error_invalid_phone_number));
            focusView = mPhoneNumberView;
            cancel = true;
        }

        // Check for a valid password
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        // Check for a valid car brand address.
        if (TextUtils.isEmpty(carBrand)) {
            mCarBrandView.setError(getString(R.string.error_field_required));
            focusView = mCarBrandView;
            cancel = true;
        } else if (!isCarBrandValid(carBrand)) {
            mCarBrandView.setError(getString(R.string.error_invalid_car_brand));
            focusView = mCarBrandView;
            cancel = true;
        }

        // Check for a valid license plate
        if (TextUtils.isEmpty(licensePlate)) {
            mLicensePlateView.setError(getString(R.string.error_field_required));
            focusView = mLicensePlateView;
            cancel = true;
        }

        // Check for a valid credit card number.
        if (TextUtils.isEmpty(creditCardNumber)) {
            mCreditCardNumberView.setError(getString(R.string.error_field_required));
            focusView = mCreditCardNumberView;
            cancel = true;
        } else if (!isCreditCardNumberValid(creditCardNumber)) {
            mCreditCardNumberView.setError(getString(R.string.error_invalid_credit_card_number));
            focusView = mCreditCardNumberView;
            cancel = true;
        }

        // Check for a valid CVV number.
        if (TextUtils.isEmpty(CVVNumber)) {
            mCVVNumberView.setError(getString(R.string.error_field_required));
            focusView = mCVVNumberView;
            cancel = true;
        } else if (!isCVVNumberValid(CVVNumber)) {
            mCVVNumberView.setError(getString(R.string.error_invalid_cvv_number));
            focusView = mCVVNumberView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            LoginActivity.dummyCredentials.add(mEmailView.getText().toString() + ":" + mPasswordView.getText().toString());
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        phoneNumber.replaceAll("\\s+","");
        return phoneNumber.length() == 10;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        if (TextUtils.isEmpty(email)) {
            return false;
        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return false;
        } else if(!email.contains("@gmail.com") && !email.contains("@hotmail.com") && !email.contains("@live.com") &&
                !email.contains("@outlook.com") && !email.contains("@inbox.com") && !email.contains("@mail.com") &&
                !email.contains("@aol.com") && !email.contains("@zoho.com") && !email.contains("@yandex.com")){
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 3;
    }

    private boolean isCarBrandValid(String brand) {
        //TODO: Replace this with your own logic
        if(brand.equalsIgnoreCase("Acura") || brand.equalsIgnoreCase("Alfa Romeo") || brand.equalsIgnoreCase("Aston Martin")
                || brand.equalsIgnoreCase("Audi") || brand.equalsIgnoreCase("Bentley") || brand.equalsIgnoreCase("BMW") ||
                brand.equalsIgnoreCase("Bugatti") || brand.equalsIgnoreCase("Cadillac") || brand.equalsIgnoreCase("Chevrolet") ||
                brand.equalsIgnoreCase("Chrysler") || brand.equalsIgnoreCase("Dodge") || brand.equalsIgnoreCase("Ferrari") ||
                brand.equalsIgnoreCase("Fiat") || brand.equalsIgnoreCase("Ford") || brand.equalsIgnoreCase("General Motors") ||
                brand.equalsIgnoreCase("Honda") || brand.equalsIgnoreCase("Hyundai") || brand.equalsIgnoreCase("Infiniti") ||
                brand.equalsIgnoreCase("Jaguar") || brand.equalsIgnoreCase("Jeep") || brand.equalsIgnoreCase("Lamborghini") ||
                brand.equalsIgnoreCase("Land Rover") || brand.equalsIgnoreCase("Lexus") || brand.equalsIgnoreCase("Maserati") ||
                brand.equalsIgnoreCase("Mazda") || brand.equalsIgnoreCase("Mercedes-Benz") || brand.equalsIgnoreCase("Mitsubishi") ||
                brand.equalsIgnoreCase("Nissan") || brand.equalsIgnoreCase("Porsche") || brand.equalsIgnoreCase("Ram") ||
                brand.equalsIgnoreCase("Rolls Royce") || brand.equalsIgnoreCase("Subaru") || brand.equalsIgnoreCase("Suzuki") ||
                brand.equalsIgnoreCase("Tesla") || brand.equalsIgnoreCase("Toyota") || brand.equalsIgnoreCase("Volkswagon") ||
                brand.equalsIgnoreCase("Volvo")){
            return true;
        } else {
            return false;
        }
    }

    private boolean isCreditCardNumberValid(String creditCardNumber){
        creditCardNumber.replaceAll("\\s+","");
        return creditCardNumber.length() == 16;
    }

    private boolean isCVVNumberValid(String CVVNumber){
        CVVNumber.replaceAll("\\s+","");
        return CVVNumber.length() == 3;
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

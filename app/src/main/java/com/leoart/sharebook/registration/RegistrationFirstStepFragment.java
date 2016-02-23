package com.leoart.sharebook.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.leoart.sharebook.R;

public class RegistrationFirstStepFragment extends Fragment implements RegistrationViewFirstStep {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "RegistFirstStepFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistrationFirstStepFragment() {
        // Required empty public constructor
    }

    private EditText et_email;
    private EditText et_password;
    private EditText confirmPassword;
    private Button btn_next;

    private RegistrationPresenter presenter;
    private OnRegistrationFirstStepInteractionListener listener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationStepOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFirstStepFragment newInstance(String param1, String param2) {
        RegistrationFirstStepFragment fragment = new RegistrationFirstStepFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        presenter = new RegistrationPresenter(getActivity().getBaseContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration_step_one, container, false);

        initUI(view);

        return view;
    }

    private void initUI(View view) {
        et_email = (EditText) view.findViewById(R.id.email);
        et_password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirm_password);
        btn_next = (Button) view.findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateCredentials(et_email.getText().toString(), et_password.getText().toString(), confirmPassword.getText().toString());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegistrationFirstStepInteractionListener) {
            listener = (OnRegistrationFirstStepInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegistrationFirstStepInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void showEmailError(String response) {
        et_email.setError(response);
    }

    @Override
    public void showPasswordError(String response) {
        et_password.setError(response);
    }

    @Override
    public void showConfirmPasswordError(String response) {
        confirmPassword.setError(response);
    }

    @Override
    public void showLicenseAcceptError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void goToNextStep() {
        Log.d(TAG, "goToNextStep");
        if(listener!=null){
            listener.onFirstRegistrationSuccess();
        }
    }

    public interface OnRegistrationFirstStepInteractionListener {
        void onFirstRegistrationSuccess();
    }
}

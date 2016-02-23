package com.leoart.sharebook.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.leoart.sharebook.R;
import com.leoart.sharebook.data.DemoDataProvider;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationSecondStepFragment extends Fragment implements RegistrationViewSecondStep{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.et_first_name) EditText et_first_name;
    @Bind(R.id.et_last_name) EditText et_last_name;
    @Bind(R.id.et_phone_number) EditText et_phone_number;
    @Bind(R.id.btn_get_started) Button btn_get_started;
    @Bind(R.id.country_spinner) AppCompatSpinner country_spinner;


    private OnRegSecondStepInteractionListener mListener;
    private RegistrationPresenter presenter;

    public RegistrationSecondStepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationStepTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationSecondStepFragment newInstance(String param1, String param2) {
        RegistrationSecondStepFragment fragment = new RegistrationSecondStepFragment();
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
        View view = inflater.inflate(R.layout.fragment_registration_step_two, container, false);
        ButterKnife.bind(this, view);



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, DemoDataProvider.genCountriest());
        country_spinner.setAdapter(dataAdapter);
        country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @OnClick(R.id.btn_get_started)
    public void submit(){
        presenter.validateCredentials(et_first_name.getText().toString(), et_last_name.getText().toString(), "country", "city", "phone", "pathAvatar");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegSecondStepInteractionListener) {
            mListener = (OnRegSecondStepInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showAvatarError() {

    }

    @Override
    public void showFirstNameError(String response) {

    }

    @Override
    public void showLastNameError() {

    }

    @Override
    public void showCountryError() {

    }

    @Override
    public void showCityError() {

    }

    @Override
    public void showPhoneNumberError() {

    }

    @Override
    public void getStarted() {
        if(mListener!=null){
            mListener.onRegistrationDone();
        }
    }


    public interface OnRegSecondStepInteractionListener {
        void onRegistrationDone();
    }
}

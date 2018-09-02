package masterung.androidthai.in.th.tutornop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import masterung.androidthai.in.th.tutornop.R;
import masterung.androidthai.in.th.tutornop.utility.MyAlert;

public class RegisterFragment extends Fragment{

    private String nameString, emailString, passwordString, rePasswordString, idCardString;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Student Controller
        studentController();

//        Teacher Controller
        teacherController();


    }   // Main Method

    private void teacherController() {
        Button button = getView().findViewById(R.id.btnTeacher);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData("Teacher");
            }
        });
    }

    private void studentController() {
        Button button = getView().findViewById(R.id.btnStudent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData("Student");
            }
        });
    }

    private void checkData(String childString) {

//        Get Value from Edittext to String
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText idCardEditText = getView().findViewById(R.id.edtIDcard);
        EditText emailEditText = getView().findViewById(R.id.edtIDcard);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);
        EditText rePasswordEditText = getView().findViewById(R.id.edtRePassword);

        nameString = nameEditText.getText().toString().trim();
        idCardString = idCardEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        rePasswordString = rePasswordEditText.getText().toString().trim();

        MyAlert myAlert = new MyAlert(getActivity());

        if (nameString.isEmpty() ||
                idCardString.isEmpty() ||
                emailString.isEmpty() ||
                passwordString.isEmpty() ||
                rePasswordString.isEmpty()) {
//            Have Space
            myAlert.normalDialog("Have Space",
                    "Please Fill all Blank");
        } else if (idCardString.length() != 13) {
            myAlert.normalDialog("ID Card False",
                    "Please fill ID Card 13 Digi");
        } else if (!passwordString.equals(rePasswordString)) {
//            Password False
            myAlert.normalDialog("Password Not Match",
                    "Please Fill Password Matching");
        } else {
//            Password True


        }








    }   // checkData

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}

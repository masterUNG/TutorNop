package masterung.androidthai.in.th.tutornop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import masterung.androidthai.in.th.tutornop.R;
import masterung.androidthai.in.th.tutornop.utility.MyAlert;
import masterung.androidthai.in.th.tutornop.utility.MyModel;

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

    private void checkData(final String childString) {

//        Get Value from Edittext to String
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText idCardEditText = getView().findViewById(R.id.edtIDcard);
        EditText emailEditText = getView().findViewById(R.id.edtEmail);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);
        EditText rePasswordEditText = getView().findViewById(R.id.edtRePassword);

        nameString = nameEditText.getText().toString().trim();
        idCardString = idCardEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        rePasswordString = rePasswordEditText.getText().toString().trim();

        final MyAlert myAlert = new MyAlert(getActivity());

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

            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest
                                        .Builder().setDisplayName(nameString).build();
                                firebaseUser.updateProfile(userProfileChangeRequest)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("2SepV1", "DisplayName ==> " + firebaseUser.getDisplayName());
                                                insertValueDatabase(firebaseUser.getUid(), childString);
                                            }
                                        });

                            } else {
                                myAlert.normalDialog("Cannot Register",
                                        task.getException().getMessage().toString());
                            }
                        }
                    });



        }   //if

    }   // checkData

    private void insertValueDatabase(String uidString, String childString) {

        MyModel myModel = new MyModel(nameString, idCardString, uidString);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child(childString);
        databaseReference.child(uidString).setValue(myModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentMainFragment, new MainFragment())
                                .commit();

                    }
                });


    }   // insert

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}

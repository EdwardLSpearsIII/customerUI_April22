package com.example.admin1.firstlogin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class signUpActivity extends AppCompatActivity {

    ImageView UserPhoto;
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    Uri pickedImgUri ;

    private EditText firstname, userlName, userEmail, useruName, userPassword, userNoCollege;
    private Button Signup;

    private FirebaseAuth mAuth;

    //DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //userfName = findViewById(R.id.f_name);
        userlName = findViewById(R.id.l_name);
        userEmail = findViewById(R.id.email);
        useruName = findViewById(R.id.u_name);
        userPassword = findViewById(R.id.password);
        userNoCollege = findViewById(R.id.school);
        Signup = findViewById(R.id.Signup);
        firstname = findViewById(R.id.f_name);
        //userphoto = findViewById(R.id.addUserPhoto);
        //databaseUsers = FirebaseDatabase.getInstance().getReference("Users");



        mAuth = FirebaseAuth.getInstance();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String fName = firstname.getText().toString();
                final String lName = userlName.getText().toString();
                final String email = userEmail.getText().toString();
                final String uName = useruName.getText().toString();
                final String password = userPassword.getText().toString();
                final String school = userNoCollege.getText().toString();


                if( email.isEmpty() || fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || password.isEmpty() || school.isEmpty()){

                    //something goes wrong: all fields must be filled
                    // we need to display error message
                    showMessage("Please Verify all fields");
                    Signup.setVisibility(View.VISIBLE);

                }
                else {
                    // everything is ok and all fields are filled now we can start creating user account
                    //CreateUserAccount mehtod will try to create the user if the email is valid

                    CreateUserAccount(email,fName,password);



                    //showMessage("all fields correct");

                }





            }
        }); /*private void addUser () {
        String fname = firstname.getText().toString().trim();
        String lName = userlName.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String uName = useruName.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        String school = userNoCollege.getText().toString().trim();

        if(!TextUtils.isEmpty(fname) || !TextUtils.isEmpty(lName) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(uName) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(school)){

            String id = databaseUsers.push().getKey();

            User user = new User(id, fname, lName, email, uName, password, school,currentUser.getPhotoUrl().toString()););


            databaseUsers.child(id).setValue(user);


        }
        else {
            Toast.makeText(this, "Verify all Fields", Toast.LENGTH_LONG).show();
        }


    }*/
        UserPhoto = findViewById(R.id.regUserPhoto) ;

        UserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                }
                else
                {
                    openGallery();
                }





            }
        });
    }


    private void CreateUserAccount(String email, final String fName, String password) {

        //this method create user account

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //user account created successfully
                            showMessage("Account created");
                            //updateUI();
                            //addUser();
                            //FirebaseUser user = mAuth.getCurrentUser();
                            updateUserInfo( fName ,pickedImgUri,mAuth.getCurrentUser());

                        }
                        else{


                            //account creation failed
                            showMessage("account creation failed" + task.getException().getMessage());


                        }
                    }
                });
    }
    private void updateUserInfo(final String fname, Uri pickedImgUri, final FirebaseUser currentUser) {

        // first we need to upload user photo to firebase storage and get url

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // image uploaded succesfully
                // now we can get our image url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        // uri contain user image url


                        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(fname)
                                .setPhotoUri(uri)
                                .build();


                        currentUser.updateProfile(profleUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            // user info updated successfully
                                            //TextView Username = findViewById(R.id.school);
                                            //TextView photourl = findViewById(R.id.username);
                                            //Username.setText(currentUser.getEmail());
                                            //photourl.setText(currentUser.getPhotoUrl().toString());
                                            showMessage("Register Complete");
                                            updateUI();
                                        }


                                        else {

                                            showMessage("account creation failed" + task.getException().getMessage());

                                        }

                                    }
                                });

                    }
                });





            }
        });






    }



    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(),navigationbar.class);
        startActivity(homeActivity);
        finish();

    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();


    }

    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(signUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(signUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(signUpActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(signUpActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            UserPhoto.setImageURI(pickedImgUri);


        }


    }
}

package com.keeyoshi.foodmandu.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.keeyoshi.foodmandu.R;
import com.keeyoshi.foodmandu.StrictModeClass;
import com.keeyoshi.foodmandu.api.UserApi;
import com.keeyoshi.foodmandu.model.User;
import com.keeyoshi.foodmandu.serverresponse.ImageResponse;
import com.keeyoshi.foodmandu.serverresponse.SignUpResponse;
import com.keeyoshi.foodmandu.url.Url;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    CircleImageView CircleImg;
    EditText etFname,etLname,etPhnum,etUser,etPass,etCpass;
    Button btnSignup, btnBack;
    String imagePath;
    private String imageName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etFname=findViewById(R.id.etFname);
        etLname=findViewById(R.id.etLname);
        etPhnum=findViewById(R.id.etPhnum);
        etUser=findViewById(R.id.etUser);
        etPass=findViewById(R.id.etPass);
        etCpass=findViewById(R.id.etCpass);
        CircleImg=findViewById(R.id.CircleImg);
        btnSignup=findViewById(R.id.btnSignup);
        btnBack=findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPass.getText().toString().equals((etCpass.getText().toString())))
                {
                    saveImageOnly();
                    signup();
                }

                else {
                    Toast.makeText(SignupActivity.this, "Password doesnot match", Toast.LENGTH_SHORT).show();
                    etPass.requestFocus();
                    return;
                }
            }
        });

        CircleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BrowseImage();
            }
        });

    }


    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        CircleImg.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }


    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile",
                file.getName(), requestBody);

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void signup(){
        ///declaration
        String fname= etFname.getText().toString();
        String lname= etLname.getText().toString();
        String pnum=etPhnum.getText().toString();
        String username= etUser.getText().toString();
        String password = etPass.getText().toString();


        User user= new User(fname,lname,pnum,username,password,imageName);

        UserApi userapi = Url.getInstance().create(UserApi.class);

        Call<SignUpResponse> signUpResponseCall= userapi.registerUser(user);

        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Toast.makeText(SignupActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

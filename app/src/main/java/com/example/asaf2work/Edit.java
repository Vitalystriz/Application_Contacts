package com.example.asaf2work;

import static com.example.asaf2work.R.id.etName;

import android.app.Activity;
import android.content.Intent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

public class Edit extends AppCompatActivity implements View.OnClickListener {


    EditText etName, etPhoneNumber;
    Button btnSave, btnCancel;
    ImageView ivProduct;
    Bitmap bitmap;
    Button btnTakePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etName = findViewById(R.id.etName);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        ivProduct = findViewById(R.id.ivProduct);
        btnTakePic = findViewById(R.id.btnPic);
        btnTakePic.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            String Name = intent.getExtras().getString("Name");
            String PhoneNumber = intent.getExtras().getString("PhoneNumber");
            bitmap = Helper.byteArrayToBitmap(intent.getExtras().getByteArray("bitmap"));
            etName.setText(Name);
            etPhoneNumber.setText(PhoneNumber);
            ivProduct.setImageBitmap(bitmap);
        }
    }
    @Override
    public void onClick(View v)
    {
        if(btnTakePic==v)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,0);
        }
        else if(btnSave==v)
        {
            if(etName.getText().toString().length()>0&&
                    etPhoneNumber.getText().toString().length()>0 &&bitmap!=null) {
                Intent intent = new Intent();
                intent.putExtra("Name", etName.getText().toString());
                intent.putExtra("PhoneNumber", etPhoneNumber.getText().toString());
                intent.putExtra("bitmap", Helper.bitmapToByteArray(bitmap));
                setResult(RESULT_OK, intent);
                finish();

            }
            else
                Toast.makeText(this,"please fill all fields", Toast.LENGTH_LONG).show();
        }
        else if (btnCancel==v)
        {
            setResult(RESULT_CANCELED,null);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0)
        {
            if(resultCode==RESULT_OK)
            {
                bitmap = (Bitmap)data.getExtras().get("data");
                if(bitmap!=null)
                    ivProduct.setImageBitmap(bitmap);
            }
        }
    }
}
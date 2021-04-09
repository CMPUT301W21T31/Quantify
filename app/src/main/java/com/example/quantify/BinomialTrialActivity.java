package com.example.quantify;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;
import java.util.UUID;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class BinomialTrialActivity extends AppCompatActivity {

    public static final String QRPath = "null";
    Experiment exp;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    TextView result;

    private double latitude;
    private double longitude;

    Button save;
    Button generateQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binomial_trial);

        Intent intent = getIntent();
        exp = (Experiment) getIntent().getSerializableExtra("Experiment");

        expDesc = findViewById(R.id.bTrialDescriptionView);
        userID = findViewById(R.id.bTrialUserIDView);
        minTrials = findViewById(R.id.bMinTrialView);
        result = findViewById(R.id.bResultValue);
        generateQR = findViewById(R.id.bTrialGenerateQRCodeButton);



        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());

        String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
        String TAG = "GenerateQRCode";
        ImageView img_QRCode;
        QRGEncoder qrgEncoder;
        UUID thisTrialID;
        UUID thisExperimenterID;
        String thisBinomialResult;
        Double thisCountResult;
        generateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = "Hello";
                try{
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(inputValue, BarcodeFormat.QR_CODE,400,400);
                    //img_qrcode.setImageBitmap(bitmap);
                    boolean save;
                    String result;
                    try {
                        save = QRGSaver.save(savePath, "Test1", bitmap, QRGContents.ImageType.IMAGE_JPEG);
                        String realPath = savePath.toString() + "Test1";
                        result = save ? "Image Saved" : "Image Not Saved";
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                        Intent QRIntent = new Intent(BinomialTrialActivity.this, DisplayQRActivity.class);
                        QRIntent.putExtra(QRPath, realPath);
                        startActivity(QRIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void successClicked(View target){
        result.setText("Success");
    }

    public void failClicked(View target){
        result.setText("Fail");
    }


    public void binomialSaveClicked(View target){
        // we give the trial an ID using UUID and save the result in the database

        if(result.getText().toString().equals("Fail") || result.getText().toString().equals("Success")) {
            FirebaseFirestore db;
            db = FirebaseFirestore.getInstance();
            String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            final CollectionReference collectionReference_1 = db.collection("Experiments");
            final DocumentReference documentReference = collectionReference_1.document(exp.getDescription());
            final CollectionReference collectionReference = documentReference.collection("Trials");

            HashMap<String, String> data = new HashMap<>();
            data.put("Trial-Result", result.getText().toString());
            data.put("Experimenter ID", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
            //data.put("Location Latitude", String.valueOf(map.getCurrentLatitude()));
            //data.put("Location Longitude", String.valueOf(map.getCurrentLongitude()));
            UUID Trial_id = UUID.randomUUID();

            collectionReference
                    .document(Trial_id.toString())
                    .set(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // These are a method which gets executed when the task is succeeded
                            Log.d("TAG", "Data has been added successfully!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // These are a method which gets executed if there’s any problem
                            Log.d("TAG", "Data could not be added!" + e.toString());
                        }
                    });


            Log.d("count", "Count: " + result.getText().toString());
        }

        finish();
    }
}
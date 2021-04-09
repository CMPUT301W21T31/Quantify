package com.example.quantify;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.UUID;

import androidmads.library.qrgenearator.QRGEncoder;

public class DisplayQRActivity extends AppCompatActivity {
    ImageView img_QRCode;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String inputValue;
    UUID thisTrialID;
    UUID thisExperimenterID;
    String thisBinomialResult;
    Double thisCountResult;

    public DisplayQRActivity(UUID trialID, UUID experimenterID, String binomialResult) {
        super();
        this.thisTrialID = trialID;
        this.thisExperimenterID = experimenterID;
        this.thisBinomialResult = binomialResult;
    }

    public DisplayQRActivity(UUID trialID, UUID experimenterID, Double countResult) {
        super();
        this.thisTrialID = trialID;
        this.thisExperimenterID = experimenterID;
        this.thisCountResult = countResult;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_qr);

        img_QRCode = findViewById(R.id.img_qr);
        Intent intent = getIntent();
        String path = intent.getStringExtra(BinomialTrialActivity.QRPath);

        File imgFile = new File(path);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            img_QRCode.setImageBitmap(myBitmap);

        }
    }
}

package com.example.addbe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private Button ppay;
    private int PAYPAL_REQ_CODE = 12;
    private static PayPalConfiguration paypalconfig = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PaypalClientIDConfigClass.Clientid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ppay = findViewById(R.id.pay);

       // Intent intent = new Intent(this, PayPalService.class);
       // intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalconfig);
       // startActivity(intent);

        ppay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paypalPayment();

            }
        });
    }
    private void paypalPayment(){
        PayPalPayment payment = new PayPalPayment(new BigDecimal(100),"USD","Test Payment", PayPalPayment.PAYMENT_INTENT_SALE );
        Intent intent = new Intent(this, PayPalPayment.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalconfig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        startActivityForResult(intent, PAYPAL_REQ_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PAYPAL_REQ_CODE){
            if(resultCode== Activity.RESULT_OK){
                Toast.makeText(this, "Payment made successifully", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Oops! payments failed", Toast.LENGTH_SHORT).show();

            }
        }
    }

    //@Override
   // protected void onDestroy() {
     //   stopService(new Intent(this, PayPalService.class));
      //  super.onDestroy();
   // }
}

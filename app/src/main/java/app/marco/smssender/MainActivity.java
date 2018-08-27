package app.marco.smssender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mPhoneNumber, mBody, mNumberOfSms;
    private ProgressBar mSendingProgress;
    private Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},1);

        mPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        mBody = (EditText) findViewById(R.id.body);
        mNumberOfSms = (EditText) findViewById(R.id.numberOfSms);
        mSend = (Button) findViewById(R.id.send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage(){
        int numberOfSms = Integer.parseInt(mNumberOfSms.getText().toString());
        for(int i = 0; i < numberOfSms; i++){
            if(i >= numberOfSms){
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(mPhoneNumber.getText().toString(), null, "Successfully send " + mNumberOfSms.getText().toString() + " messages!" , null, null);
                Toast.makeText(this, "Successfully send " + mNumberOfSms.getText().toString() + " messages to " + mPhoneNumber.getText().toString() + "!", Toast.LENGTH_LONG).show();
            } else {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(mPhoneNumber.getText().toString(), null, mBody.getText().toString(), null, null);
                Toast.makeText(this, "Sms " + i + " from " + numberOfSms + " sended.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.credits:
                startActivity(new Intent(this, CreditActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

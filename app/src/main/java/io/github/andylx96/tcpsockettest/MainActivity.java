package io.github.andylx96.tcpsockettest;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText el;
//    private Socket s;
//    private PrintWriter writer;
//    private MessageSender messageSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        el= (EditText)findViewById(R.id.editText);

        Thread myThread = new Thread(new MyServerThread());
        myThread.start();


    }

    class MyServerThread implements Runnable{

        Socket s;
        ServerSocket ss;
        InputStreamReader isr;
        BufferedReader bufferedReader;
        String message;
        Handler h = new Handler();

        @Override
        public void run() {


            try {
                ss = new ServerSocket(7801);
                while (true){

                    s = ss.accept();
                    isr = new InputStreamReader(s.getInputStream());
                    bufferedReader = new BufferedReader(isr);
                    message = bufferedReader.readLine();

                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public void send(View v){

        String message = el.getText().toString();
        BackgroundTask b1 = new BackgroundTask();
            b1.execute(message);

//        MessageSender messageSender = new MessageSender();
//        messageSender.execute(el.getText().toString());
//        Toast.makeText(getApplicationContext(),"Sent", Toast.LENGTH_SHORT).show();
    }

    class BackgroundTask extends AsyncTask<String,Void,Void>{

        Socket s;
        PrintWriter writer;

        @Override
        protected Void doInBackground(String... voids) {


            try {

                String message = voids[0];
                s = new Socket("192.168.1.158",7800);
                writer = new PrintWriter( s.getOutputStream());
                writer.write(message);
                writer.flush();
                writer.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;


        }
    }

}

//package io.github.andylx96.tcpsockettest;
//
//import android.os.AsyncTask;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//
///**
// * Created by zabuz on 11/6/2017.
// */
//
//public class MessageSender extends AsyncTask<String, Void, Void> {
//
//
//    Socket s;
//    DataOutputStream dos;
//    PrintWriter pw;
//
//
//
//    @Override
//
//    protected Void doInBackground(String... voids) {
//
//        String message = voids[0];
//
//        try {
//            s = new Socket("192.168.1.158",7800 );
//            pw = new PrintWriter(s.getOutputStream());
//            pw.write(message);
//            pw.flush();
//            pw.close();
//            s.close();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}

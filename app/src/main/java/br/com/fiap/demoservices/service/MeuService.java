package br.com.fiap.demoservices.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MeuService extends Service {

    private final String TAG = "MEUSERVICE";
    private boolean isRunning = false;

    public MeuService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Serviço criado");
        isRunning = true;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "On start Command: " + startId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i <= 20; i++){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){

                    }

                    if(isRunning){
                        Log.i(TAG, "Servico Rodando." + i);
                    }
                }

                // para o serviço. finaliza a tarefa
                stopSelf();
            }

        }).start();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //isRunning = false;
        Log.i(TAG, "Serviço parado");
    }
}

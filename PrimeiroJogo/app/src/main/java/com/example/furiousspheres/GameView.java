package com.example.furiousspheres;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.furiousspheres.R;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable{
    private Thread thread;
    private boolean isPlaying;
    private Objetos fundo;
    private Paint paint;
    private float x=0;
    private float y=0;
    private float screenRatioX, screenRatioY;
    private int screenX, screenY;
    private int direcao=0;
    private Bolinha[] bolinhas = new Bolinha[6];
    private Random numAleatorio = new Random();
    private int contador=0;
    private boolean notTouching = true;
    private int gameMaxCooldown=120;
    private int gameMinCoolDown = 60;
    private boolean fim = false;
    private String debug = "";
    private int pontos = 0;
    private float larguraIdeal = 1350;
    private float alturaIdeal = 750;
    float alteraX;
    float alteraY;

    public GameView(Context context, int screenX, int screenY){
        super(context);
        //alteraX =  (screenX * (larguraIdeal/screenX));
        //alteraY =  (screenY * (larguraIdeal/screenX));
       // this.screenX = Math.round();
        //this.screenY = (int) screenY*(larguraIdeal/screenX);
        this.screenX=screenX;
        this.screenY=screenY;

        screenRatioX = screenX / larguraIdeal;
        screenRatioY = screenY / alturaIdeal;
        fundo = new Objetos(0,0,screenX,screenY);
        fundo.bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fundo_heaven);
        fundo.bitmap = Bitmap.createScaledBitmap(fundo.bitmap, fundo.width,fundo.height, false);

        iniciaBolinha(0);
        iniciaBolinha(1);
        iniciaBolinha(2);
        iniciaBolinha(3);
        iniciaBolinha(4);
        iniciaBolinha(5);

        paint= new Paint();
    }

    public void iniciaBolinha(int indice){
        if (indice==0) bolinhas[indice] = new Bolinha(screenRatioX ,screenRatioY, getResources(), R.drawable.cor1, R.drawable.receptaculocor1, R.drawable.cor10, R.drawable.cor11, R.drawable.cor12, R.drawable.cor13, R.drawable.cor14, R.drawable.cor15, R.drawable.cor16, R.drawable.cor17, R.drawable.cor18, R.drawable.cor19);
        else if (indice==1) bolinhas[indice] = new Bolinha(screenRatioX ,screenRatioY, getResources(), R.drawable.cor2, R.drawable.receptaculocor2, R.drawable.cor20, R.drawable.cor21, R.drawable.cor22, R.drawable.cor23, R.drawable.cor24, R.drawable.cor25, R.drawable.cor26, R.drawable.cor27, R.drawable.cor28, R.drawable.cor29);
        else if (indice==2) bolinhas[indice] = new Bolinha(screenRatioX ,screenRatioY, getResources(), R.drawable.cor3, R.drawable.receptaculocor3, R.drawable.cor30, R.drawable.cor31, R.drawable.cor32, R.drawable.cor33, R.drawable.cor34, R.drawable.cor35, R.drawable.cor36, R.drawable.cor37, R.drawable.cor38, R.drawable.cor39);
        else if (indice==3) bolinhas[indice] = new Bolinha(screenRatioX ,screenRatioY, getResources(), R.drawable.cor4, R.drawable.receptaculocor4, R.drawable.cor40, R.drawable.cor41, R.drawable.cor42, R.drawable.cor43, R.drawable.cor44, R.drawable.cor45, R.drawable.cor46, R.drawable.cor47, R.drawable.cor48, R.drawable.cor49);
        else if (indice==4) bolinhas[indice] = new Bolinha(screenRatioX ,screenRatioY, getResources(), R.drawable.cor5, R.drawable.receptaculocor5, R.drawable.cor50, R.drawable.cor51, R.drawable.cor52, R.drawable.cor53, R.drawable.cor54, R.drawable.cor55, R.drawable.cor56, R.drawable.cor57, R.drawable.cor58, R.drawable.cor59);
        else if (indice==5) bolinhas[indice] = new Bolinha(screenRatioX ,screenRatioY, getResources(), R.drawable.cor6, R.drawable.receptaculocor6, R.drawable.cor60, R.drawable.cor61, R.drawable.cor62, R.drawable.cor63, R.drawable.cor64, R.drawable.cor65, R.drawable.cor66, R.drawable.cor67, R.drawable.cor68, R.drawable.cor69);
     /* while((bolinhas[indice].bolinhaAberta.x+bolinhas[indice].bolinhaAberta.width)>=bolinhas[indice].bolinhaFechada.x && bolinhas[indice].bolinhaAberta.x<bolinhas[indice].bolinhaFechada.x+bolinhas[indice].bolinhaFechada.width && (bolinhas[indice].bolinhaAberta.y+bolinhas[indice].bolinhaAberta.height)>=bolinhas[indice].bolinhaFechada.y && bolinhas[indice].bolinhaAberta.y<bolinhas[indice].bolinhaFechada.y+bolinhas[indice].bolinhaFechada.height){
            bolinhas[indice].bolinhaAberta.x = numAleatorio.nextInt((int) (screenX - bolinhas[indice].bolinhaAberta.width));
            bolinhas[indice].bolinhaFechada.x = numAleatorio.nextInt((int) (screenX - bolinhas[indice].bolinhaFechada.width));
            bolinhas[indice].bolinhaAberta.y = numAleatorio.nextInt((int) (screenY - bolinhas[indice].bolinhaAberta.height));
            bolinhas[indice].bolinhaFechada.y = numAleatorio.nextInt((int) (screenY - bolinhas[indice].bolinhaFechada.height));
        }
*/
       if(indice!=0)bolinhas[indice].contador=numAleatorio.nextInt((gameMaxCooldown*indice)-(gameMinCoolDown*indice))+(gameMinCoolDown*indice);
       //// else if(indice==1)bolinhas[indice].contador=gameMaxCooldown/3;
       // else if(indice==2)bolinhas[indice].contador=gameMaxCooldown/2;
       // else bolinhas[indice].contador=gameMaxCooldown;
      // bolinhas[indice].ativo=true;
       bolinhas[indice].ativo=false;
    }
    public boolean tentaIniciarBolinha(int indice){
        //bolinhas[indice] = new Bolinha(screenRatioX ,screenRatioY, getResources(), R.drawable.bolinha_azul, R.drawable.receptaculo_bolinha_azul, R.drawable.azul_0, R.drawable.azul_1, R.drawable.azul_2, R.drawable.azul_3, R.drawable.azul_4, R.drawable.azul_5, R.drawable.azul_6, R.drawable.azul_7, R.drawable.azul_8, R.drawable.azul_9);
        //bolinhas[indice].criar=true;
        Boolean emcima = false;
        bolinhas[indice].tempo=9;
        bolinhas[indice].bolinhaAberta.x = numAleatorio.nextInt((int) (screenX - bolinhas[indice].bolinhaAberta.width));
        bolinhas[indice].bolinhaFechada.x = numAleatorio.nextInt((int) (screenX - bolinhas[indice].bolinhaFechada.width));
        bolinhas[indice].bolinhaAberta.y = numAleatorio.nextInt((int) (screenY - bolinhas[indice].bolinhaAberta.height));
        bolinhas[indice].bolinhaFechada.y = numAleatorio.nextInt((int) (screenY - bolinhas[indice].bolinhaFechada.height));
        for(int i=0;i< bolinhas.length;i++) {
            if(bolinhas[i].ativo || i==indice) {
                if ((bolinhas[indice].bolinhaAberta.x + bolinhas[indice].bolinhaAberta.width) >= bolinhas[i].bolinhaFechada.x && bolinhas[indice].bolinhaAberta.x < bolinhas[i].bolinhaFechada.x + bolinhas[i].bolinhaFechada.width && (bolinhas[indice].bolinhaAberta.y + bolinhas[indice].bolinhaAberta.height) >= bolinhas[i].bolinhaFechada.y && bolinhas[indice].bolinhaAberta.y < bolinhas[i].bolinhaFechada.y + bolinhas[i].bolinhaFechada.height) {
                    emcima = true;
                }
                if (indice != i && (bolinhas[i].bolinhaAberta.x + bolinhas[i].bolinhaAberta.width) >= bolinhas[indice].bolinhaFechada.x && bolinhas[i].bolinhaAberta.x < bolinhas[indice].bolinhaFechada.x + bolinhas[indice].bolinhaFechada.width && (bolinhas[i].bolinhaAberta.y + bolinhas[i].bolinhaAberta.height) >= bolinhas[indice].bolinhaFechada.y && bolinhas[i].bolinhaAberta.y < bolinhas[indice].bolinhaFechada.y + bolinhas[indice].bolinhaFechada.height) {
                    emcima = true;
                }
                if (indice != i && ((bolinhas[indice].bolinhaAberta.x + bolinhas[indice].bolinhaAberta.width) >= bolinhas[i].bolinhaAberta.x && bolinhas[indice].bolinhaAberta.x < bolinhas[i].bolinhaAberta.x + bolinhas[i].bolinhaAberta.width && (bolinhas[indice].bolinhaAberta.y + bolinhas[indice].bolinhaAberta.height) >= bolinhas[i].bolinhaAberta.y && bolinhas[indice].bolinhaAberta.y < bolinhas[i].bolinhaAberta.y + bolinhas[i].bolinhaAberta.height)) {
                    emcima = true;
                }
                if (indice != i && ((bolinhas[indice].bolinhaFechada.x + bolinhas[indice].bolinhaFechada.width) >= bolinhas[i].bolinhaFechada.x && bolinhas[indice].bolinhaFechada.x < bolinhas[i].bolinhaFechada.x + bolinhas[i].bolinhaFechada.width && (bolinhas[indice].bolinhaFechada.y + bolinhas[indice].bolinhaFechada.height) >= bolinhas[i].bolinhaFechada.y && bolinhas[indice].bolinhaFechada.y < bolinhas[i].bolinhaFechada.y + bolinhas[i].bolinhaFechada.height)) {
                    emcima = true;
                }
            }
        }
        return (!emcima);
    }

    @Override
    public void run(){
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    public void update(){
        debug="";
        if(!fim) {
            contador++;
            if(contador>=60) {
                contador=0;
                for(int i=0;i< bolinhas.length;i++) {
                    if (bolinhas[i].ativo) {
                        bolinhas[i].tempo--;
                        if (bolinhas[i].tempo <= 0){
                            bolinhas[i].ativo = false;
                            if(i!=0)bolinhas[i].contador = numAleatorio.nextInt((gameMaxCooldown*i)-(gameMinCoolDown*i))+(gameMinCoolDown*i);
                            else bolinhas[i].contador =60;
                            fim=true;
                        }else bolinhas[i].atualizaTempo();

                    }
                }
            }
            for(int i=0;i< bolinhas.length;i++) {
                if (!bolinhas[i].ativo && !bolinhas[i].criar) {
                    bolinhas[i].contador--;
                    if (bolinhas[i].contador <= 0) {
                        bolinhas[i].criar = true;
                    }
                }else{
                    if (bolinhas[i].ativo && notTouching && ((bolinhas[i].bolinhaAberta.x + bolinhas[i].bolinhaAberta.width) >= bolinhas[i].bolinhaFechada.x && bolinhas[i].bolinhaAberta.x < bolinhas[i].bolinhaFechada.x + bolinhas[i].bolinhaFechada.width && (bolinhas[i].bolinhaAberta.y + bolinhas[i].bolinhaAberta.height) >= bolinhas[i].bolinhaFechada.y && bolinhas[i].bolinhaAberta.y < bolinhas[i].bolinhaFechada.y + bolinhas[i].bolinhaFechada.height)) {
                        pontos+=i+1;
                        bolinhas[i].ativo = false;
                        gameMaxCooldown--;
                        gameMinCoolDown--;
                        if(i!=0)bolinhas[i].contador = numAleatorio.nextInt((gameMaxCooldown*i)-(gameMinCoolDown*i))+(gameMinCoolDown*i);
                        else bolinhas[i].contador =60;
                    }
                }
                if(bolinhas[i].criar){
                    bolinhas[i].ativo = tentaIniciarBolinha(i);
                    if (bolinhas[i].ativo) {
                        bolinhas[i].criar = false;
                        bolinhas[i].atualizaTempo();
                    }
                    debug+=bolinhas[i].ativo+"/";
                }
            }
        }
    }

    public void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(fundo.bitmap, 0, 0, paint);
            if(!fim) {
                for (int i = 0; i < bolinhas.length; i++) {
                    if (bolinhas[i].ativo) {
                        canvas.drawBitmap(bolinhas[i].bolinhaFechada.bitmap, bolinhas[i].bolinhaFechada.x, bolinhas[i].bolinhaFechada.y, paint);
                        canvas.drawBitmap(bolinhas[i].tempoImagem.bitmap, (bolinhas[i].bolinhaFechada.x + (bolinhas[i].bolinhaFechada.width / 2) - (bolinhas[i].tempoImagem.width / 2)), (bolinhas[i].bolinhaFechada.y + (bolinhas[i].bolinhaFechada.height / 2) - (bolinhas[i].tempoImagem.height / 2)), paint);
                       }
                }
                for (int i = 0; i < bolinhas.length; i++) {
                    if (bolinhas[i].ativo) {
                        canvas.drawBitmap(bolinhas[i].bolinhaAberta.bitmap, bolinhas[i].bolinhaAberta.x, bolinhas[i].bolinhaAberta.y, paint);
                    }
                }
                paint.setColor(Color.BLACK);
                paint.setTextSize(60);
                //debug = "" + screenX + " - " + screenY + " - "+ alteraX + " - " + screenRatioY;
                canvas.drawText(pontos + "", screenX/2, screenY-60, paint);
                //canvas.drawText(debug + "", screenX/2, screenY-60, paint);
                getHolder().unlockCanvasAndPost(canvas);
            }else{

                paint.setColor(Color.BLACK);
                paint.setTextSize(60);
                canvas.drawText("Fim, você fez: " + pontos + " pontos", screenX/2-300, screenY/2, paint);
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    public void sleep(){
        try{
            Thread.sleep(17);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    public void resume(){
        isPlaying=true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause(){
        try{
            isPlaying=false;
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public boolean onTouchEvent(MotionEvent event){
        if(!fim) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    for (int i = 0; i < bolinhas.length; i++) {
                        if (bolinhas[i].ativo) {
                            if (event.getX() > bolinhas[i].bolinhaAberta.x && event.getX() < (bolinhas[i].bolinhaAberta.x + bolinhas[i].bolinhaAberta.width) && event.getY() > bolinhas[i].bolinhaAberta.y && event.getY() < (bolinhas[i].bolinhaAberta.y + bolinhas[i].bolinhaAberta.height)) {
                                direcao = i;
                            }
                        }
                    }
                    notTouching = false;
                    break;
                case MotionEvent.ACTION_UP:
                    direcao = -1;
                    notTouching = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (direcao >= 0) {
                        bolinhas[direcao].bolinhaAberta.x = event.getX() - (bolinhas[direcao].bolinhaAberta.width / 2);
                        bolinhas[direcao].bolinhaAberta.y = event.getY() - (bolinhas[direcao].bolinhaAberta.height / 2);
                    }
                    break;
            }
        }
        return true;
    }

}

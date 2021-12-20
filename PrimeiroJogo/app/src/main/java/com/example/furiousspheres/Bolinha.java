package com.example.furiousspheres;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bolinha{
    int tempo = 9;
    int contador=0;
    boolean ativo = false;
    boolean criar = false;
    Objetos bolinhaFechada;
    Objetos bolinhaAberta;
    Objetos tempoImagem;
    int bolinhaWidht = 150;
    int bolinhaHeight = 150;
    int tempoWidht = 75;
    int tempoHeight = 75;
    int tempoImg9;
    int tempoImg8;
    int tempoImg7;
    int tempoImg6;
    int tempoImg5;
    int tempoImg4;
    int tempoImg3;
    int tempoImg2;
    int tempoImg1;
    int tempoImg0;
    Resources res;

    public Bolinha(float screenRatioX, float screenRatioY, Resources res, int bolinhaA, int bolinhaF, int tempoImg0, int tempoImg1, int tempoImg2, int tempoImg3, int tempoImg4, int tempoImg5, int tempoImg6, int tempoImg7, int tempoImg8, int tempoImg9){
        this.res = res;
        this.tempoImg0 = tempoImg0;
        this.tempoImg1 = tempoImg1;
        this.tempoImg2 = tempoImg2;
        this.tempoImg3 = tempoImg3;
        this.tempoImg4 = tempoImg4;
        this.tempoImg5 = tempoImg5;
        this.tempoImg6 = tempoImg6;
        this.tempoImg7 = tempoImg7;
        this.tempoImg8 = tempoImg8;
        this.tempoImg9 = tempoImg9;
        bolinhaFechada = new Objetos(0,0, (int) (bolinhaWidht*screenRatioX),(int) (bolinhaHeight*screenRatioY));
        bolinhaFechada.bitmap = BitmapFactory.decodeResource(res, bolinhaF);
        bolinhaFechada.bitmap = Bitmap.createScaledBitmap(bolinhaFechada.bitmap,bolinhaFechada.width,bolinhaFechada.height , false);

        bolinhaAberta = new Objetos(0,0, (int) (bolinhaWidht*screenRatioX),(int) (bolinhaHeight*screenRatioY));
        bolinhaAberta.bitmap = BitmapFactory.decodeResource(res, bolinhaA);
        bolinhaAberta.bitmap = Bitmap.createScaledBitmap(bolinhaAberta.bitmap,bolinhaAberta.width,bolinhaAberta.height , false);

        tempoImagem = new Objetos(0,0, (int) (tempoWidht*screenRatioX),(int) (tempoHeight*screenRatioY));
        tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg9);
        tempoImagem.bitmap = Bitmap.createScaledBitmap(tempoImagem.bitmap,tempoImagem.width,tempoImagem.height , false);
    }
    public void atualizaTempo(){
        tempoImagem = new Objetos(tempoImagem.x,tempoImagem.y, tempoImagem.width, tempoImagem.height);
        if(tempo==9){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg9);
        }else  if(tempo==8){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg8);
        }else if(tempo==7){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg7);
        }else if(tempo==6){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg6);
        }else if(tempo==5){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg5);
        }else if(tempo==4){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg4);
        }else if(tempo==3){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg3);
        }else if(tempo==2){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg2);
        }else if(tempo==1){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg1);
        }else if(tempo==0){
            tempoImagem.bitmap = BitmapFactory.decodeResource(res, tempoImg0);
        }
        tempoImagem.bitmap = Bitmap.createScaledBitmap(tempoImagem.bitmap,tempoImagem.width,tempoImagem.height , false);
    }
}

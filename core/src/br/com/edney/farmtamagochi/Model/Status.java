package br.com.edney.farmtamagochi.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Desktop on 25/05/2017.
 */

public class Status {
    private int fomeMax;
    private int fomeMin;
    private int frequenciaFome;
    private int quantidadePorFome;
    private int fomeAtual;

    private float timeToEvolve;
    private boolean isReadyToEvolve;

    // Usado para calcular os status
    private float timerFome = 0;

    public Status(){
        fomeMax = 100;
        fomeMin = 0;
        frequenciaFome = 3;
        quantidadePorFome = 1;
        fomeAtual = fomeMax;

        timeToEvolve = 60;
        isReadyToEvolve = false;
    }

    public void update(float deltaTime){
        timerFome += deltaTime;

        if(timerFome >= frequenciaFome){
            // nunca abaixar mais que fomeMin
            fomeAtual = MathUtils.clamp(fomeAtual - quantidadePorFome, fomeMin, fomeMax);
            timerFome = 0;
        }

        timeToEvolve -= deltaTime;

        if(timeToEvolve <= 0){
            isReadyToEvolve = true;
        }
    }

    public int getFomeMax() {
        return fomeMax;
    }

    public void setFomeMax(int fomeMax) {
        this.fomeMax = fomeMax;
    }

    public int getFomeMin() {
        return fomeMin;
    }

    public void setFomeMin(int fomeMin) {
        this.fomeMin = fomeMin;
    }

    public int getFrequenciaFome() {
        return frequenciaFome;
    }

    public void setFrequenciaFome(int frequenciaFome) {
        this.frequenciaFome = frequenciaFome;
    }

    public int getQuantidadePorFome() {
        return quantidadePorFome;
    }

    public void setQuantidadePorFome(int quantidadePorFome) {
        this.quantidadePorFome = quantidadePorFome;
    }

    public int getFomeAtual() {
        return fomeAtual;
    }

    public void setFomeAtual(int fomeAtual) {
        this.fomeAtual = fomeAtual;
    }

    public float getTimeToEvolve() {
        return timeToEvolve;
    }

    public void setTimeToEvolve(float timeToEvolve) {
        this.timeToEvolve = timeToEvolve;
    }
    public boolean isReadyToEvolve() {
        return isReadyToEvolve;
    }

    public void setReadyToEvolve(boolean readyToEvolve) {
        isReadyToEvolve = readyToEvolve;
    }
}

package br.com.edney.farmtamagochi.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Desktop on 25/05/2017.
 */

public class CuidadoPet {
    private int fomeMax;
    private int fomeMin;
    private int frequenciaFome;
    private int quantidadePorFome;

    private int fomeAtual;
    private float timer = 0;

    public CuidadoPet(){
        fomeMax = 100;
        fomeMin = 0;
        frequenciaFome = 3;
        quantidadePorFome = 1;
        fomeAtual = fomeMax;
    }

    public void update(float deltaTime){
        timer += deltaTime;
        if(timer >= frequenciaFome){
            // nunca abaixar mais que fomeMin
            fomeAtual = MathUtils.clamp(fomeAtual - quantidadePorFome, fomeMin, fomeMax);
            timer = 0;
            Gdx.app.log("Cuidados", "Atualizou fome para "+fomeAtual);
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
}

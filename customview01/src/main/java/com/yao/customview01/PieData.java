package com.yao.customview01;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class PieData {
    private String name;
    private float value;
    private float percentage;

    private int color = 0;
    private float angle = 0;

    public PieData(String name, float value) {
        this.name = name;
        this.value = value;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}

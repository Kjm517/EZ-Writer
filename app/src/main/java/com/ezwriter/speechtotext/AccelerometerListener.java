package com.ezwriter.speechtotext;

public interface AccelerometerListener {

    void onAccelerationChanged(float x, float y, float z);

    void onShake(float force);
}
package com.aliosman.emall.Interface;

import java.io.Serializable;

public interface IStepperListener extends Serializable {
    void OnNext(int position,Object o);
}

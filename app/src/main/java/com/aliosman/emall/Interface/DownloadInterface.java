package com.aliosman.emall.Interface;

import java.util.List;

public abstract class DownloadInterface<T> {
    public void Start() {

    }
    public abstract void Complete(List<T> items);
}

package com.aliosman.emall.Interface;

public abstract class DownloadSingleInterface<T> {
    public void Start() {}
    public abstract void Complete(T item);
}

package com.kamilkirstein.indexcards.asynctaskreplacement;

import java.util.concurrent.Callable;

// implementation from https://medium.com/swlh/asynctask-is-deprecated-now-what-f30c31362761

public interface CustomCallable<R> extends Callable<R> {
    void setDataAfterLoading(R result);
    void setUiForLoading();
}

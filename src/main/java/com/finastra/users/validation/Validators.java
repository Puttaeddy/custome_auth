package com.finastra.users.validation;
@FunctionalInterface
public interface Validators<T> {
    public boolean validate(T t);
}

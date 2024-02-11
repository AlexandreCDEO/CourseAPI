package com.rocketseat.courseapi.infra;

import java.util.ArrayList;
import java.util.List;

public class ResultDTO<T> {

    private T data;
    private List<String> errors = new ArrayList<>();

    public ResultDTO(T data, List<String> errors) {
        this.data = data;
        this.errors = errors;
    }

    public ResultDTO(T data) {
        this.data = data;
    }

    public ResultDTO(List<String> errors) {
        this.errors = errors;
    }

    public ResultDTO(String erro) {
        this.errors.add(erro);
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }
}

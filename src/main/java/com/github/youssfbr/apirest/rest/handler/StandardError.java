package com.github.youssfbr.apirest.rest.handler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String erro;
    private String messagem;
    private String path;

}

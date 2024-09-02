package com.dev.pos.dto;

public class ProductDetailsJoinDto {

    private int code;
    private String description;
    private BatchDTO batchDTO;

    public ProductDetailsJoinDto() {
    }

    public ProductDetailsJoinDto(int code, String description, BatchDTO batchDTO) {
        this.code = code;
        this.description = description;
        this.batchDTO = batchDTO;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BatchDTO getBatchDTO() {
        return batchDTO;
    }

    public void setBatchDTO(BatchDTO batchDTO) {
        this.batchDTO = batchDTO;
    }
}

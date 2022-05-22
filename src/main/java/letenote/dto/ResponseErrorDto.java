package letenote.dto;

import java.util.Date;

public class ResponseErrorDto {
    private Integer status;
    private String shortCode;
    private Date timestamp;
    private String message;

    public ResponseErrorDto() {
    }

    public ResponseErrorDto setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ResponseErrorDto setShortCode(String shortCode) {
        this.shortCode = shortCode;
        return this;
    }

    public ResponseErrorDto setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ResponseErrorDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public String getShortCode() {
        return shortCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

}

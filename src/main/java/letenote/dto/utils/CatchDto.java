package letenote.dto.utils;

import java.util.Date;

public class CatchDto extends CustomDto {
    private String message;

    public CatchDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public CatchDto setShortCode(String shortCode) {
        this.shortCode = shortCode;
        return this;
    }

    public CatchDto setStatus(Integer status) {
        this.status = status;
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

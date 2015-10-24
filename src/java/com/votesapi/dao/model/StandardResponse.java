
package com.votesapi.dao.model;

/**
 *
 * @author artem
 */
public class StandardResponse {
    
    private boolean result;
    private String message;

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
    
    
    
}

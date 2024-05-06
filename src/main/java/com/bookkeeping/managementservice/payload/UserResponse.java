package com.bookkeeping.managementservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private List<String> errors = new ArrayList<>();

    public boolean isEmptyErrorList(){
        return this.getErrors().isEmpty();
    }
}

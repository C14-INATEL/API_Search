package com.api_search.project.response;

import com.api_search.project.excepetion.FindByPasswordExcept;
import org.springframework.stereotype.Component;

@Component
public class FindByPasswordResponse {

    public String parse(String body, String suffix) throws FindByPasswordExcept {
        try {
            for (String line : body.split("\r?\n")) {
                String[] parts = line.split(":");
                if (parts[0].equalsIgnoreCase(suffix)) {
                    return "Password leaked " + parts[1].trim() + " time";
                }
            }
            return "Password was not found in leaked password logs";
        }
        catch (Exception e)
        {
            throw new FindByPasswordExcept(e.getMessage());
        }
    }
}

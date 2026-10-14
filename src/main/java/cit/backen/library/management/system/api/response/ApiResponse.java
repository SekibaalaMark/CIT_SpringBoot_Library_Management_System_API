package cit.backen.library.management.system.api.response;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    String status;
    String message;
    T data;
}

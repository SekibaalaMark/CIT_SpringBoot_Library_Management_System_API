package cit.backen.library.management.system.api.response;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    String status;
    String message;
    T data;
}

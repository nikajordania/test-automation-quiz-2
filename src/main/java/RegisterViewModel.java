import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterViewModel(
        @JsonProperty("userName") Object userName,
        @JsonProperty("password") Object password) {
}

package cicd.cicd.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDto {
    private final Long pk;
    private final String content;
    private final Integer number;
}

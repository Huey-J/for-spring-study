package cicd.cicd.web;

import cicd.cicd.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")   //GET요청 응답
    public ResponseEntity<?> hello() {
        return new ResponseEntity<ResponseDto>(new ResponseDto(1L, "content", 2), HttpStatus.OK);
    }
}

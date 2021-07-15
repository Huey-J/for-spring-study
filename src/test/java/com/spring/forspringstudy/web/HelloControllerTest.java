package com.spring.forspringstudy.web;

//라이브러리
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//선언
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)    // helloController를 테스트함
public class HelloControllerTest {

    @Autowired                  //스프링 빈 주입
    private MockMvc mvc;        //웹 API테스트를 위함 (GET, POST 등)

    @Test
    public void 헬로가_출력되야함() throws Exception {
        mvc.perform(get("/hello"))                          //'/hello'에 GET 요청
                .andExpect(status().isOk())                           //200, ok
                .andExpect(content().string("hello"));  //내용은 'hello'
    }

    @Test
    public void helloDto가_리턴되야함() throws Exception {
        String name = "hello";
        int amount = 100;

        mvc.perform(get("/hello/dto").param("name", name)                      //GET테스트 시 param 설정
                                               .param("amount", String.valueOf(amount))) //param은 String만 허용되기 때문에 형변환 사용
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))        //json으로 name키를 가진 값의 value가 위에서의 name값과 같아야 함
                .andExpect(jsonPath("$.amount", is(amount)));   //json으로 amount키를 가진 값의 value가 위에서의 amount값과 같아야 함
    }
}

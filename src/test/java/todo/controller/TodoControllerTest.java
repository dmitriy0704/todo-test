package todo.controller;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.whitelist.ValidationErrorsWhitelist;
import com.atlassian.oai.validator.whitelist.rule.WhitelistRules;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTodos_ReturnsResponseWithStatusOk() throws Exception {

        //Дано:
        var requestBuilder = MockMvcRequestBuilders.get("/api/todos");

        //Когда:
        this.mockMvc.perform(requestBuilder)
                // Тогда:
                .andExpectAll(status().isOk(),
                        openApi().isValid("static/openapi.json"),
                        content().contentTypeCompatibleWith("application/todo+json"),
                        content().json("""
                                       [
                                          {
                                             "id": "1",
                                             "title":  "Купить хлеб",
                                             "description": "Купить хлеб 1 булку"
                                          },
                                          {
                                             "id": "2",
                                             "title": "Купить молоко",
                                             "description": "Купить молоко 2л"
                                          }
                                       ]
                                """)
                );

    }


    @Test
    void createTodo_ReturnsResponseWithStatusCreated() throws Exception {

        // given
        var requestBuilder = post("/api/todos")
                .contentType("application/todos+json")
                .content("""
                        {
                            "title": "Купить молоко",
                            "description": "Купить молоко 1 литр"
                        }
                        """);

        //when
        this.mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        status().isCreated(),
                        openApi().isValid("static/openapi.json"),
                        header().exists(HttpHeaders.LOCATION),
                        content().contentTypeCompatibleWith("application/todos+json"),
                        content().json("""
                                    {
                                       "title": "Купить молоко",
                                        "description": "Купить молоко 1 литр"
                                    }
                                """),
                        jsonPath("$.id").exists()
                );
    }


    @Test
    void createTodo_PayloadIsInvalid_ReturnsResponseWithStatusCreated() throws Exception {

        // given
        var requestBuilder = post("/api/todos")
                .contentType("application/todos+json")
                .content("""
                        {
                            "title": null,
                            "details": "Купить молоко 1 литр"
                        }
                        """);

        //when
        this.mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        status().isBadRequest(),
                        openApi().isValid(OpenApiInteractionValidator
                                .createFor("static/openapi.json")
                                .withWhitelist(ValidationErrorsWhitelist.create()
                                        .withRule("Ignoring null title",
                                                WhitelistRules
                                                        .messageHasKey("validation.request.body.schema.type")

                                        ))
                                .withWhitelist(
                                        ValidationErrorsWhitelist.create()
                                                .withRule("Ignoring additionalProperties",
                                                        WhitelistRules
                                                                .messageHasKey("validation.request.body.schema.additionalProperties")

                                                )
                                )
                                .build())
                );
    }
}
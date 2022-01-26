package seoul.DietDiary;

import com.google.api.client.json.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.charset.Charset;

@Configuration
public class DialogFlowConfiguration {
        @Bean
        public JsonFactory jsonFactory() {
                return new JsonFactory() {
                        @Override
                        public JsonParser createJsonParser(InputStream in) throws IOException {
                                return null;
                        }

                        @Override
                        public JsonParser createJsonParser(InputStream in, Charset charset) throws IOException {
                                return null;
                        }

                        @Override
                        public JsonParser createJsonParser(String value) throws IOException {
                                return null;
                        }

                        @Override
                        public JsonParser createJsonParser(Reader reader) throws IOException {
                                return null;
                        }

                        @Override
                        public JsonGenerator createJsonGenerator(OutputStream out, Charset enc) throws IOException {
                                return null;
                        }

                        @Override
                        public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
                                return null;
                        }
                };
        }
}
package com.example.Ttunes;

import static org.assertj.core.api.Assertions.*;

import com.example.Ttunes.DTO.Component;
import com.example.Ttunes.DTO.Juke;
import com.example.Ttunes.DTO.Setting;
import com.example.Ttunes.DTO.Settings;
import com.example.Ttunes.Exceptions.NullJukeListException;
import com.example.Ttunes.Exceptions.NullSettingsListException;
import com.example.Ttunes.Service.FilteredJukesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class FilteredJukesServiceTests {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private FilteredJukesService filteredJukesService;
    private final String JUKE_URL = "http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes";
    private final String SETTINGS_URL = "http://my-json-server.typicode.com/touchtunes/tech-assignment/settings";


    @Test
    public void getFilterdJukes() {
        Component component = new Component.Builder()
                .name("led_panel")
                .build();
        Component component2 = new Component.Builder()
                .name("amplifier")
                .build();
        Component component3 = new Component.Builder()
                .name("speaker")
                .build();

        Juke juke = new Juke.Builder()
                .id("1234")
                .model("models")
                .components(new ArrayList<>(Arrays.asList(component, component2)))
                .build();
        Juke juke2 = new Juke.Builder()
                .id("5678")
                .model("modelx")
                .components(new ArrayList<>(Arrays.asList(component, component3)))
                .build();
        Setting setting = new Setting.Builder().id("2468").requires(new ArrayList<>(Arrays.asList("speaker", "led_panel"))).build();
        Setting setting2 = new Setting.Builder().id("1357").requires(new ArrayList<>(Arrays.asList("speaker", "amplifier"))).build();
        Settings settings = new Settings.Builder().settings(new ArrayList<>(Arrays.asList(setting, setting2))).build();

        Mockito.when(restTemplate.exchange(JUKE_URL, HttpMethod.GET, null, Juke[].class)).thenReturn(new ResponseEntity<>(new Juke[]{juke, juke2}, HttpStatus.OK));
        Mockito.when(restTemplate.exchange(SETTINGS_URL, HttpMethod.GET, null, Settings.class)).thenReturn(new ResponseEntity<>(settings, HttpStatus.OK));

        List<Juke> testJuke = filteredJukesService.getFilterdJukes("2468", null, null, null);
        assertThat(testJuke.get(0).getId()).isEqualTo("5678");

        testJuke = filteredJukesService.getFilterdJukes("2468", "models", null, null);
        assertThat(testJuke.size()).isEqualTo(0);


    }

    @Test(expected = NullJukeListException.class)
    public void getFilterdJukesThrowsNullJukeListException() {
        Setting setting = new Setting.Builder().id("2468").requires(new ArrayList<>(Arrays.asList("speaker", "led_panel"))).build();
        Setting setting2 = new Setting.Builder().id("1357").requires(new ArrayList<>(Arrays.asList("speaker", "amplifier"))).build();
        Settings settings = new Settings.Builder().settings(new ArrayList<>(Arrays.asList(setting, setting2))).build();

        Mockito.when(restTemplate.exchange(JUKE_URL, HttpMethod.GET, null, Juke[].class)).thenReturn(new ResponseEntity<>(null, HttpStatus.OK));
        Mockito.when(restTemplate.exchange(SETTINGS_URL, HttpMethod.GET, null, Settings.class)).thenReturn(new ResponseEntity<>(settings, HttpStatus.OK));

        filteredJukesService.getFilterdJukes("2468", null, null, null);


    }

    @Test(expected = NullSettingsListException.class)
    public void getFilterdJukesThrowsNullSettingsListException() {
        Component component = new Component.Builder()
                .name("led_panel")
                .build();
        Component component2 = new Component.Builder()
                .name("amplifier")
                .build();
        Component component3 = new Component.Builder()
                .name("speaker")
                .build();

        Juke juke = new Juke.Builder()
                .id("1234")
                .model("models")
                .components(new ArrayList<>(Arrays.asList(component, component2)))
                .build();
        Juke juke2 = new Juke.Builder()
                .id("5678")
                .model("modelx")
                .components(new ArrayList<>(Arrays.asList(component, component3)))
                .build();

        Mockito.when(restTemplate.exchange(JUKE_URL, HttpMethod.GET, null, Juke[].class)).thenReturn(new ResponseEntity<>(new Juke[]{juke, juke2}, HttpStatus.OK));
        Mockito.when(restTemplate.exchange(SETTINGS_URL, HttpMethod.GET, null, Settings.class)).thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        filteredJukesService.getFilterdJukes("2468", null, null, null);


    }
}
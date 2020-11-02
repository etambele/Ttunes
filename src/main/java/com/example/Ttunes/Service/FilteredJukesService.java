package com.example.Ttunes.Service;

import com.example.Ttunes.DTO.*;
import com.example.Ttunes.Exceptions.NullJukeListException;
import com.example.Ttunes.Exceptions.NullSettingsListException;
import com.example.Ttunes.Routes.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class FilteredJukesService implements IFilteredJukeService{
    private static final Logger LOGGER = LoggerFactory.getLogger(FilteredJukesService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Juke> getFilterdJukes(String settingId, String model, Integer pageSize, Integer offSet){
        List<Juke> allJukes = getJukesList();
        List<Juke> filteredJukes = new ArrayList<>();
        Setting filteredSetting = getFilteredSetting(settingId);

        if(!allJukes.isEmpty() && filteredSetting != null){
            filteredJukes = getJukesWithComponent(filteredSetting, allJukes);
            if(model!= null){
                filteredJukes = filteredJukes.stream()
                        .filter(juke -> juke.getModel().equals(model))
                        .collect(Collectors.toList());
            }
            if(pageSize != null && offSet != null && offSet <= filteredJukes.size()){
                filteredJukes = filteredJukes.subList(offSet, Math.min(offSet + pageSize, filteredJukes.size()));
            }
        }

        return filteredJukes;
    }



    private List<Juke> getJukesList(){
        LOGGER.info("Getting list of Jukes from uri");
        Juke[] jukeList = restTemplate.exchange(Routes.BASE_URL + Routes.JUKES, HttpMethod.GET,null, Juke[].class).getBody();
        return Arrays.asList(Optional.ofNullable(jukeList).orElseThrow(NullJukeListException::new));
    }

    private Settings getSettingsList(){
        LOGGER.info("Getting list of settings from uri");
        return Optional.ofNullable(restTemplate.exchange(Routes.BASE_URL + Routes.SETTINGS, HttpMethod.GET,null, Settings.class).getBody())
                .orElseThrow(NullSettingsListException::new);
    }

    private Setting getFilteredSetting(String settingId){
        return getSettingsList().getSettings().stream()
                .filter(setting -> settingId.equals(setting.getId()))
                .findFirst()
                .orElse(null);
    }

    private List<Juke> getJukesWithComponent(Setting filteredSetting, List<Juke> allJukes) {
        return allJukes.stream().filter(juke -> {
            List<String> componentNames = juke.getComponents().stream()
                            .map(Component::getName)
                            .collect(Collectors.toList());

            return componentNames.containsAll(filteredSetting.getRequires());
        }).collect(Collectors.toList());
    }

}

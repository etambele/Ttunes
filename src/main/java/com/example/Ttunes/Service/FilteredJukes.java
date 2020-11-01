package com.example.Ttunes.Service;

import com.example.Ttunes.DTO.Juke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilteredJukes {

    @Autowired
    IFilteredJukeService iFilteredJukeService;

    public List<Juke> getJukes(String settingId, String model, Integer pageSize, Integer offSet){
        return iFilteredJukeService.getFilterdJukes(settingId, model, pageSize, offSet);
    }

}

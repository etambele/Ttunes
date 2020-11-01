package com.example.Ttunes.Service;

import com.example.Ttunes.DTO.Juke;

import java.util.List;

public interface IFilteredJukeService {

    List<Juke> getFilterdJukes(String settingId, String model, Integer pageSize, Integer offset);
}

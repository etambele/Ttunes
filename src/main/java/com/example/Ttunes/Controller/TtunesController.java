package com.example.Ttunes.Controller;

import com.example.Ttunes.DTO.Juke;
import com.example.Ttunes.Service.FilteredJukes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ttunes")
public class TtunesController implements ITtunesController{

    private final FilteredJukes filteredJukes;

    public TtunesController(FilteredJukes filteredJukes) {
        this.filteredJukes = filteredJukes;
    }

    @Override
    public ResponseEntity<List<Juke>> getListOfJuke(String settingId, String model, Integer pageSize, Integer offSet) {
        return new ResponseEntity<>(filteredJukes.getJukes(settingId, model, pageSize, offSet), HttpStatus.OK);
    }
}

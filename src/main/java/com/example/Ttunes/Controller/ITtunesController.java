package com.example.Ttunes.Controller;

import com.example.Ttunes.DTO.Juke;
import com.example.Ttunes.Routes.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ITtunesController {
    @RequestMapping(value = Routes.GET_JUKES, method= RequestMethod.GET)
    ResponseEntity<List<Juke>> getListOfJuke(@RequestParam String settingId,
                                             @RequestParam(required = false) String model,
                                             @RequestParam(required = false) Integer pageSize,
                                             @RequestParam(required = false) Integer offset);
}

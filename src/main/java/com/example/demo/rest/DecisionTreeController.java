package com.example.demo.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.other.DTAttributes;
import com.example.demo.service.impl.DecisionTree;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/decision")
public class DecisionTreeController {

	@PostMapping(value = "/result")
	public ResponseEntity<Boolean> getResult(@RequestBody DTAttributes data) throws IOException, Exception {
		Boolean result = DecisionTree.resultDecisionTree(data.getType(), data.getValues());
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}

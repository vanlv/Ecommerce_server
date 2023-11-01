package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.user.CommentDto;
import com.example.demo.service.CommentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

	@Qualifier("commentServiceImpl")
	@Autowired
	private CommentService service;

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<MessageResponse> createComment(@RequestBody CommentDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		MessageResponse result = service.createComment(dto);
		return new ResponseEntity<MessageResponse>(result, HttpStatus.OK);
	}
	
	@PutMapping("/confirm/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> displayComment(@RequestBody CommentDto dto, @PathVariable Long id) {
		dto.setId(id);
		MessageResponse result = service.displayComment(dto);
		return new ResponseEntity<MessageResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CommentDto> getById(@PathVariable Long id) {
		CommentDto result = service.getOne(id);
		return new ResponseEntity<CommentDto>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/product/search")
	public ResponseEntity<List<CommentDto>> getAllByProduct(@RequestParam Long productId) {
		List<CommentDto> result = service.getAllCommentByProduct(productId);
		return new ResponseEntity<List<CommentDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<CommentDto>> getAllByUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		List<CommentDto> result = service.getAllCommentByUser(username);
		return new ResponseEntity<List<CommentDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<CommentDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "24") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		SearchDto dto = new SearchDto(page, limit, keyword);
		Page<CommentDto> result = service.getAllComments(dto);
		return new ResponseEntity<Page<CommentDto>>(result, HttpStatus.OK);
	}

}
